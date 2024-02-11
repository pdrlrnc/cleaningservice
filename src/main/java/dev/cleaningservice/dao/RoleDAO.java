package dev.cleaningservice.dao;

import dev.cleaningservice.entity.Role;

import java.util.List;

public interface RoleDAO {

    public Role findRoleById(int id);

    public List<Role> findAll();

    public Role findByName(String name);

}
