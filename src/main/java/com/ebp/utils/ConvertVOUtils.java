package com.ebp.utils;
import com.ebp.entity.Cart;
import com.ebp.entity.Goods;
import com.ebp.entity.OrderBase;
import com.ebp.entity.OrderDetail;
import com.ebp.entity.Profile;
import com.ebp.entity.User;
import com.ebp.service.GoodsService;
import com.ebp.service.GoodsTypeService;
import com.ebp.service.OrderStatusService;
import com.ebp.service.ProfileService;
import com.ebp.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
@Component
public class ConvertVOUtils {
    @Resource
    private GoodsService defaultGoodsService;
    private static GoodsService goodsService;
    @Resource
    private GoodsTypeService defaultGoodsTypeService;
    private static GoodsTypeService goodsTypeService;
    @Resource
    private OrderStatusService defaultOrderStatusService;
    private static OrderStatusService orderStatusService;
    @Resource
    private UserService defaultUserService;
    private static UserService userService;
    @Resource
    private ProfileService defaultProfileService;
    private static ProfileService profileService;
    private ConvertVOUtils() {

    }
    @PostConstruct
    public void init() {
        goodsService = defaultGoodsService;
        goodsTypeService = defaultGoodsTypeService;
        orderStatusService = defaultOrderStatusService;
        userService = defaultUserService;
        profileService = defaultProfileService;
    }
    public static List<Object> convertGoodsDetailVO(Goods goods) {
        List<Object> goodsDetailVO = new ArrayList<>();
        goodsDetailVO.add(goods.getId());
        goodsDetailVO.add(goodsTypeService.selectGoodsTypeById(goods.getGoodsTypeId()).getTitle());
        goodsDetailVO.add(goods.getTitle());
        goodsDetailVO.add(goods.getOriginalPrice());
        goodsDetailVO.add(goods.getCurrentPrice());
        goodsDetailVO.add(goods.getStore());
        goodsDetailVO.add(goods.getPicture());
        return goodsDetailVO;
    }
    public static List<Object> convertCartDetailVO(Cart cart, Goods goods) {
        List<Object> cartDetailVO = new ArrayList<>();
        cartDetailVO.add(cart.getId());
        cartDetailVO.add(goods.getId());
        cartDetailVO.add(goodsTypeService.selectGoodsTypeById(goods.getGoodsTypeId()).getTitle());
        cartDetailVO.add(goods.getTitle());
        cartDetailVO.add(goods.getCurrentPrice());
        cartDetailVO.add(goods.getStore());
        cartDetailVO.add(goods.getPicture());
        cartDetailVO.add(cart.getGoodsNum());
        return cartDetailVO;
    }
    public static List<Object> convertOrderBaseVO1(OrderBase orderBase) {
        List<Object> orderBaseVO = new ArrayList<>();
        orderBaseVO.add(orderBase.getId());
        orderBaseVO.add(orderBase.getUserId());
        orderBaseVO.add(orderBase.getOrderTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        orderBaseVO.add(orderStatusService.selectOrderStatusById(orderBase.getOrderStatusId()).getStatus());
        return orderBaseVO;
    }
    public static List<Object> convertOrderBaseVO2(OrderBase orderBase) {
        List<Object> orderBaseVO = new ArrayList<>();
        orderBaseVO.add(orderBase.getId());
        orderBaseVO.add(orderBase.getUserId());
        orderBaseVO.add(userService.selectUserById(orderBase.getUserId()).getUsername());
        orderBaseVO.add(orderBase.getOrderTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        orderBaseVO.add(orderStatusService.selectOrderStatusById(orderBase.getOrderStatusId()).getStatus());
        return orderBaseVO;
    }
    public static List<Object> convertOrderDetailVO(OrderDetail orderDetail) {
        List<Object> orderDetailVO = new ArrayList<>();
        orderDetailVO.add(orderDetail.getId());
        Goods goods = goodsService.selectGoodsById(orderDetail.getGoodsId());
        orderDetailVO.add(goods.getId());
        orderDetailVO.add(goods.getTitle());
        orderDetailVO.add(goods.getCurrentPrice());
        orderDetailVO.add(goods.getPicture());
        orderDetailVO.add(orderDetail.getGoodsNum());
        return orderDetailVO;
    }
    public static List<Object> convertUserDetailVO(User user) {
        List<Object> userDetailVO = new ArrayList<>();
        userDetailVO.add(user.getId());
        userDetailVO.add(user.getUsername());
        userDetailVO.add(Optional.of(profileService.selectProfileByUserId(user.getId())).orElse(new Profile()).getEmail());
        userDetailVO.add(user.getRole());
        return userDetailVO;
    }
}