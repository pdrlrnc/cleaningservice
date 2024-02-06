package dev.cleaningservice.entity;

import jakarta.persistence.*;

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
    private int id;

    @Column(name = "name")
    private String name;

    public Role(){

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
