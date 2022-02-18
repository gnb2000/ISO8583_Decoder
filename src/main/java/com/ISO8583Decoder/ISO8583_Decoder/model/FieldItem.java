package com.ISO8583Decoder.ISO8583_Decoder.model;

import com.ISO8583Decoder.ISO8583_Decoder.model.entities.Field;

public class FieldItem {

    private Integer id;
    private Field field;
    private DecodeField decodeField;

    public FieldItem(){}

    public FieldItem(Field field, DecodeField decodeField) {
        this.field = field;
        this.decodeField = decodeField;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public DecodeField getDecodeField() {
        return decodeField;
    }

    public void setDecodeField(DecodeField decodeField) {
        this.decodeField = decodeField;
    }
}
