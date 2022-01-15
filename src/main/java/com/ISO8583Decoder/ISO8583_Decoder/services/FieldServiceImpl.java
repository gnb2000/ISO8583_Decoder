package com.ISO8583Decoder.ISO8583_Decoder.services;

import com.ISO8583Decoder.ISO8583_Decoder.model.Field;
import com.ISO8583Decoder.ISO8583_Decoder.repositories.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FieldServiceImpl implements FieldService{

    @Autowired
    private FieldRepository fieldRepository;


    @Override
    public void save(Field f) {
        fieldRepository.save(f);
    }

    @Override
    public void update(Field f) {
        fieldRepository.save(f);
    }

    @Override
    public void delete(Field f) {
        fieldRepository.delete(f);
    }

    @Override
    public Field getFieldById(Integer id) throws Exception {
        try {
            Optional<Field> field = fieldRepository.findById(id);
            if (field.isPresent()){
                return field.get();
            } else {
                throw new Exception("No existe el campo con id "+ id);
            }
        } catch (Exception e){
            throw new Exception("No existe el campo con id "+ id);
        }

    }
}
