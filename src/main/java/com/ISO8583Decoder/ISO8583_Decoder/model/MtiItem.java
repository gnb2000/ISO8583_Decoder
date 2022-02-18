package com.ISO8583Decoder.ISO8583_Decoder.model;

import com.ISO8583Decoder.ISO8583_Decoder.model.entities.Mti;

public class MtiItem {

    private Integer id;
    private Mti mti;
    private DecodeMsg decodeMsg;

    public MtiItem(){}

    public MtiItem(Mti mti, DecodeMsg decodeMsg) {
        this.mti = mti;
        this.decodeMsg = decodeMsg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Mti getMti() {
        return mti;
    }

    public void setMti(Mti mti) {
        this.mti = mti;
    }

    public DecodeMsg getDecodeMsg() {
        return decodeMsg;
    }

    public void setDecodeMsg(DecodeMsg decodeMsg) {
        this.decodeMsg = decodeMsg;
    }
}
