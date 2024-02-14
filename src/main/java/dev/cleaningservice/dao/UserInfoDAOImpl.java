package dev.cleaningservice.dao;

import dev.cleaningservice.entity.UserEntity;
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

    @Autowired
    UserEntityDAO userEntityDAO;

    public UserInfoDAOImpl(EntityManager entityManager, UserEntityDAO userEntityDAO) {
        this.entityManager = entityManager;
        this.userEntityDAO = userEntityDAO;
    }

    @Override
    public UserInfo findUserInfoById(Long id) {
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
        entityManager.merge(userInfo);

    }

    @Override
    public UserInfo findUserInfoByUsername(String username) {
        TypedQuery<UserEntity> query = entityManager.createQuery(
                "SELECT u FROM users u WHERE u.username = :username", UserEntity.class);
        query.setParameter("username", username);
        UserEntity userEntity;
        try {
            userEntity = query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }


        TypedQuery<UserInfo> query2 = entityManager.createQuery(
                "SELECT u FROM user_info u WHERE u.userEntity = :user_entity", UserInfo.class);
        query2.setParameter("user_entity", userEntity);
        UserInfo userInfo;
        try {
            userInfo = query2.getSingleResult();
        } catch (NoResultException nre) {
            userInfo = null;
        }
        return userInfo;
    }
}
