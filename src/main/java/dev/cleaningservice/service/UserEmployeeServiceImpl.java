package dev.cleaningservice.service;

import dev.cleaningservice.dao.UserEmployeeDAO;
import dev.cleaningservice.dto.ProfileDTO;
import dev.cleaningservice.entity.UserEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserEmployeeServiceImpl implements UserEmployeeService {

    private UserEmployeeDAO userEmployeeDAO;

    @Autowired
    public UserEmployeeServiceImpl (UserEmployeeDAO userEmployeeDAO){
        this.userEmployeeDAO = userEmployeeDAO;
    }

    @Override
    public List<UserEmployee> listEmployees() {
        return userEmployeeDAO.listEmployees();
    }

    @Override
    @Transactional
    public void save(UserEmployee employee) {
        userEmployeeDAO.save(employee);
    }

    @Override
    public UserEmployee getById(int employee) {
        return userEmployeeDAO.getById(employee);
    }

    @Override
    public void confirmApplicant(int id) {
        UserEmployee userEmployee = getById(id);

        userEmployee.setActive(true);
        userEmployee.setStartedWorking(java.sql.Date.valueOf(LocalDate.now()));

        save(userEmployee);
    }

    @Override
    @Transactional
    public void deleteApplicant(int id) {
        userEmployeeDAO.deleteById(id);
    }

}
