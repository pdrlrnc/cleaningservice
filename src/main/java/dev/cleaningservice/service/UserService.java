package dev.cleaningservice.service;

import dev.cleaningservice.dto.RegistrationDTO;
import dev.cleaningservice.entity.UserEntity;
import dev.cleaningservice.entity.UserInfo;

public interface UserService {

    public UserInfo save(RegistrationDTO registrationDTO);
}
