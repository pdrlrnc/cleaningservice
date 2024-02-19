package dev.cleaningservice.controller;

import dev.cleaningservice.dto.EmployeeDTO;
import dev.cleaningservice.dto.RegistrationDTO;
import dev.cleaningservice.entity.Appointment;
import dev.cleaningservice.entity.UserEntity;
import dev.cleaningservice.entity.UserInfo;
import dev.cleaningservice.service.RoleService;
import dev.cleaningservice.service.UserEmployeeService;
import dev.cleaningservice.service.UserInfoService;
import dev.cleaningservice.service.security.UserSecService;
import dev.cleaningservice.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
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

    private UserSecService userService;

    private UserEmployeeService userEmployeeService;

    @Autowired
    public HomepageController(RoleService roleService, UserInfoService userInfoService,
                              UserSecService userService, UserEmployeeService userEmployeeService) {

        this.roleService = roleService;
        this.userInfoService = userInfoService;
        this.userService = userService;
        this.userEmployeeService = userEmployeeService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {

        Appointment appointment = new Appointment();
        model.addAttribute("appointment", appointment);

        model = Utils.listEmployees(model, userEmployeeService);

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
    public String showApply(Model model, HttpServletRequest request){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");
        if(userEntity != null) {
            UserInfo userInfo = userInfoService.findUserInfoByUsername(userEntity.getUsername());
            if(userInfo != null){
                employeeDTO.setFullName(userInfo.getFullName());
                employeeDTO.setAddress(userInfo.getAddress());
                employeeDTO.setPhoneNumber(userInfo.getPhoneNumber());
                employeeDTO.setDateOfBirth(userInfo.getDateOfBirth());
                employeeDTO.setUsername(userEntity.getUsername());
            }
        } else {
            return "login";
        }
        System.out.println("\n\n\n\n\n" + employeeDTO + "\n\n\n\n\n\n\n");
        model.addAttribute("employeeDTO", employeeDTO);
        return "apply";
    }

    @PostMapping("/apply/save")
    public String saveApply(@Valid @ModelAttribute(name = "employeeDTO") EmployeeDTO employeeDTO,
                            BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("employeeDTO", employeeDTO);
            return "apply";
        }
        userService.save(employeeDTO);

        model = Utils.listEmployees(model, userEmployeeService);

        return "home";
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
