package dev.cleaningservice.service;

import dev.cleaningservice.dto.ClientProfileDTO;
import dev.cleaningservice.dto.EmployeeProfileDTO;
import dev.cleaningservice.entity.UserInfo;
import dev.cleaningservice.validation.EmailAlreadyExistsException;
import dev.cleaningservice.validation.UsernameAlreadyExistsException;

public interface UserInfoService {

    public UserInfo findUserInfoById(Long id);

    public void save(EmployeeProfileDTO employeeProfileDTO) throws UsernameAlreadyExistsException, EmailAlreadyExistsException;

    public UserInfo findUserInfoByUsername(String username);

    public UserInfo findUserInfoByUserEntityId(Long id);

    public UserInfo findUserInfoByEmail(String email);

    EmployeeProfileDTO populateEmployeeProfileDTO(UserInfo userInfo);

    ClientProfileDTO populateClientProfileDTO(UserInfo userInfo);
}
