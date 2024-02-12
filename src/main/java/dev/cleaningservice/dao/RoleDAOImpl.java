package dev.cleaningservice.dao;

import dev.cleaningservice.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO{

    @Autowired
    EntityManager entityManager;

    public RoleDAOImpl (EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Role findRoleById(int id) {

        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> findAll() {

        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM role r", Role.class);
        return query.getResultList();
    }

    @Override
    public Role findByName(String name) {

        TypedQuery<Role> query = entityManager
                .createQuery("SELECT r FROM role r where  r.name = :name", Role.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
