package dev.cleaningservice.service;

import dev.cleaningservice.dao.UserInfoDAO;
import dev.cleaningservice.entity.UserInfo;
import dev.cleaningservice.validation.EmailAlreadyExistsException;
import dev.cleaningservice.validation.UsernameAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.Objects;


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
    public void save(UserInfo userInfo) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        UserInfo checkUsername = findUserInfoByUsername(userInfo.getUsername());
        UserInfo checkEmail = findUserInfoByEmail(userInfo.getEmail());

        if(checkUsername != null)
        if(!Objects.equals(checkUsername.getId(), userInfo.getId()))
                throw new UsernameAlreadyExistsException("that username is already taken!");

        if(checkEmail != null)
        if(!Objects.equals(checkEmail.getId(), userInfo.getId()))
                throw new EmailAlreadyExistsException("that email is already used!");

        System.out.println(userInfo);

        userInfoDAO.save(userInfo);
    }

    @Override
    public UserInfo findUserInfoByUsername(String username) { return userInfoDAO.findUserInfoByUsername(username);  }

    @Override
    public UserInfo findUserInfoByEmail(String email) {
        return userInfoDAO.findUserInfoByEmail(email);
    }

}
