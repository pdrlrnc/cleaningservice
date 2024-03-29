package dev.cleaningservice.controller;

import dev.cleaningservice.dto.ClientProfileDTO;
import dev.cleaningservice.dto.EmployeeProfileDTO;
import dev.cleaningservice.entity.UserEmployee;
import dev.cleaningservice.entity.UserEntity;
import dev.cleaningservice.entity.UserInfo;
import dev.cleaningservice.service.UserEmployeeService;
import dev.cleaningservice.service.UserInfoService;
import dev.cleaningservice.utils.Utils;
import dev.cleaningservice.validation.EmailAlreadyExistsException;
import dev.cleaningservice.validation.UsernameAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    private UserInfoService userInfoService;

    private UserEmployeeService userEmployeeService;

    @Autowired
    public ProfileController(UserInfoService userInfoService, UserEmployeeService userEmployeeService){

        this.userInfoService = userInfoService;
        this.userEmployeeService = userEmployeeService;
    }

    @GetMapping("/profile/edit")
    public String showSignUpPage(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();

        UserEntity userEntity = (UserEntity) session.getAttribute("user");

        UserInfo userInfo = userInfoService.findUserInfoByUsername(userEntity.getUsername());

        EmployeeProfileDTO employeeProfileDTO = new EmployeeProfileDTO(userInfo.getId(), userInfo.getUsername(), userInfo.getEmail(),
                userInfo.getFirstName(), userInfo.getFullName(), userInfo.getDateOfBirth(), userInfo.getPhoneNumber(), userInfo.getAddress());

        model.addAttribute("employeeProfileDTO", employeeProfileDTO);

        return "profile-edit";
    }

    @PostMapping("/profile/edit/save")
    public String saveProfile(
            @Valid @ModelAttribute("employeeProfileDTO") EmployeeProfileDTO employeeProfileDTO,
            BindingResult bindingResult, Model model, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("employeeProfileDTO", employeeProfileDTO);
            return "profile-edit";
        }
        try {
            userInfoService.save(employeeProfileDTO);
        } catch (EmailAlreadyExistsException eaee) {
            model.addAttribute("emailError", employeeProfileDTO.getEmail() + " is already taken");
            return "profile";
        } catch (UsernameAlreadyExistsException uaee) {
            model.addAttribute("usernameError", employeeProfileDTO.getUsername() + " is already taken");
            return "profile";
        }

        HttpSession session = request.getSession();
        UserEntity loggedUser = (UserEntity) session.getAttribute("user");
        loggedUser.setUsername(employeeProfileDTO.getUsername());
        session.setAttribute("user", loggedUser);

        model = Utils.listEmployees(model, userEmployeeService);

        return "home";
    }
    @GetMapping("/profile/{userId}")
    public String showProfile(@PathVariable("userId") Long userId, Model model) {

        System.out.println(userId + ":userID");

        UserInfo userInfo = userInfoService.findUserInfoById(userId);
        System.out.println(userInfo + ": USERINFO\n\n");

        UserEmployee userEmployee = userEmployeeService.getByUserInfoId(userInfo.getId());

        System.out.println(userEmployee + ": USEREMPLOYEE\n\n");

        if(userEmployee != null) {
            EmployeeProfileDTO employeeProfileDTO = userInfoService.populateEmployeeProfileDTO(userInfo);

            model.addAttribute("employeeProfileDTO", employeeProfileDTO);

            return "employee-profile-display";
        } else {

            ClientProfileDTO clientProfileDTO = userInfoService.populateClientProfileDTO(userInfo);

            model.addAttribute("clientProfileDTO", clientProfileDTO);

            return "client-profile-display";
        }
    }
}
