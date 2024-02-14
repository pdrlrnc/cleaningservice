package dev.cleaningservice.service;

import dev.cleaningservice.dao.UserInfoDAO;
import dev.cleaningservice.dto.ProfileDTO;
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
    public UserInfo findUserInfoById(Long id) {
        return userInfoDAO.findUserInfoById(id);
    }

    @Override
    public void save(ProfileDTO profileDTO) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        UserInfo userInfo = userInfoDAO.findUserInfoById(profileDTO.getUserInfoId());

        UserInfo checkUsername = findUserInfoByUsername(profileDTO.getUsername());
        UserInfo checkEmail = findUserInfoByEmail(profileDTO.getEmail());

        if(checkUsername != null)
            if(!Objects.equals(checkUsername.getId(), userInfo.getId()))
                throw new UsernameAlreadyExistsException("that username is already taken!");

        userInfo.setUsername(profileDTO.getUsername());


        if(checkEmail != null)
            if(!Objects.equals(checkEmail.getId(), userInfo.getId()))
                throw new EmailAlreadyExistsException("that email is already taken!");

        userInfo.setEmail(profileDTO.getEmail());

        userInfo.setFirstName(profileDTO.getFirstName());
        userInfo.setFullName(profileDTO.getFullName());
        userInfo.setDateOfBirth(profileDTO.getDateOfBirth());
        userInfo.setPhoneNumber(profileDTO.getPhoneNumber());
        userInfo.setAddress(profileDTO.getAddress());

        userInfoDAO.save(userInfo);
    }

    @Override
    public UserInfo findUserInfoByUsername(String username) { return userInfoDAO.findUserInfoByUsername(username);  }

    @Override
    public UserInfo findUserInfoByEmail(String email) {
        return userInfoDAO.findUserInfoByEmail(email);
    }

}
