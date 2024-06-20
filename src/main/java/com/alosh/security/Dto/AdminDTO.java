package com.alosh.security.Dto;

import lombok.Data;

@Data
public class AdminDTO {
    private int id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private double salary;
    private String role;
    private int age;

//    private List<Integer> roomid;


}
