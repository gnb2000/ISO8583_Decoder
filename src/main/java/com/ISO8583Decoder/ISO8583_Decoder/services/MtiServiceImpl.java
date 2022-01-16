package com.ISO8583Decoder.ISO8583_Decoder.services;

import com.ISO8583Decoder.ISO8583_Decoder.model.Mti;
import com.ISO8583Decoder.ISO8583_Decoder.repositories.MtiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MtiServiceImpl implements MtiService{

    @Autowired
    private MtiRepository mtiRepository;

    @Override
    public void save(Mti m) {
        mtiRepository.save(m);
    }

    @Override
    public Mti findByMtiNumber(String mtiNumber) {
        return mtiRepository.findByMtiNumber(mtiNumber);
    }
}
