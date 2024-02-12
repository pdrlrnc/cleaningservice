package dev.cleaningservice.service;

import dev.cleaningservice.dao.UserEntityDAO;
import dev.cleaningservice.dao.UserInfoDAO;
import dev.cleaningservice.dao.RoleDAO;
import dev.cleaningservice.dto.RegistrationDTO;
import dev.cleaningservice.entity.Role;
import dev.cleaningservice.entity.UserEntity;
import dev.cleaningservice.entity.UserInfo;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserEntityDAO userEntityDAO;

    private UserInfoDAO userInfoDAO;

    private RoleDAO roleDAO;

    private UserInfoService userInfoService;

    @Autowired
    public UserServiceImpl (UserEntityDAO userEntityDAO, UserInfoDAO userInfoDAO,
                            RoleDAO roleDAO, UserInfoService userInfoService){
        this.userEntityDAO = userEntityDAO;
        this.userInfoDAO = userInfoDAO;
        this.roleDAO = roleDAO;
        this.userInfoService = userInfoService;
    }


    @Override
    @Transactional
    public UserInfo save(RegistrationDTO registrationDTO) {


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
        userInfo.setFirstName(registrationDTO.getFirstName());

        System.out.println("\n\n\n\n" + userInfo + "\n\n\n\n");

        //saving userInfo
        userInfoDAO.save(userInfo);
        return userInfo;

    }
}
