package com.example.systemadminproject.model.store;

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
public class StoreResponse extends Pagination {
    private List<StoreData> data;
}
