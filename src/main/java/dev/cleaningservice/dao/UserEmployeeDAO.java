package dev.cleaningservice.dao;

import dev.cleaningservice.entity.UserEmployee;

import java.util.List;

public interface UserEmployeeDAO {

    public void save(UserEmployee userEmployee);

    public List<UserEmployee> listEmployees();
}