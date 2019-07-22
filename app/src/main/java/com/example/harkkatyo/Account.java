package com.example.harkkatyo;

import java.io.Serializable;

public class Account implements Serializable {
    private Integer bankid;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String age;

    public Account(Integer id, String user, String passw, String firstn, String lastn, String ag){
        bankid = id;
        username = user;
        password = passw;
        firstname = firstn;
        lastname = lastn;
        age = ag;

    }

    public Integer getBankid() {
        return bankid;
    }

    public String getUsername() {
        return username;
    }

    public String getAge() {
        return age;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }
}
