package com.ISO8583Decoder.ISO8583_Decoder.services;

import com.ISO8583Decoder.ISO8583_Decoder.model.entities.Acquirer;

public interface AcquirerService {

    void save(Acquirer a);
    Acquirer findById(Integer id) throws Exception;
}
