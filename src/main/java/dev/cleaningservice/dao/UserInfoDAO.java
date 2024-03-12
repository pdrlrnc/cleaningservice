package dev.cleaningservice.dao;

import dev.cleaningservice.entity.UserInfo;


public interface UserInfoDAO {

    public UserInfo findUserInfoById(Long id);

    public void save(UserInfo userInfo);

    public UserInfo findUserInfoByUsername(String username);


    public UserInfo findUserInfoByEmail(String email);

    public UserInfo findUserInfoByUserEntityId(Long id);

}
