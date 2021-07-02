package com.hassan.main.core.dto.Common;

public class CommonDropDownDTO {
    //region PRIVATE FIELDS
    private String value;
    private String text;
    private Integer valueInteger;
    //endregion

    //region CONSTRUCTORS
    public CommonDropDownDTO() {
    }

    public CommonDropDownDTO(String _value, String _text) {
        this.value = _value;
        this.text = _text;
    }

    public CommonDropDownDTO(Integer _valueInteger,String _text) {
        this.valueInteger = _valueInteger;
        this.text = _text;
    }
    //endregion

    //region GETTER & SETTER
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getValueInteger() {
        return valueInteger;
    }

    public void setValueInteger(Integer valueInteger) {
        this.valueInteger = valueInteger;
    }
    //endregion
}
