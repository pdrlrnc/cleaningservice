package dev.cleaningservice.service;

import dev.cleaningservice.dao.UserDetailDAO;
import dev.cleaningservice.entity.UserDetail;
import org.springframework.stereotype.Service;


@Service
public class UserDetailServiceImpl implements UserDetailService{

    UserDetailDAO userDetailDAO;

    public UserDetailServiceImpl(UserDetailDAO userDetailDAO) {
        this.userDetailDAO = userDetailDAO;
    }

    @Override
    public UserDetail findUserDetailById(int id) {
        return userDetailDAO.findUserDetailById(id);
    }

    @Override
    public void save(UserDetail userDetail) {
        userDetailDAO.save(userDetail);
    }
}
