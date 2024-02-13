package dev.cleaningservice.dao;

import dev.cleaningservice.entity.UserEntity;

public interface UserEntityDAO {

    public UserEntity findByUsername(String username);

    public void save(UserEntity userEntity);

}
