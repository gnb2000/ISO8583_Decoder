package com.ISO8583Decoder.ISO8583_Decoder.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "fields")
public class Field {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int field_number;
    private String type;
    private Integer length;
    private String name;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FieldItem> fieldItemList;

    @ManyToOne()
    @JoinColumn(name = "acquirer_id")
    private Acquirer acquirer;

    public Field(){}

    public Field(int field_number, String type, Integer length, String name, Acquirer a) {
        this.field_number = field_number;
        this.type = type;
        this.length = length;
        this.name = name;
        this.acquirer = a;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getField_number() {
        return field_number;
    }

    public void setField_number(int field_number) {
        this.field_number = field_number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FieldItem> getFieldItemList() {
        return fieldItemList;
    }

    public void setFieldItemList(List<FieldItem> fieldItemList) {
        this.fieldItemList = fieldItemList;
    }

    public Acquirer getAcquirer() {
        return acquirer;
    }

    public void setAcquirer(Acquirer acquirer) {
        this.acquirer = acquirer;
    }
}
