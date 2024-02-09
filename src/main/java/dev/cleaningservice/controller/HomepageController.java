package dev.cleaningservice.controller;

import dev.cleaningservice.entity.Role;
import dev.cleaningservice.entity.UserInfo;
import dev.cleaningservice.service.RoleService;
import dev.cleaningservice.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomepageController {

    private RoleService roleService;

    private UserInfoService userInfoService;

    @Autowired
    public HomepageController(RoleService roleService, UserInfoService userInfoService){

        this.roleService = roleService;
        this.userInfoService = userInfoService;
    }

    @GetMapping("/")
    public String showHomePage(Model model){

        return "home";
    }

    @GetMapping("/profile")
    public String showSignUpPage(Model model){

        UserInfo userInfo = new UserInfo();

        model.addAttribute("userInfo", userInfo);

        return "profile";
    }

    @PostMapping("/profile/save")
    public String save(
            @Valid @ModelAttribute("userInfo") UserInfo userInfo,
            BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()) {
            System.out.println(userInfo);
            model.addAttribute("userInfo", userInfo);
            return "profile";
        }

        userInfoService.save(userInfo);

        return "home";
    }

    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

    //add initbinder to convert/strip input string
    //remove leading and trailing whitespace
    //resolve issues for our validation, namely to
    //convert empty strings "" to null
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }


}
