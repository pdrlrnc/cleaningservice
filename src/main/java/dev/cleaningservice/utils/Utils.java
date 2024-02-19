package dev.cleaningservice.utils;

import dev.cleaningservice.entity.UserEmployee;
import dev.cleaningservice.service.UserEmployeeService;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

public class Utils {

    public static Model listEmployees(Model model, UserEmployeeService userEmployeeService){
        List<UserEmployee> employees = userEmployeeService.listEmployees();
        model.addAttribute("employees", employees);

        return model;
    }

    public static RedirectAttributes reddirectToLastPage(RedirectAttributes redirectAttributes){
        String currentUrl = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
        redirectAttributes.addAttribute("lastPage", currentUrl);
        return redirectAttributes;
    }
}
