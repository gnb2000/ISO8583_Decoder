package com.ISO8583Decoder.ISO8583_Decoder.dto;

public class DecodeFieldDto {

    private int field_number;
    private String name;
    private String value;

    public DecodeFieldDto(int field_number, String name, String value) {
        this.field_number = field_number;
        this.name = name;
        this.value = value;
    }

    public int getField_number() {
        return field_number;
    }

    public void setField_number(int field_number) {
        this.field_number = field_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
