package dev.cleaningservice.service;

import dev.cleaningservice.dao.UserEmployeeDAO;
import dev.cleaningservice.dao.UserInfoDAO;
import dev.cleaningservice.dto.ProfileDTO;
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

    @Override
    public ProfileDTO populateProfileDTO(Long userId) {

        UserInfo userInfo = findUserInfoById(userId);
        System.out.println("USERINFO: \n\n\n" + userInfo);
        UserEmployee userEmployee = userEmployeeDAO.getByUserInfoID(userId);
        System.out.println("USEREMPLOYEE: \n\n\n" + userEmployee);


        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setAddress(userInfo.getAddress());
        profileDTO.setFullName(userInfo.getFullName());

        if(userEmployee != null){
            profileDTO.setYearsOfExperience(userEmployee.getYearsOfExperience());
            profileDTO.setStartedWorking(userEmployee.getStartedWorking());
        }

        return profileDTO;
    }
}
