package dev.cleaningservice.service;

import dev.cleaningservice.entity.UserInfo;

public interface UserInfoService {

    public UserInfo findUserInfoById(int id);

    public void save(UserInfo userInfo);

    public UserInfo findUserInfoByUsername(String username);
}
