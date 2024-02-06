package dev.cleaningservice.controller;

import dev.cleaningservice.entity.Role;
import dev.cleaningservice.entity.UserDetail;
import dev.cleaningservice.service.RoleService;
import dev.cleaningservice.service.UserDetailService;
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

    private UserDetailService userDetailService;

    @Autowired
    public HomepageController(RoleService roleService, UserDetailService userDetailService){

        this.roleService = roleService;
        this.userDetailService = userDetailService;
    }

    @GetMapping("/")
    public String showHomePage(Model model){

        //get roles from database
        List<Role> listRole = roleService.findAll();

        //add the list to the model
        model.addAttribute("listRole", listRole);

        //return name of the template view
        return "home";
    }

    @GetMapping("/sign-up")
    public String showSignUpPage(Model model){

        UserDetail userDetail = new UserDetail();

        model.addAttribute("userDetail", userDetail);

        return "sign-up";
    }

    @PostMapping("/sign-up/save")
    public String save(
            @Valid @ModelAttribute("userDetail") UserDetail userDetail,
            BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()) {
            model.addAttribute("userDetail", userDetail);
            return "sign-up";
        }

        userDetailService.save(userDetail);

        return "home";
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
