package dev.cleaningservice.dao;

import dev.cleaningservice.entity.Appointment;
import dev.cleaningservice.entity.UserEmployee;
import dev.cleaningservice.entity.UserInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppointmentDAOImpl implements AppointmentDAO{

    @Autowired
    private EntityManager entityManager;

    public AppointmentDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Appointment> listAppointmentByEmployee(UserEmployee employee) {

        TypedQuery<Appointment> query = entityManager.createQuery(
                "SELECT a FROM Appointment a WHERE a.employee = :employee",
                Appointment.class
        );
        query.setParameter("employee", employee);
        return query.getResultList();
    }

    @Override
    public List<Appointment> listAppointmentByClient(UserInfo client) {

        TypedQuery<Appointment> query = entityManager.createQuery(
                "SELECT a FROM Appointment a WHERE a.client = :client",
                Appointment.class
        );
        query.setParameter("client", client);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(Appointment appointment) {
        entityManager.persist(appointment);
    }
}
