package dev.cleaningservice.service;

import dev.cleaningservice.entity.UserInfo;
import dev.cleaningservice.validation.EmailAlreadyExistsException;
import dev.cleaningservice.validation.UsernameAlreadyExistsException;

public interface UserInfoService {

    public UserInfo findUserInfoById(int id);

    public void save(UserInfo userInfo) throws UsernameAlreadyExistsException, EmailAlreadyExistsException;

    public UserInfo findUserInfoByUsername(String username);

    public UserInfo findUserInfoByEmail(String email);
}
