package com.ISO8583Decoder.ISO8583_Decoder.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "acquirers")
public class Acquirer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "acquirer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Field> fields;

    public Acquirer(){}

    public Acquirer(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
