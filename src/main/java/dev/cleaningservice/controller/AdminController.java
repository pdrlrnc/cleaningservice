package dev.cleaningservice.controller;

import dev.cleaningservice.entity.UserEmployee;
import dev.cleaningservice.service.UserEmployeeService;
import dev.cleaningservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller

public class AdminController {

    private UserEmployeeService userEmployeeService;

    @Autowired
    public AdminController(UserEmployeeService userEmployeeService){
        this.userEmployeeService = userEmployeeService;
    }

    @GetMapping("/admin")
    public String showAdmin(){
        return "admin";
    }

    @GetMapping("/admin/new-applicants")
    public String showNewApplicants(Model model){

        model = Utils.listEmployees(model, userEmployeeService);

        return "new-applicants";
    }

    @GetMapping("/confirmApplicant")
    public String confirmApplicant(@RequestParam int id){

        userEmployeeService.confirmApplicant(id);

        return "redirect:/admin/new-applicants";
    }

    @GetMapping("/deleteApplicant")
    public String deleteApplicant(@RequestParam int id){

        userEmployeeService.deleteApplicant(id);

        return "redirect:/admin/new-applicants";
    }
}
