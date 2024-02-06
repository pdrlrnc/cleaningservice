package dev.cleaningservice.service;

import dev.cleaningservice.dao.RoleDAO;
import dev.cleaningservice.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO){
        this.roleDAO = roleDAO;
    }

    @Override
    public Role findRoleById(int id) {
        return roleDAO.findRoleById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleDAO.findAll();
    }


}
