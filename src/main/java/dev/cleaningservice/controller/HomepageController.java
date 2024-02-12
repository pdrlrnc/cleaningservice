package dev.cleaningservice.controller;

import dev.cleaningservice.dto.RegistrationDTO;
import dev.cleaningservice.entity.UserEntity;
import dev.cleaningservice.entity.UserInfo;
import dev.cleaningservice.service.RoleService;
import dev.cleaningservice.service.UserInfoService;
import dev.cleaningservice.service.UserService;
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

    private UserService userService;

    @Autowired
    public HomepageController(RoleService roleService, UserInfoService userInfoService, UserService userService){

        this.roleService = roleService;
        this.userInfoService = userInfoService;
        this.userService = userService;
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
        RegistrationDTO registrationDTO = new RegistrationDTO();
        model.addAttribute("registrationDTO", registrationDTO);
        return "sign-up";
    }

    @PostMapping("/sign-up/save")
    public String save(@Valid @ModelAttribute("registrationDTO") RegistrationDTO registrationDTO,
                             BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("registrationDTO", registrationDTO);
            return "sign-up";
        }

        //add userEntity to db
        UserInfo userInfo = userService.save(registrationDTO);

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
