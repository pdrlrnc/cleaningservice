package dev.cleaningservice.dao;

import dev.cleaningservice.entity.UserDetail;


public interface UserDetailDAO {

    public UserDetail findUserDetailById(int id);

    public void save(UserDetail userDetail);


}
