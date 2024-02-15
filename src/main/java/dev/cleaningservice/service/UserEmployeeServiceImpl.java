package dev.cleaningservice.service;

import dev.cleaningservice.dao.UserEmployeeDAO;
import dev.cleaningservice.entity.UserEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
