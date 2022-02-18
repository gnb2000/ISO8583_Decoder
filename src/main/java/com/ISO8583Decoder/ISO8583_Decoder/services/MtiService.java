package com.ISO8583Decoder.ISO8583_Decoder.services;

import com.ISO8583Decoder.ISO8583_Decoder.model.entities.Mti;

public interface MtiService {

    void save(Mti m);
    Mti findByMtiNumber(String mtiNumber);
}
