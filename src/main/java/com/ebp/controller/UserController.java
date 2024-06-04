package com.ebp.controller;
import com.ebp.entity.Cart;
import com.ebp.entity.Goods;
import com.ebp.entity.OrderBase;
import com.ebp.entity.OrderDetail;
import com.ebp.entity.OrderStatus;
import com.ebp.entity.Profile;
import com.ebp.entity.User;
import com.ebp.service.CartService;
import com.ebp.service.GoodsService;
import com.ebp.service.OrderBaseService;
import com.ebp.service.OrderDetailService;
import com.ebp.service.OrderStatusService;
import com.ebp.service.ProfileService;
import com.ebp.utils.ConvertVOUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class UserController {
    @Resource
    private GoodsService goodsService;
    @Resource
    private CartService cartService;
    @Resource
    private OrderStatusService orderStatusService;
    @Resource
    private OrderBaseService orderBaseService;
    @Resource
    private OrderDetailService orderDetailService;
    @Resource
    private ProfileService profileService;
    @RequestMapping(
            value = "/user/index",
            method = RequestMethod.GET
    )
    public String index() {
        return "/user/index";
    }
    @RequestMapping(
            value = "/user/select-goods",
            method = RequestMethod.GET
    )
    public String selectGoods(Model model) {
        List<List<Object>> goodsDetailVOList = goodsService
                .selectAllGoods()
                .stream()
                .map((Goods goods)->{
                    List<Object> goodsDetailVO = ConvertVOUtils.convertGoodsDetailVO(goods);
                    return goodsDetailVO;
                })
                .collect(Collectors.toList());
        model.addAttribute("goodsDetailVOList", goodsDetailVOList);
        return "/user/goods/select-goods";
    }
    @RequestMapping(
            value = "/user/select-goods-detail",
            method = RequestMethod.GET
    )
    public String selectGoodsDetail(
            @RequestParam(value = "id") Integer id,
            Model model
    ) {
        Goods goods = goodsService.selectGoodsById(id);
        List<Object> goodsDetailVO = ConvertVOUtils.convertGoodsDetailVO(goods);
        model.addAttribute("goodsDetailVO", goodsDetailVO);
        return "/user/goods/select-goods-detail";
    }
    @RequestMapping(
            value = "/user/select-goods-detail",
            method = RequestMethod.POST
    )
    public String selectGoodsDetail(
            @RequestParam(value = "goodsId") Integer goodsId,
            @RequestParam(value = "goodsNum") Integer goodsNum,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("user");
        Goods goods = goodsService.selectGoodsById(goodsId);
        goods.setStore(goods.getStore() - goodsNum);
        goodsService.updateGoodsById(goods);
        OrderBase orderBase = new OrderBase();
        orderBase.setUserId(user.getId());
        orderBase.setOrderTime(LocalDateTime.now());
        orderBase.setOrderStatusId(1);
        orderBaseService.insertOrderBase(orderBase);
        List<OrderBase> orderBaseList = orderBaseService.selectAllOrderBase();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderBaseId(orderBaseList.get(orderBaseList.size() - 1).getId());
        orderDetail.setGoodsId(goodsId);
        orderDetail.setGoodsNum(goodsNum);
        orderDetailService.insertOrderDetail(orderDetail);
        return "redirect:/user/select-goods-detail?id=" + goodsId;
    }
    @RequestMapping(
            value = "/user/insert-my-cart",
            method = RequestMethod.GET
    )
    public String insertMyCart(
            @RequestParam(value = "id") Integer id,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("user");
        Optional<Cart> cartOptional = cartService
                .selectCartByUserId(user.getId())
                .stream()
                .filter((Cart cart)->{
                    return Objects.equals(cart.getGoodsId(), id);
                })
                .findFirst();
        if (cartOptional.isPresent() == true) {
            Cart cart = cartOptional.get();
            cart.setGoodsNum(cart.getGoodsNum() + 1);
            cartService.updateCartById(cart);
        }
        else {
            Cart cart = new Cart();
            cart.setUserId(user.getId());
            cart.setGoodsId(id);
            cart.setGoodsNum(1);
            cartService.insertCart(cart);
        }
        return "redirect:/user/select-goods";
    }
    @RequestMapping(
            value = "/user/update-my-cart",
            method = RequestMethod.POST
    )
    public String updateMyCart(
            @RequestParam Map<String, String> map,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("user");
        List<Cart> cartList = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (Objects.equals(entry.getValue(), "on") == true) {
                Integer cartId = Integer.valueOf(entry.getKey().split("-")[1]);
                Integer goodsNum = Integer.valueOf(map.get(String.valueOf(cartId)));
                Cart cart = cartService.selectCartById(cartId);
                cart.setGoodsNum(goodsNum);
                cartList.add(cart);
            }
        }
        OrderBase orderBase = new OrderBase();
        orderBase.setUserId(user.getId());
        orderBase.setOrderTime(LocalDateTime.now());
        orderBase.setOrderStatusId(1);
        orderBaseService.insertOrderBase(orderBase);
        List<OrderBase> orderBaseList = orderBaseService.selectAllOrderBase();
        Integer orderBaseId = orderBaseList.get(orderBaseList.size() - 1).getId();
        for (int i = 0; i < cartList.size(); i++) {
            Cart cart = cartList.get(i);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderBaseId(orderBaseId);
            orderDetail.setGoodsId(cart.getGoodsId());
            orderDetail.setGoodsNum(cart.getGoodsNum());
            orderDetailService.insertOrderDetail(orderDetail);
            cartService.deleteCartById(cart.getId());
        }
        return "redirect:/user/select-my-cart";
    }
    @RequestMapping(
            value = "/user/select-my-cart",
            method = RequestMethod.GET
    )
    public String insertMyCart(
            HttpSession session,
            Model model
    ) {
        User user = (User) session.getAttribute("user");
        List<List<Object>> cartDetailVOList = cartService
                .selectCartByUserId(user.getId())
                .stream()
                .map((Cart cart)->{
                    Goods goods = goodsService.selectGoodsById(cart.getGoodsId());
                    List<Object> cartDetailVO = ConvertVOUtils.convertCartDetailVO(cart, goods);
                    return cartDetailVO;
                })
                .collect(Collectors.toList());
        model.addAttribute("cartDetailVOList", cartDetailVOList);
        return "/user/cart/select-my-cart";
    }
    @RequestMapping(
            value = "/user/update-my-order-base",
            method = RequestMethod.GET
    )
    public String updateMyOrderBase(
            @RequestParam(value = "id") Integer id,
            Model model
    ) {
        OrderBase orderBase = orderBaseService.selectOrderBaseById(id);
        List<Object> orderBaseVO = ConvertVOUtils.convertOrderBaseVO1(orderBase);
        model.addAttribute("orderBaseVO", orderBaseVO);
        model.addAttribute("orderStatusList", orderStatusService.selectAllOrderStatus());
        return "/user/order/update-my-order-base";
    }
    @RequestMapping(
            value = "/user/update-my-order-base",
            method = RequestMethod.POST
    )
    public String updateMyOrderBase(
            @RequestParam(value = "orderBaseId") Integer orderBaseId,
            @RequestParam(value = "orderStatusId") Integer orderStatusId
    ) {
        List<OrderStatus> orderStatusList = orderStatusService.selectAllOrderStatus();
        OrderBase orderBase = orderBaseService.selectOrderBaseById(orderBaseId);
        orderBase.setOrderStatusId(orderStatusId);
        if (Objects.equals(orderStatusId, orderStatusList.get(1).getId()) == true) {
            List<OrderDetail> orderDetailList = orderDetailService.selectOrderDetailByOrderBaseId(orderBaseId);
            for (int i = 0; i < orderDetailList.size(); i++) {
                OrderDetail orderDetail = orderDetailList.get(i);
                Goods goods = goodsService.selectGoodsById(orderDetail.getGoodsId());
                goods.setStore(goods.getStore() - orderDetail.getGoodsNum());
                goodsService.updateGoodsById(goods);
            }
        }
        else if (Objects.equals(orderStatusId, orderStatusList.get(4).getId()) == true) {
            List<OrderDetail> orderDetailList = orderDetailService.selectOrderDetailByOrderBaseId(orderBaseId);
            for (int i = 0; i < orderDetailList.size(); i++) {
                OrderDetail orderDetail = orderDetailList.get(i);
                Goods goods = goodsService.selectGoodsById(orderDetail.getGoodsId());
                goods.setStore(goods.getStore() + orderDetail.getGoodsNum());
                goodsService.updateGoodsById(goods);
            }
        }
        orderBaseService.updateOrderBaseById(orderBase);
        return "redirect:/user/select-my-order-base";
    }
    @RequestMapping(
            value = "/user/select-my-order-base",
            method = RequestMethod.GET
    )
    public String selectMyOrderBase(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Object> orderBaseVOList = orderBaseService
                .selectOrderBaseByUserId(user.getId())
                .stream()
                .map((OrderBase orderBase)->{
                    List<Object> orderBaseVO = ConvertVOUtils.convertOrderBaseVO1(orderBase);
                    return orderBaseVO;
                })
                .collect(Collectors.toList());
        model.addAttribute("orderBaseVOList", orderBaseVOList);
        return "/user/order/select-my-order-base";
    }
    @RequestMapping(
            value = "/user/select-my-order-detail",
            method = RequestMethod.GET
    )
    public String selectMyOrderDetail(
            @RequestParam(value = "id") Integer id,
            Model model
    ) {
        OrderBase orderBase = orderBaseService.selectOrderBaseById(id);
        List<Object> orderBaseVO = ConvertVOUtils.convertOrderBaseVO2(orderBase);
        final BigDecimal[] price = {BigDecimal.valueOf(0.0)};
        List<List<Object>> orderDetailVOList = orderDetailService
                .selectOrderDetailByOrderBaseId(id)
                .stream()
                .map((OrderDetail orderDetail)->{
                    List<Object> orderDetailVO = ConvertVOUtils.convertOrderDetailVO(orderDetail);
                    price[0] = price[0].add(goodsService.selectGoodsById(orderDetail.getGoodsId()).getCurrentPrice().multiply(BigDecimal.valueOf(orderDetail.getGoodsNum())));
                    return orderDetailVO;
                })
                .collect(Collectors.toList());
        model.addAttribute("orderBaseVO", orderBaseVO);
        model.addAttribute("price", price[0]);
        model.addAttribute("orderDetailVOList", orderDetailVOList);
        return "/user/order/select-my-order-detail";
    }
    @RequestMapping(
            value = "/user/update-my-user",
            method = RequestMethod.POST
    )
    public String updateMyUser(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "email", required = false) String email
    ) {
        Profile profile = profileService.selectProfileByUserId(id);
        if (profile == null) {
            profile = new Profile();
            profile.setUserId(id);
            profile.setEmail(email);
            profileService.insertProfile(profile);
        }
        else {
            profile.setEmail(email);
            profileService.updateProfileById(profile);
        }
        return "redirect:/user/select-my-user";
    }
    @RequestMapping(
            value = "/user/select-my-user",
            method = RequestMethod.GET
    )
    public String selectMyUser(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Object> userDetailVO = ConvertVOUtils.convertUserDetailVO(user);
        model.addAttribute("userDetailVO", userDetailVO);
        return "/user/user/select-my-user";
    }
}