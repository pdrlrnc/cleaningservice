package dev.cleaningservice.dao;

import dev.cleaningservice.entity.UserEmployee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserEmployeeDAOImpl implements UserEmployeeDAO{

    @Autowired
    private EntityManager entityManager;

    public UserEmployeeDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(UserEmployee userEmployee) {
        entityManager.persist(userEmployee);
    }

    @Override
    public List<UserEmployee> listEmployees() {

        TypedQuery<UserEmployee> query = entityManager.createQuery("FROM employee", UserEmployee.class);
        return query.getResultList();
    }

    @Override
    public UserEmployee getById(int employee) {
        return entityManager.find(UserEmployee.class, employee);
    }

    @Override
    @Transactional
    public void deleteById(int id) {

        UserEmployee employeeToDelete = getById(id);

        if(employeeToDelete != null)
            entityManager.remove(employeeToDelete);
    }
}
