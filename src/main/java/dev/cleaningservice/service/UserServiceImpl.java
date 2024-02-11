package dev.cleaningservice.service;

import dev.cleaningservice.dao.UserEntityDAO;
import dev.cleaningservice.dao.UserInfoDAO;
import dev.cleaningservice.dao.RoleDAO;
import dev.cleaningservice.dto.RegistrationDTO;
import dev.cleaningservice.entity.Role;
import dev.cleaningservice.entity.UserEntity;
import dev.cleaningservice.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserEntityDAO userEntityDAO;

    private UserInfoDAO userInfoDAO;

    private RoleDAO roleDAO;

    @Autowired
    public UserServiceImpl (UserEntityDAO userEntityDAO, UserInfoDAO userInfoDAO, RoleDAO roleDAO){
        this.userEntityDAO = userEntityDAO;
        this.userInfoDAO = userInfoDAO;
        this.roleDAO = roleDAO;
    }


    @Override
    public void save(RegistrationDTO registrationDTO) {


        //creating userEntity
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(registrationDTO.getPassword());
        userEntity.setUsername(registrationDTO.getUsername());
        Role role = roleDAO.findByName("ROLE_CUSTOMER");
        userEntity.addRole(role);

        //saving userEntity
        userEntityDAO.save(userEntity);

        //creating userInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(registrationDTO.getUsername());
        userInfo.setEmail(registrationDTO.getEmail());
        userInfo.setId(userEntity.getId());
        userInfo.setFirstName(registrationDTO.getFirstName());

        //saving userInfo
        userInfoDAO.save(userInfo);

    }
}
