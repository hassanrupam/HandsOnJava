package com.hassan.main.core.enumurations;

/**
 * This Enum will be Used for Maintaining
 * the status for  Data validation
 *
 * @Author: Hassan Sakib Afrin
 * @Created: 02-07-2021 00.15 PM
 */
public enum DataValidationEnum {

    VALID_STATUS(true),
    INVALID_STATUS(false);

    private final Boolean status;

    private DataValidationEnum(Boolean _status){
        this.status = _status;
    }

    public Boolean status(){
        return this.status;
    }

}
