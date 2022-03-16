package com.example.systemadminproject.model.brand;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StoreListResponse {
    private int id;
    private int brandId;
    private String brandName;
    private String name;
    private String address;
    private int approvedStatus;
}
