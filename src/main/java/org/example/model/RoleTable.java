package org.example.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class RoleTable implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @Enumerated(EnumType.STRING)
    private Role name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return name;
    }

    public void setRole(Role name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name.toString();
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleTable roleTable = (RoleTable) o;
        return id == roleTable.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

