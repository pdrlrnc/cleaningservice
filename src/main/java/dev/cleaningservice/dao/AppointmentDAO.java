package dev.cleaningservice.dao;

import dev.cleaningservice.entity.Appointment;
import dev.cleaningservice.entity.UserEmployee;
import dev.cleaningservice.entity.UserInfo;

import java.util.List;

public interface AppointmentDAO {

    public List<Appointment> listAppointmentByEmployee(UserEmployee employee);

    public List<Appointment> listAppointmentByClient(UserInfo client);

    public void save(Appointment appointment);
}
