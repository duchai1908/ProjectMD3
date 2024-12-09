package com.ra.projectmd3test.controller.user;

import com.ra.projectmd3test.model.dto.user.UserLogin;
import com.ra.projectmd3test.model.dto.user.UserRegister;
import com.ra.projectmd3test.model.entity.Image;
import com.ra.projectmd3test.model.entity.ProductDetail;
import com.ra.projectmd3test.service.impl.admin.*;
import com.ra.projectmd3test.service.impl.user.CartService;
import com.ra.projectmd3test.service.impl.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

// Đăng ký, đăng nhâp User
@Controller
@RequestMapping("/")
public class AuthUserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private BannerService bannerService;

    // Đăng ký user
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegister", new UserRegister());
        return "user/register";
    }
    @PostMapping("/register")
    public String doRegister(@Valid @ModelAttribute("userRegister") UserRegister userRegister, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userRegister", userRegister);
            return "user/register";
        }
        userService.save(userRegister);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userLogin", new UserLogin());
        return "user/login";
    }
    @PostMapping("/login")
    public String doLogin(@ModelAttribute("userLogin") UserLogin userLogin, Model model,HttpSession session) {
        if(userService.login(userLogin)){
            session.setAttribute("userLogin", userLogin);
            return "redirect:/";
        }else{
            model.addAttribute("error", "Invalid username or password!");
            model.addAttribute("userLogin",userLogin);
            return "user/login";
        }
    }

    @GetMapping(value = {"/","/index"})
    public String index(HttpSession session, Model model) {
        model.addAttribute("banners",bannerService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("productDetails",productDetailService.findAll(0,4));
        // Get the map of productDetailId to a single image from the service
        Map<Integer, Image> productImageMap = productDetailService.getOneProductImageMap();

        List<ProductDetail> productDetailList = productDetailService.findProductDetailsByCategoryId(2);
        model.addAttribute("productImageMap", productImageMap);
        // Retrieve userLogin from the session
        UserLogin userLogin = (UserLogin) session.getAttribute("userLogin");
        if (userLogin != null) {
            model.addAttribute("userLogin", userLogin);
        }

        return "user/index";
    }

}
