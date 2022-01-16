package com.ISO8583Decoder.ISO8583_Decoder.dto;

import java.util.List;

public class DecodeMsgDto {

    private String originalMsg;
    private String length;
    private String tpdu;
    private String mti;
    private String bitmap;
    private List<DecodeFieldDto> decode_fields;

    public DecodeMsgDto(String originalMsg, String length, String tpdu,String bitmap, String mti, List<DecodeFieldDto> decode_fields) {
        this.originalMsg = originalMsg;
        this.length = length;
        this.tpdu = tpdu;
        this.mti = mti;
        this.bitmap = bitmap;
        this.decode_fields = decode_fields;
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

    public String getMti() {
        return mti;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }


    public String getBitmap() {
        return bitmap;
    }

    public void setBitmap(String bitmap) {
        this.bitmap = bitmap;
    }

    public List<DecodeFieldDto> getDecode_fields() {
        return decode_fields;
    }

    public void setDecode_fields(List<DecodeFieldDto> decode_fields) {
        this.decode_fields = decode_fields;
    }

    public String getOriginalMsg() {
        return originalMsg;
    }

    public void setOriginalMsg(String originalMsg) {
        this.originalMsg = originalMsg;
    }
}
