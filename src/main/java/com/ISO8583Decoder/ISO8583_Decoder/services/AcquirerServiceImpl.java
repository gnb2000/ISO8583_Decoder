package com.ISO8583Decoder.ISO8583_Decoder.services;

import com.ISO8583Decoder.ISO8583_Decoder.model.Acquirer;
import com.ISO8583Decoder.ISO8583_Decoder.model.Field;
import com.ISO8583Decoder.ISO8583_Decoder.repositories.AcquirerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AcquirerServiceImpl implements AcquirerService{

    @Autowired
    private AcquirerRepository acquirerRepository;


    @Override
    public void save(Acquirer a) {
        acquirerRepository.save(a);
    }

    @Override
    public Acquirer findById(Integer id) throws Exception {
        try {
            Optional<Acquirer> acquirer = acquirerRepository.findById(id);
            if (acquirer.isPresent()){
                return acquirer.get();
            } else {
                throw new Exception("No existe el acquirer con "+ id);
            }
        } catch (Exception e){
            throw new Exception("No existe el acquirer con "+ id);
        }
    }
}
