package com.ISO8583Decoder.ISO8583_Decoder.controller;

import com.ISO8583Decoder.ISO8583_Decoder.model.Mti;
import com.ISO8583Decoder.ISO8583_Decoder.services.MtiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MtiController {

    @Autowired
    private MtiService mtiService;


    @PostMapping("/mtis")
    public void createMtis(){
        List<Mti> mtis = new ArrayList<Mti>();
        Mti m1 = new Mti("0100","Requerimiento de autorización");
        mtis.add(m1);
        Mti m2 = new Mti("0120","Aviso de Autorización");
        mtis.add(m2);
        Mti m3 = new Mti("0121","Aviso de Autorización");
        mtis.add(m3);
        Mti m4 = new Mti("0200","Requerimiento Financiero del Comprador");
        mtis.add(m4);
        Mti m5 = new Mti("0210","Respuesta al Requerimiento Financiero del Comprador");
        mtis.add(m5);
        Mti m6 = new Mti("0220","Respuesta al Requerimiento Financiero del Comprador");
        mtis.add(m6);
        Mti m7 = new Mti("0221","Aviso Financiero del Comprador Repetición");
        mtis.add(m7);
        Mti m8 = new Mti("0230","Respuesta al Aviso Financiero del Comprador");
        mtis.add(m8);
        Mti m9 = new Mti("0400","Requerimiento de Reverso del Comprador");
        mtis.add(m9);
        Mti m10 = new Mti("0420","Aviso de Reverso del Comprador");
        mtis.add(m10);
        Mti m11 = new Mti("0421","Aviso de Reverso del Comprador Repetición");
        mtis.add(m11);
        Mti m12 = new Mti("0430","Respuesta del Aviso de Reverso del Comprador");
        mtis.add(m12);
        Mti m13 = new Mti("0800","Requerimiento de Manejo de Red");
        mtis.add(m13);
        Mti m14 = new Mti("0820","Aviso de Manejo de Red");
        mtis.add(m14);

        for (Mti m : mtis){
            mtiService.save(m);
        }
    }
}
