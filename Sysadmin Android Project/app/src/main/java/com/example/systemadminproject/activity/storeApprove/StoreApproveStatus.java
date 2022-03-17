package com.example.systemadminproject.activity.storeApprove;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreApproveStatus {
    private int status;

    public StoreApproveStatus(int status) {
        this.status = status;
    }
}
