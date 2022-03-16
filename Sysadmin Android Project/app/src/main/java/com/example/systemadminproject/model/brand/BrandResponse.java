package com.example.systemadminproject.model.brand;

import com.example.systemadminproject.model.common.Pagination;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
public class BrandResponse extends Pagination {
    private List<BrandData> data;
}
