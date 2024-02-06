package dev.cleaningservice.service;

import dev.cleaningservice.entity.Role;
import java.util.List;

public interface RoleService {

    /**
     * This method finds a ROLE by ID
     * @param id - ID of the role
     * @return - role with id ID
     */
    public Role findRoleById(int id);

    /**
     * This method lists all roles
     * @return
     */
    public List<Role> findAll();




}
