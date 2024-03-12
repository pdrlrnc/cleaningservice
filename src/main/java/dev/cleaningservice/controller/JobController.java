package dev.cleaningservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        List<Appointment> appointments = appointmentService.listAppointmentsByEmployee(userInfo);


        // Convert appointments to FullCalendar event format
        List<Map<String, Object>> events = appointments.stream().map(appointment -> {
            Map<String, Object> event = new HashMap<>();
            event.put("title", appointment.getClient().getFirstName());

            // Convert LocalDateTime to ISO8601 string
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            String start = appointment.getStartDate().format(formatter);
            String end = appointment.getEndDate().format(formatter);

            event.put("start", start);
            event.put("end", end);
            event.put("allDay", false);
            return event;
        }).collect(Collectors.toList());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonEvents = null;
        try {
            jsonEvents = objectMapper.writeValueAsString(events);
            System.out.println(jsonEvents);
        } catch (JsonProcessingException e) {
            System.err.println("writeValueAsString failed!");
            e.printStackTrace();
        }

        model.addAttribute("events", jsonEvents);

        return "jobs";
    }
}
