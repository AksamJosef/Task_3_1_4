package ru.kata.spring.boot_security.demo.entities;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    @NotEmpty(message = "Username can't be empty")
    @Email(message = "This is not email!")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "Password can't be empty")
    private String password;

    @Column(name = "name")
    @NotEmpty(message = "Field can't be empty")
    @Size(min = 2, max = 30, message = "Size of the field could be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Field can't be empty")
    @Size(min = 2, max = 30, message = "Size of the field could be between 2 and 30 characters")
    @Column(name = "last_name")
    private String lastName;


    @Column(name = "age")
    private int age;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                            CascadeType.REFRESH, CascadeType.DETACH}
                            , fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public void addRole(Role role) {
        if (roles == null) roles = new HashSet<>();
        roles.add(role);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<String> getRoleNames() {


        return roles.stream()
        .map(role -> role.getRole().replace("ROLE_", ""))
        .collect(Collectors.toCollection(HashSet::new));
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                ", roles=" + roles +
                '}';
    }
}

