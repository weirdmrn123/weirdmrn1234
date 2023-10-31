package com.simpleform2.simpleform2.controller;

import com.simpleform2.simpleform2.model.UserModel;
import com.simpleform2.simpleform2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model){
        model.addAttribute("registerRequest", new UserModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UserModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel usermodel, Model model){
        System.out.println("register request:"+usermodel);
        UserModel registeredUser=userService.registerUser(usermodel.getLogin(),usermodel.getPassword(),
                usermodel.getEmail());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel, Model model){
        System.out.println("login request:"+userModel);
        UserModel authenticated=userService.authenticate(userModel.getLogin(),userModel.getPassword());
        if(authenticated != null) {
            model.addAttribute("userLogin", authenticated.getLogin());
            return "personal_page";

        }else{
            return "error_page";
        }
    }

}
