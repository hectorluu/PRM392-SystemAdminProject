package com.example.systemadminproject.model.brand;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BrandData {
    private int id;
    private String name;
    private int status;
    private List<UserListResponse> userList;
    private List<StoreListResponse> storeList;
}
