package com.ISO8583Decoder.ISO8583_Decoder.model;

import com.ISO8583Decoder.ISO8583_Decoder.dto.DecodeFieldDto;
import com.ISO8583Decoder.ISO8583_Decoder.dto.DecodeMsgDto;

import javax.persistence.*;

public class DecodeField {

    private Integer id;
    private FieldItem field;
    private String value;
    private DecodeMsg decodeMsg;

    public DecodeField(){}

    public DecodeField(DecodeMsg decodeMsg, String value) {
        this.decodeMsg = decodeMsg;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FieldItem getField() {
        return field;
    }

    public void setField(FieldItem field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DecodeMsg getDecodeMsg() {
        return decodeMsg;
    }

    public void setDecodeMsg(DecodeMsg decodeMsg) {
        this.decodeMsg = decodeMsg;
    }

    public DecodeFieldDto toDto(){
        return new DecodeFieldDto(this.getField().getField().getField_number(),this.getField().getField().getName(),this.value);
    }
}
