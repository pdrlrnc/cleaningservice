package dev.cleaningservice.dao;

import dev.cleaningservice.entity.UserInfo;


public interface UserInfoDAO {

    public UserInfo findUserInfoById(int id);

    public void save(UserInfo userInfo);

    public UserInfo findUserInfoByUsername(String username);


}
