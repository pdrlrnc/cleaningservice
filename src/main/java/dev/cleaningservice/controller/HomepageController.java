package dev.cleaningservice.controller;

import dev.cleaningservice.entity.UserEntity;
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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/sign-up")
    public String showSignUp(Model model){
        UserEntity userEntity = new UserEntity();
        model.addAttribute("userEntity", userEntity);
        return "sign-up";
    }

    @PostMapping("/sign-up/save")
    public String saveSignUp(@Valid @RequestParam("userEntity") UserEntity userEntity,
                             BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("userEntity", userEntity);
            return "sign-up";
        }

        UserInfo userInfo = userInfoService.findUserInfoByUsername(userEntity.getUsername());
        if(userInfo == null){
            //how to have error message? maybe add a model attribute with the string error?
            return "sign-up";
        }

        //add userEntity to db

        model.addAttribute("userInfo", userInfo);
        return "profile";
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
