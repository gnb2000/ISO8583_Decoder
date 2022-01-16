package com.ISO8583Decoder.ISO8583_Decoder.model;

import com.ISO8583Decoder.ISO8583_Decoder.dto.DecodeFieldDto;
import com.ISO8583Decoder.ISO8583_Decoder.dto.DecodeMsgDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "decodeMsgs")
public class DecodeMsg {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String length;
    private String tpdu;
    private String bitmap;

    @OneToOne(mappedBy = "decodeMsg", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private MtiItem mti;

    @OneToMany(mappedBy = "decodeMsg", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DecodeField> decodeFields;

    public DecodeMsg(){}

    public DecodeMsg(String length,String bitmap,String tpdu){
        this.length = length;
        this.bitmap = bitmap;
        this.tpdu = tpdu;
    }

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

    public String getBitmap() {
        return bitmap;
    }

    public void setBitmap(String bitmap) {
        this.bitmap = bitmap;
    }

    public DecodeMsgDto toDto(){
        List<DecodeFieldDto> decodeFieldDtos = new ArrayList<DecodeFieldDto>();
        for (DecodeField d : this.decodeFields){
            decodeFieldDtos.add(d.toDto());
        }
        return new DecodeMsgDto(this.length,this.tpdu,this.bitmap,this.mti.getMti().getMti(),decodeFieldDtos);
    }
}
