package dev.cleaningservice.service;

import dev.cleaningservice.dao.UserInfoDAO;
import dev.cleaningservice.entity.UserInfo;
import org.springframework.stereotype.Service;


@Service
public class UserInfoServiceImpl implements UserInfoService {

    UserInfoDAO userInfoDAO;

    public UserInfoServiceImpl(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }

    @Override
    public UserInfo findUserInfoById(int id) {
        return userInfoDAO.findUserInfoById(id);
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfoDAO.save(userInfo);
    }

    @Override
    public UserInfo findUserInfoByUsername(String username) { return userInfoDAO.findUserInfoByUsername(username);  }

    @Override
    public UserInfo findUserInfoByEmail(String email) {
        return userInfoDAO.findUserInfoByEmail(email);
    }

}
