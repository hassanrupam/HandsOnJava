package com.hassan.main.core.enumurations;

public enum StatusEnum {

    CREATED("Created"),
    CANCELLED("Cancelled"),
    IN_PROGRESS("InProgress"),
    COMPLETED("Completed"),
    ON_HOLD("OnHold");

    private String status;

    private StatusEnum(String _status){
        this.status = _status;
    }

    public String getStatus(){
        return this.status;
    }

}
