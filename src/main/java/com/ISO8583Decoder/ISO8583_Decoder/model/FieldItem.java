package com.ISO8583Decoder.ISO8583_Decoder.model;

import javax.persistence.*;

@Entity
@Table(name = "fieldItems")
public class FieldItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "field_id")
    private Field field;

    @JoinColumn(name = "decodeField_id")
    @OneToOne(fetch = FetchType.LAZY)
    private DecodeField decodeField;

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
