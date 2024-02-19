package dev.cleaningservice.dao;

import dev.cleaningservice.entity.UserEmployee;
import dev.cleaningservice.entity.UserInfo;

import java.util.List;

public interface UserEmployeeDAO {

    public void save(UserEmployee userEmployee);

    public List<UserEmployee> listEmployees();

    public List<UserEmployee> listActiveEmployees();

    public UserEmployee getById(int employee);

    public void deleteById(int id);

    public UserEmployee getByUserInfoID(Long userId);

}
