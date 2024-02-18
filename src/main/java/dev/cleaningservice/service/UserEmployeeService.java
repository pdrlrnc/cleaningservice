package dev.cleaningservice.service;

import dev.cleaningservice.entity.UserEmployee;

import java.util.List;

public interface UserEmployeeService {

    public List<UserEmployee> listEmployees();

    public void save(UserEmployee employee);

    UserEmployee getById(int employee);

    void confirmApplicant(int id);

    void deleteApplicant(int id);
}
