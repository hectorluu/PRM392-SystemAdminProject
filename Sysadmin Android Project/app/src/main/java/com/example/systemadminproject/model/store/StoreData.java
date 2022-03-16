package com.example.systemadminproject.model.store;

import com.example.systemadminproject.model.brand.BrandData;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StoreData {
    private int id;
    private int brandId;
    private String brandName;
    private BrandData brand;
    private String name;
    private String address;
    private int approvedStatus;
}
