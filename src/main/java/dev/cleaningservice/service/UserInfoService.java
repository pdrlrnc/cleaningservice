package dev.cleaningservice.service;

import dev.cleaningservice.dto.ProfileDTO;
import dev.cleaningservice.entity.UserInfo;
import dev.cleaningservice.validation.EmailAlreadyExistsException;
import dev.cleaningservice.validation.UsernameAlreadyExistsException;

public interface UserInfoService {

    public UserInfo findUserInfoById(Long id);

    public void save(ProfileDTO profileDTO) throws UsernameAlreadyExistsException, EmailAlreadyExistsException;

    public UserInfo findUserInfoByUsername(String username);

    public UserInfo findUserInfoByEmail(String email);

    ProfileDTO populateProfileDTO(Long userId);
}
