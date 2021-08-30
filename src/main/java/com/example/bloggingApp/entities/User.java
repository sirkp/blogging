package com.example.bloggingApp.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users", indexes = {@Index(name = "uuid_index", columnList = "uuid", unique = true),
                                  @Index(name = "email_index", columnList = "email", unique = true)})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid", unique = true, nullable = false)
    private String uuid;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", unique = false, nullable = false)
    private String password;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Article> articles;

    @Column(name = "role", unique = false, nullable = false)
    @Setter(AccessLevel.NONE)
    private String role;

    public void setRole() {
        role = "USER";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if(!(obj instanceof User)) return false;

        User user = (User) obj;
        return (Objects.equals(this.id, user.id)
                && Objects.equals(this.name, user.name)
                && Objects.equals(this.role, user.role));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.email, this.uuid, this.password, this.name, this.role);
    }

    @Override
    public String toString() {
        return "Employee{\n   id: " + this.id
                + "\n   uuid: " + this.uuid 
                + "\n   name: " + this.name
                + "\n   email: " + this.email
                + "\n   role: " + this.role + "\n}";
    }
}
