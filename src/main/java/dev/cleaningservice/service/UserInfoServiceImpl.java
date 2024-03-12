package dev.cleaningservice.service;

import dev.cleaningservice.dao.UserEmployeeDAO;
import dev.cleaningservice.dao.UserInfoDAO;
import dev.cleaningservice.dto.ClientProfileDTO;
import dev.cleaningservice.dto.EmployeeProfileDTO;
import dev.cleaningservice.entity.UserEmployee;
import dev.cleaningservice.entity.UserInfo;
import dev.cleaningservice.validation.EmailAlreadyExistsException;
import dev.cleaningservice.validation.UsernameAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserInfoServiceImpl implements UserInfoService {

    UserInfoDAO userInfoDAO;

    UserEmployeeDAO userEmployeeDAO;

    public UserInfoServiceImpl(UserInfoDAO userInfoDAO, UserEmployeeDAO userEmployeeDAO) {
        this.userInfoDAO = userInfoDAO;
        this.userEmployeeDAO = userEmployeeDAO;
    }

    @Override
    public UserInfo findUserInfoById(Long id) {
        return userInfoDAO.findUserInfoById(id);
    }

    @Override
    public void save(EmployeeProfileDTO employeeProfileDTO) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        UserInfo userInfo = userInfoDAO.findUserInfoById(employeeProfileDTO.getUserInfoId());

        UserInfo checkUsername = findUserInfoByUsername(employeeProfileDTO.getUsername());
        UserInfo checkEmail = findUserInfoByEmail(employeeProfileDTO.getEmail());

        if(checkUsername != null)
            if(!Objects.equals(checkUsername.getId(), userInfo.getId()))
                throw new UsernameAlreadyExistsException("that username is already taken!");

        userInfo.setUsername(employeeProfileDTO.getUsername());


        if(checkEmail != null)
            if(!Objects.equals(checkEmail.getId(), userInfo.getId()))
                throw new EmailAlreadyExistsException("that email is already taken!");

        userInfo.setEmail(employeeProfileDTO.getEmail());

        userInfo.setFirstName(employeeProfileDTO.getFirstName());
        userInfo.setFullName(employeeProfileDTO.getFullName());
        userInfo.setDateOfBirth(employeeProfileDTO.getDateOfBirth());
        userInfo.setPhoneNumber(employeeProfileDTO.getPhoneNumber());
        userInfo.setAddress(employeeProfileDTO.getAddress());

        userInfoDAO.save(userInfo);
    }

    @Override
    public UserInfo findUserInfoByUsername(String username) { return userInfoDAO.findUserInfoByUsername(username);  }

    @Override
    public UserInfo findUserInfoByUserEntityId(Long id) {
        return userInfoDAO.findUserInfoByUserEntityId(id);
    }

    @Override
    public UserInfo findUserInfoByEmail(String email) {
        return userInfoDAO.findUserInfoByEmail(email);
    }

    @Override
    public EmployeeProfileDTO populateEmployeeProfileDTO(UserInfo userInfo) {

        UserEmployee userEmployee = userEmployeeDAO.getByUserInfoID(userInfo.getId());

        EmployeeProfileDTO employeeProfileDTO = new EmployeeProfileDTO();
        employeeProfileDTO.setAddress(userInfo.getAddress());
        employeeProfileDTO.setFullName(userInfo.getFullName());

        if(userEmployee != null){
            employeeProfileDTO.setYearsOfExperience(userEmployee.getYearsOfExperience());
            employeeProfileDTO.setStartedWorking(userEmployee.getStartedWorking());
        }

        return employeeProfileDTO;
    }

    @Override
    public ClientProfileDTO populateClientProfileDTO(UserInfo userInfo) {

        ClientProfileDTO clientProfileDTO = new ClientProfileDTO();

        clientProfileDTO.setName(userInfo.getFirstName());
        clientProfileDTO.setAddress(userInfo.getAddress());
        clientProfileDTO.setPhoneNumber(userInfo.getPhoneNumber());

        return clientProfileDTO;
    }
}
