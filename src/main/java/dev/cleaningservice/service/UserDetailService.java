package dev.cleaningservice.service;

import dev.cleaningservice.entity.UserDetail;

public interface UserDetailService {

    public UserDetail findUserDetailById(int id);

    public void save(UserDetail userDetail);
}
