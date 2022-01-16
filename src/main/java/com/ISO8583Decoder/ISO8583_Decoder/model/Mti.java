package com.ISO8583Decoder.ISO8583_Decoder.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mtis")
public class Mti {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mti;
    private String meaning;

    @OneToMany(mappedBy = "mti", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MtiItem> mtiItemList;

    public Mti(String mti, String meaning) {
        this.mti = mti;
        this.meaning = meaning;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getMti() {
        return mti;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }
}
