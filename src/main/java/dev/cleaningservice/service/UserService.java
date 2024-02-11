package dev.cleaningservice.service;

import dev.cleaningservice.dto.RegistrationDTO;
import dev.cleaningservice.entity.UserEntity;

public interface UserService {

    public void save(RegistrationDTO registrationDTO);
}
