package com.hassan.main.core.utility;

public class CustomServerResponse {

    //region PRIVATE FIELDS
    private Boolean status;
    private String message;
    private Object dto;
    private Object savedIdentity;
    //endregion

    //region CONSTRUCTORS
    public CustomServerResponse() {
    }

    public CustomServerResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }
    //endregion

    //region GETTER & SETTER
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDto() {
        return dto;
    }

    public void setDto(Object dto) {
        this.dto = dto;
    }

    public Object getSavedIdentity() {
        return savedIdentity;
    }

    public void setSavedIdentity(Object savedIdentity) {
        this.savedIdentity = savedIdentity;
    }
    //endregion
}
