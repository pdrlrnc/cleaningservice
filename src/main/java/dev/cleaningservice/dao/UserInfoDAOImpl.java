package dev.cleaningservice.dao;

import dev.cleaningservice.entity.UserInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserInfoDAOImpl implements UserInfoDAO {

    @Autowired
    EntityManager entityManager;

    public UserInfoDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public UserInfo findUserInfoById(int id) {
        return entityManager.find(UserInfo.class, id);
    }


    @Override
    public UserInfo findUserInfoByEmail(String email) {
        TypedQuery<UserInfo> query = entityManager.createQuery(
                "SELECT u FROM user_info u WHERE u.email = :email", UserInfo.class);
        query.setParameter("email", email);
        UserInfo userInfo;
        try {
            userInfo = query.getSingleResult();
        } catch (NoResultException nre) {
            userInfo = null;
        }
        return userInfo;
    }

    @Override
    @Transactional
    public void save(UserInfo userInfo) {
        entityManager.persist(userInfo);
    }

    @Override
    public UserInfo findUserInfoByUsername(String username) {
        TypedQuery<UserInfo> query = entityManager.createQuery(
                "SELECT u FROM user_info u WHERE u.username = :username", UserInfo.class);
        query.setParameter("username", username);
        UserInfo userInfo;
        try {
            userInfo = query.getSingleResult();
        } catch (NoResultException nre) {
            userInfo = null;
        }
        return userInfo;
    }
}
