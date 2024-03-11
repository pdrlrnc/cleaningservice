package dev.cleaningservice.controller;

import dev.cleaningservice.entity.Appointment;
import dev.cleaningservice.entity.UserEntity;
import dev.cleaningservice.entity.UserInfo;
import dev.cleaningservice.service.AppointmentService;
import dev.cleaningservice.service.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class JobController {

    private AppointmentService appointmentService;
    private UserInfoService userInfoService;

    @Autowired
    public JobController(AppointmentService appointmentService, UserInfoService userInfoService){
        this.appointmentService = appointmentService;
        this.userInfoService = userInfoService;
    }

    @GetMapping("/profile/jobs")
    public String showJobs(Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        UserInfo userInfo = userInfoService.findUserInfoByUsername(userEntity.getUsername());

        System.out.println(userInfo.getFullName());

        List<Appointment> appointmentList = appointmentService.listAppointmentsByEmployee(userInfo);
        if(appointmentList.isEmpty())
            System.out.println("CARALHO TA BAZIA");
        for(Appointment a : appointmentList)
            System.out.println(a);

        model.addAttribute("jobsList", appointmentList);

        return "jobs";
    }
}
