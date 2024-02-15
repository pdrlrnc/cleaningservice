package dev.cleaningservice.service.security;

import dev.cleaningservice.dto.EmployeeDTO;
import dev.cleaningservice.dto.RegistrationDTO;
import dev.cleaningservice.entity.UserEntity;
import dev.cleaningservice.entity.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserSecService extends UserDetailsService {

    public UserEntity findByUseName(String username);

    public void save(RegistrationDTO registrationDTO);

    public void save(EmployeeDTO employeeDTO);


}
