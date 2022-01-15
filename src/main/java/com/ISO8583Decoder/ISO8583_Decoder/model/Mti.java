package com.ISO8583Decoder.ISO8583_Decoder.model;

import javax.persistence.*;

@Entity
@Table(name = "mtis")
public class Mti {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer mti;
    private String meaning;
    private String usage;

    public Mti(Integer id, Integer mti, String meaning, String usage) {
        this.id = id;
        this.mti = mti;
        this.meaning = meaning;
        this.usage = usage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMti() {
        return mti;
    }

    public void setMti(Integer mti) {
        this.mti = mti;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }
}
