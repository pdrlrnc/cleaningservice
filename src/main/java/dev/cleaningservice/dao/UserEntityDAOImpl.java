package dev.cleaningservice.dao;

import dev.cleaningservice.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserEntityDAOImpl implements UserEntityDAO {

    @Autowired
    EntityManager entityManager;

    public UserEntityDAOImpl (EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public UserEntity findByUsername(String username) {

        TypedQuery<UserEntity> query = entityManager.createQuery(
                "SELECT u FROM users u WHERE u.username = :username", UserEntity.class);
        query.setParameter("username", username);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void save(UserEntity userEntity) {
        entityManager.persist(userEntity);
    }

}

