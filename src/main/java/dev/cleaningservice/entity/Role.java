package dev.cleaningservice.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Java class that represents role database table
 * It only has getters because all the values must be from the
 * database and should never be generated thru java code
 */
@Entity(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users = new ArrayList<>();

}
