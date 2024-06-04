package com.ebp.controller;
import com.ebp.entity.Goods;
import com.ebp.entity.OrderBase;
import com.ebp.entity.OrderDetail;
import com.ebp.entity.Profile;
import com.ebp.entity.User;
import com.ebp.service.GoodsService;
import com.ebp.service.GoodsTypeService;
import com.ebp.service.OrderBaseService;
import com.ebp.service.OrderDetailService;
import com.ebp.service.OrderStatusService;
import com.ebp.service.ProfileService;
import com.ebp.service.UserService;
import com.ebp.utils.ConvertVOUtils;
import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class AdminController {
    @Resource
    private GoodsTypeService goodsTypeService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private OrderStatusService orderStatusService;
    @Resource
    private OrderBaseService orderBaseService;
    @Resource
    private OrderDetailService orderDetailService;
    @Resource
    private UserService userService;
    @Resource
    private ProfileService profileService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @RequestMapping(
            value = "/admin/index",
            method = RequestMethod.GET
    )
    public String index(Model model) {
        model.addAttribute("goodsNum", goodsService.selectAllGoods().size());
        model.addAttribute("orderBaseNum", orderBaseService.selectAllOrderBase().size());
        model.addAttribute("userNum", userService.selectAllUser().size());
        return "/admin/index";
    }
    @RequestMapping(
            value = "/admin/insert-goods",
            method = RequestMethod.GET
    )
    public String insertGoods(Model model) {
        model.addAttribute("goodsTypeList", goodsTypeService.selectAllGoodsType());
        return "/admin/goods/insert-goods";
    }
    @RequestMapping(
            value = "/admin/insert-goods",
            method = RequestMethod.POST
    )
    public String insertGoods(
            @RequestParam(value = "goodsTypeId") Integer goodsTypeId,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "originalPrice") BigDecimal originalPrice,
            @RequestParam(value = "currentPrice") BigDecimal currentPrice,
            @RequestParam(value = "store") Integer store,
            @RequestParam(value = "picture", required = false) String picture
    ) {
        Goods goods = new Goods();
        goods.setGoodsTypeId(goodsTypeId);
        goods.setTitle(title);
        goods.setOriginalPrice(originalPrice);
        goods.setCurrentPrice(currentPrice);
        goods.setStore(store);
        goods.setPicture(picture);
        goodsService.insertGoods(goods);
        return "redirect:/admin/select-goods";
    }
    @RequestMapping(
            value = "/admin/delete-goods",
            method = RequestMethod.GET
    )
    public String deleteGoods(
            @RequestParam(value = "id") Integer id
    ) {
        goodsService.deleteGoodsById(id);
        return "redirect:/admin/select-goods";
    }
    @RequestMapping(
            value = "/admin/update-goods",
            method = RequestMethod.GET
    )
    public String updateGoods(
            @RequestParam(value = "id") Integer id,
            Model model
    ) {
        Goods goods = goodsService.selectGoodsById(id);
        List<Object> goodsDetailVO = ConvertVOUtils.convertGoodsDetailVO(goods);
        model.addAttribute("goodsDetailVO", goodsDetailVO);
        model.addAttribute("goodsTypeList", goodsTypeService.selectAllGoodsType());
        return "/admin/goods/update-goods";
    }
    @RequestMapping(
            value = "/admin/update-goods",
            method = RequestMethod.POST
    )
    public String updateGoods(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "goodsTypeId") Integer goodsTypeId,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "originalPrice") BigDecimal originalPrice,
            @RequestParam(value = "currentPrice") BigDecimal currentPrice,
            @RequestParam(value = "store") Integer store,
            @RequestParam(value = "picture", required = false) String picture
    ) {
        Goods goods = goodsService.selectGoodsById(id);
        goods.setTitle(title);
        goods.setGoodsTypeId(goodsTypeId);
        goods.setOriginalPrice(originalPrice);
        goods.setCurrentPrice(currentPrice);
        goods.setStore(store);
        goods.setPicture(picture);
        goodsService.updateGoodsById(goods);
        return "redirect:/admin/select-goods";
    }
    @RequestMapping(
            value = "/admin/select-goods",
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
        return "/admin/goods/select-goods";
    }
    @RequestMapping(
            value = "/admin/delete-order-base",
            method = RequestMethod.GET
    )
    public String deleteOrderBase(
            @RequestParam(value = "id") Integer id
    ) {
        orderDetailService.deleteOrderDetailByOrderBaseId(id);
        orderBaseService.deleteOrderBaseById(id);
        return "redirect:/admin/select-order-base";
    }
    @RequestMapping(
            value = "/admin/update-order-base",
            method = RequestMethod.GET
    )
    public String updateOrderBase(
            @RequestParam(value = "id") Integer id,
            Model model
    ) {
        OrderBase orderBase = orderBaseService.selectOrderBaseById(id);
        List<Object> orderBaseVO = ConvertVOUtils.convertOrderBaseVO1(orderBase);
        model.addAttribute("orderBaseVO", orderBaseVO);
        model.addAttribute("orderStatusList", orderStatusService.selectAllOrderStatus());
        return "/admin/order/update-order-base";
    }
    @RequestMapping(
            value = "/admin/update-order-base",
            method = RequestMethod.POST
    )
    public String updateOrderBase(
            @RequestParam(value = "orderBaseId") Integer orderBaseId,
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "orderTime", required = false) LocalDateTime orderTime,
            @RequestParam(value = "orderStatusId", required = false) Integer orderStatusId
    ) {
        OrderBase orderBase = orderBaseService.selectOrderBaseById(orderBaseId);
        orderBase.setOrderStatusId(orderStatusId);
        orderBaseService.updateOrderBaseById(orderBase);
        return "redirect:/admin/select-order-base";
    }
    @RequestMapping(
            value = "/admin/select-order-base",
            method = RequestMethod.GET
    )
    public String selectOrderBase(Model model) {
        List<Object> orderBaseVOList = orderBaseService
                .selectAllOrderBase()
                .stream()
                .map((OrderBase orderBase)->{
                    List<Object> orderBaseVO = ConvertVOUtils.convertOrderBaseVO1(orderBase);
                    return orderBaseVO;
                })
                .collect(Collectors.toList());
        model.addAttribute("orderBaseVOList", orderBaseVOList);
        return "/admin/order/select-order-base";
    }
    @RequestMapping(
            value = "/admin/select-order-detail",
            method = RequestMethod.GET
    )
    public String selectOrderDetail(
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
        return "/admin/order/select-order-detail";
    }
    @RequestMapping(
            value = "/admin/insert-user",
            method = RequestMethod.GET
    )
    public String insertUser() {
        return "/admin/user/insert-user";
    }
    @RequestMapping(
            value = "/admin/insert-user",
            method = RequestMethod.POST
    )
    public String insertUser(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "role") String role,
            @RequestParam(value = "email", required = false) String email
    ) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userService.insertUser(user);
        if (email != null) {
            Profile profile = new Profile();
            profile.setUserId(userService.selectUserByUsername(username).getId());
            profile.setEmail(email);
            profileService.insertProfile(profile);
        }
        return "redirect:/admin/select-user";
    }
    @RequestMapping(
            value = "/admin/delete-user",
            method = RequestMethod.GET
    )
    public String deleteUser(
            @RequestParam(value = "id") Integer id
    ) {
        profileService.deleteProfileById(profileService.selectProfileByUserId(id).getId());
        userService.deleteUserById(id);
        return "redirect:/admin/select-user";
    }
    @RequestMapping(
            value = "/admin/update-user",
            method = RequestMethod.GET
    )
    public String updateUser(
            @RequestParam("id") Integer id,
            Model model
    ) {
        User user = userService.selectUserById(id);
        List<Object> userDetailVO = ConvertVOUtils.convertUserDetailVO(user);
        model.addAttribute("userDetailVO", userDetailVO);
        return "/admin/user/update-user";
    }
    @RequestMapping(
            value = "/admin/update-user",
            method = RequestMethod.POST
    )
    public String updateUser(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "role", required = false) String role,
            @RequestParam(value = "email", required = false) String email
    ) {
        User user = userService.selectUserById(id);
        user.setRole(role);
        userService.updateUserById(user);
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
        return "redirect:/admin/select-user";
    }
    @RequestMapping(
            value = "/admin/select-user",
            method = RequestMethod.GET
    )
    public String selectUser(Model model) {
        List<List<Object>> userDetailVOList = userService
                .selectAllUser()
                .stream()
                .map((User user)->{
                    List<Object> userDetailVO = ConvertVOUtils.convertUserDetailVO(user);
                    return userDetailVO;
                })
                .collect(Collectors.toList());
        model.addAttribute("userDetailVOList", userDetailVOList);
        return "/admin/user/select-user";
    }
}