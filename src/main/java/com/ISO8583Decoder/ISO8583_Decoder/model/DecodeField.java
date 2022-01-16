package com.ISO8583Decoder.ISO8583_Decoder.model;

import javax.persistence.*;

@Entity
@Table(name = "decodeFields")
public class DecodeField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "decodeField", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private FieldItem field;
    private String value;

    @ManyToOne()
    @JoinColumn(name = "decodeMsg_id")
    private DecodeMsg decodeMsg;

    public DecodeField(){}

    public DecodeField(FieldItem field, String value) {
        this.field = field;
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
}
