package com.example.systemadminproject.model.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserData {
    private int id;
    private String username;
    private String email;
    private String phone;
    private String name;
    private int status;
}
