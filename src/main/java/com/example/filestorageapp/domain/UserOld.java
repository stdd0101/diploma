package com.example.filestorageapp.domain;

import javax.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public class UserOld {
    private Long id;
    private String email;
    private String username;
    private String password;

    public UserOld() {}

    public UserOld(String email, String username, String password) {
        //super();
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public UserOld setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserOld setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserOld setPassword(String password) {
        this.password = password;
        return this;
    }
}