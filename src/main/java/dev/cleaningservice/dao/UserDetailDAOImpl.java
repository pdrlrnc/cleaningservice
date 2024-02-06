package dev.cleaningservice.dao;

import dev.cleaningservice.entity.UserDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDetailDAOImpl implements UserDetailDAO {

    @Autowired
    EntityManager entityManager;

    public UserDetailDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public UserDetail findUserDetailById(int id) {
        return entityManager.find(UserDetail.class, id);
    }

    @Override
    @Transactional
    public void save(UserDetail userDetail) {

        entityManager.persist(userDetail);
    }
}
