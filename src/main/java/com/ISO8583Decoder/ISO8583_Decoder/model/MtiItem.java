package com.ISO8583Decoder.ISO8583_Decoder.model;

import javax.persistence.*;

@Entity
@Table(name = "mtiItems")
public class MtiItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "mti_id")
    private Mti mti;

    @JoinColumn(name = "decodeMsg_id")
    @OneToOne(fetch = FetchType.LAZY)
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
