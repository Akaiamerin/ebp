package com.ebp.controller;
import com.ebp.entity.Profile;
import com.ebp.entity.User;
import com.ebp.service.ProfileService;
import com.ebp.service.UserService;
import jakarta.annotation.Resource;
import java.util.Objects;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class AuthController {
    @Resource
    private UserService userService;
    @Resource
    private ProfileService profileService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @RequestMapping(
            value = "/",
            method = RequestMethod.GET
    )
    public String index() {
        return "/index";
    }
    @RequestMapping(
            value = "/auth/login",
            method = RequestMethod.GET
    )
    public String login() {
        return "/login";
    }
    @RequestMapping(
            value = "/auth/register",
            method = RequestMethod.GET
    )
    public String register() {
        return "/register";
    }
    @RequestMapping(
            value = "/auth/register",
            method = RequestMethod.POST
    )
    public String register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email
    ) {
        if (userService.selectUserByUsername(username) != null) {
            return "/register";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("user");
        userService.insertUser(user);
        Profile profile = new Profile();
        Integer userId = userService.selectUserByUsername(username).getId();
        profile.setUserId(userId);
        profile.setEmail(email);
        profileService.insertProfile(profile);
        return "redirect:/auth/login";
    }
    @RequestMapping(
            value = "/auth/forget",
            method = RequestMethod.GET
    )
    public String forget() {
        return "/forget";
    }
    @RequestMapping(
            value = "/auth/forget",
            method = RequestMethod.POST
    )
    public String forget(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        User user = userService.selectUserByUsername(username);
        if (user == null) {
            return "/forget";
        }
        Profile profile = profileService.selectProfileByUserId(user.getId());
        if (Objects.equals(profile.getEmail(), email) == false) {
            return "/forget";
        }
        user.setPassword(passwordEncoder.encode(password));
        userService.updateUserById(user);
        return "redirect:/auth/login";
    }
}