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


}
