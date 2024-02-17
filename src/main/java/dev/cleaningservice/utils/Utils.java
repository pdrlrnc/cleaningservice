package dev.cleaningservice.utils;

import dev.cleaningservice.entity.UserEmployee;
import dev.cleaningservice.service.UserEmployeeService;
import org.springframework.ui.Model;

import java.util.List;

public class Utils {



    public static Model listEmployees(Model model, UserEmployeeService userEmployeeService){
        List<UserEmployee> employees = userEmployeeService.listEmployees();
        model.addAttribute("employees", employees);

        return model;
    }
}
