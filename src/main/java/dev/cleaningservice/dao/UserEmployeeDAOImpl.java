package dev.cleaningservice.dao;

import dev.cleaningservice.entity.UserEmployee;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
