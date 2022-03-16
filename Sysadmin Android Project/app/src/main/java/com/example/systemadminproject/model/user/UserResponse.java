package com.example.systemadminproject.model.user;

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
public class UserResponse extends Pagination {
    private List<UserData> data;
}
