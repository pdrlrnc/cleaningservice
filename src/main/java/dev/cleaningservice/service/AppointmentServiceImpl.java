package dev.cleaningservice.service;

import dev.cleaningservice.dao.AppointmentDAO;
import dev.cleaningservice.entity.Appointment;
import dev.cleaningservice.entity.UserEmployee;
import dev.cleaningservice.entity.UserEntity;
import dev.cleaningservice.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    private UserEmployeeService userEmployeeService;
    private AppointmentDAO appointmentDAO;

    @Autowired
    public AppointmentServiceImpl(UserEmployeeService userEmployeeService, AppointmentDAO appointmentDAO){
        this.userEmployeeService = userEmployeeService;
        this.appointmentDAO = appointmentDAO;
    }



    @Override
    @Transactional
    public void createAppointment(Appointment appointment) {

        List<UserEmployee> listEmployees = userEmployeeService.listEmployees();

        do {
            Random random = new Random();
            int randomIndex = random.nextInt(listEmployees.size());
            listEmployees.remove(randomIndex);
            UserEmployee userEmployee = listEmployees.get(randomIndex);
            appointment.setEmployee(userEmployee);
        } while (hasAppointment(appointment));

        appointmentDAO.save(appointment);
    }

    @Override
    public List<Appointment> listAppointmentsByClient(UserInfo client) {
        return appointmentDAO.listAppointmentByClient(client);
    }

    private boolean hasAppointment(Appointment appointment){

        List<Appointment> appointmentList = appointmentDAO.listAppointmentByEmployee(appointment.getEmployee());

        for(Appointment appointmentEmployee : appointmentList){
            if(appointmentEmployee.isSameTimeFrame(appointment)){
                return true;
            }
        }
        return false;
    }

}
