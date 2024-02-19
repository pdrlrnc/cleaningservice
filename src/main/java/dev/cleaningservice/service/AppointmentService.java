package dev.cleaningservice.service;

import dev.cleaningservice.entity.Appointment;
import dev.cleaningservice.entity.UserInfo;
import dev.cleaningservice.validation.NoneAvailableCleaners;

import java.util.List;

public interface AppointmentService {

    public void createAppointment(Appointment appointment) throws NoneAvailableCleaners;

    public List<Appointment> listAppointmentsByClient(UserInfo client);
}
