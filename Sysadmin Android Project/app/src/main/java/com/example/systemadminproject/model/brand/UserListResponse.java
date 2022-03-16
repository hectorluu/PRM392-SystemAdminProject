package com.example.systemadminproject.model.brand;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserListResponse {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String username;
    public int status;
}
