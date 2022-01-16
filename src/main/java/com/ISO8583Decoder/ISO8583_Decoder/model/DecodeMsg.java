package com.ISO8583Decoder.ISO8583_Decoder.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "decodeMsgs")
public class DecodeMsg {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String length;
    private String tpdu;

    @OneToOne(mappedBy = "decodeMsg", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private MtiItem mti;

    @OneToMany(mappedBy = "decodeMsg", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DecodeField> decodeFields;

    public DecodeMsg(String length, String tpdu, MtiItem mti, List<DecodeField> decodeFields) {
        this.length = length;
        this.tpdu = tpdu;
        this.mti = mti;
        this.decodeFields = decodeFields;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getTpdu() {
        return tpdu;
    }

    public void setTpdu(String tpdu) {
        this.tpdu = tpdu;
    }

    public MtiItem getMti() {
        return mti;
    }

    public void setMti(MtiItem mti) {
        this.mti = mti;
    }

    public List<DecodeField> getDecodeFields() {
        return decodeFields;
    }

    public void setDecodeFields(List<DecodeField> decodeFields) {
        this.decodeFields = decodeFields;
    }
}
