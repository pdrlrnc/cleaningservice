package dev.cleaningservice.service;

import dev.cleaningservice.dao.AppointmentDAO;
import dev.cleaningservice.entity.Appointment;
import dev.cleaningservice.entity.UserEmployee;
import dev.cleaningservice.entity.UserEntity;
import dev.cleaningservice.entity.UserInfo;
import dev.cleaningservice.validation.NoneAvailableCleaners;
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
    public void createAppointment(Appointment appointment) throws NoneAvailableCleaners {

        List<UserEmployee> listEmployees = userEmployeeService.listActiveEmployees();

        do {
            Random random = new Random();
            int randomIndex = random.nextInt(listEmployees.size());
            UserEmployee userEmployee = listEmployees.get(randomIndex);
            listEmployees.remove(randomIndex);
            appointment.setEmployee(userEmployee);
        } while (hasAppointment(appointment) && !listEmployees.isEmpty());

        if(listEmployees.isEmpty()){
            throw new NoneAvailableCleaners("There are no cleaners available for that date and time");
        }
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
