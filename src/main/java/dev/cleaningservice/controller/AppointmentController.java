package dev.cleaningservice.controller;

import dev.cleaningservice.entity.Appointment;
import dev.cleaningservice.entity.UserEntity;
import dev.cleaningservice.entity.UserInfo;
import dev.cleaningservice.service.AppointmentService;
import dev.cleaningservice.service.UserEmployeeService;
import dev.cleaningservice.service.UserInfoService;
import dev.cleaningservice.utils.Utils;
import dev.cleaningservice.validation.NoneAvailableCleaners;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppointmentController {

    private AppointmentService appointmentService;

    private UserInfoService userInfoService;

    private UserEmployeeService userEmployeeService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, UserInfoService userInfoService, UserEmployeeService userEmployeeService) {
        this.appointmentService = appointmentService;
        this.userInfoService = userInfoService;
        this.userEmployeeService = userEmployeeService;
    }

    @PostMapping("/appointment")
    public String createAppointment(Model model, @ModelAttribute("appointment") Appointment appointment,
                                    HttpServletRequest request) {

        HttpSession session = request.getSession();
        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        UserInfo userInfo = userInfoService.findUserInfoByUsername(userEntity.getUsername());
        appointment.setClient(userInfo);

        try {
            appointmentService.createAppointment(appointment);
        } catch (NoneAvailableCleaners nac){
            model.addAttribute("noneAvailableCleaners", nac.getMessage());
        }
        model = Utils.listEmployees(model, userEmployeeService);

        if(!model.containsAttribute("noneAvailableCleaners"))
            model.addAttribute("appointmentSuccess",
                    "Success creating your appointment, you can check it in your Appointments tab");

        return "home";
    }

    @GetMapping("/profile/appointments")
    public String showAppointmentsForClient(Model model, HttpServletRequest request){

        UserEntity userEntity = Utils.getUserInfoBySession(request);
        UserInfo client = userInfoService.findUserInfoByUsername(userEntity.getUsername());
        List<Appointment> appointmentList = appointmentService.listAppointmentsByClient(client);

        model.addAttribute("appointmentList", appointmentList);

        return "user-appointments";
    }

}
