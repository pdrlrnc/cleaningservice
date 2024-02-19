package dev.cleaningservice.service;

import dev.cleaningservice.entity.Appointment;
import dev.cleaningservice.entity.UserInfo;

import java.util.List;

public interface AppointmentService {

    public void createAppointment(Appointment appointment);

    public List<Appointment> listAppointmentsByClient(UserInfo client);
}
