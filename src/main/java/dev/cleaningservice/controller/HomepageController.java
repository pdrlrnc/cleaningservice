package dev.cleaningservice.controller;

import dev.cleaningservice.dto.EmployeeDTO;
import dev.cleaningservice.dto.ProfileDTO;
import dev.cleaningservice.dto.RegistrationDTO;
import dev.cleaningservice.entity.UserEntity;
import dev.cleaningservice.entity.UserInfo;
import dev.cleaningservice.service.RoleService;
import dev.cleaningservice.service.UserInfoService;
import dev.cleaningservice.service.security.UserSecService;
import dev.cleaningservice.validation.EmailAlreadyExistsException;
import dev.cleaningservice.validation.UsernameAlreadyExistsException;
import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomepageController {

    private RoleService roleService;

    private UserInfoService userInfoService;

    private UserSecService userService;

    @Autowired
    public HomepageController(RoleService roleService, UserInfoService userInfoService, UserSecService userService) {

        this.roleService = roleService;
        this.userInfoService = userInfoService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {

        return "home";
    }

    @GetMapping("/profile")
    public String showSignUpPage(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();

        UserEntity userEntity = (UserEntity) session.getAttribute("user");

        UserInfo userInfo = userInfoService.findUserInfoByUsername(userEntity.getUsername());

        ProfileDTO profileDTO = new ProfileDTO(userInfo.getId(), userInfo.getUsername(), userInfo.getEmail(),
        userInfo.getFirstName(), userInfo.getFullName(), userInfo.getDateOfBirth(), userInfo.getPhoneNumber(), userInfo.getAddress());

        model.addAttribute("profileDTO", profileDTO);

        return "profile";
    }

    @PostMapping("/profile/save")
    public String saveProfile(
            @Valid @ModelAttribute("profileDTO") ProfileDTO profileDTO,
            BindingResult bindingResult, Model model, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("profileDTO", profileDTO);
            return "profile";
        }
        try {
            userInfoService.save(profileDTO);
        } catch (EmailAlreadyExistsException eaee) {
            model.addAttribute("emailError", profileDTO.getEmail() + " is already taken");
            return "profile";
        } catch (UsernameAlreadyExistsException uaee) {
            model.addAttribute("usernameError", profileDTO.getUsername() + " is already taken");
            return "profile";
        }

        HttpSession session = request.getSession();
        UserEntity loggedUser = (UserEntity) session.getAttribute("user");
        loggedUser.setUsername(profileDTO.getUsername());
        session.setAttribute("user", loggedUser);

        return "home";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/sign-up")
    public String showSignUp(Model model) {
        RegistrationDTO registrationDTO = new RegistrationDTO();
        model.addAttribute("registrationDTO", registrationDTO);
        return "sign-up";
    }

    @PostMapping("/sign-up/save")
    public String saveSignUp(@Valid @ModelAttribute("registrationDTO") RegistrationDTO registrationDTO,
                             BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationDTO", registrationDTO);
            return "sign-up";
        }

        if (userInfoService.findUserInfoByUsername(registrationDTO.getUsername()) != null) {
            model.addAttribute("registrationDTO", registrationDTO);
            model.addAttribute("usernameExists", true);
            return "sign-up";
        }
        if (userInfoService.findUserInfoByEmail(registrationDTO.getEmail()) != null) {
            model.addAttribute("registrationDTO", registrationDTO);
            model.addAttribute("emailExists", true);
            return "sign-up";
        }

        //add userEntity to db
        userService.save(registrationDTO);

        model.addAttribute("signup", true);

        //get back to login so user can authenticate
        return "login";
    }

    @GetMapping("/apply")
    public String showApply(Model model){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        model.addAttribute("employeeDTO", employeeDTO);
        return "apply";
    }

    //add initbinder to convert/strip input string
    //remove leading and trailing whitespace
    //resolve issues for our validation, namely to
    //convert empty strings "" to null
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }


}
