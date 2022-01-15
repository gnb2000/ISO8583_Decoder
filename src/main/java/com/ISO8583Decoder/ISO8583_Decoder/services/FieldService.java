package com.ISO8583Decoder.ISO8583_Decoder.services;

import com.ISO8583Decoder.ISO8583_Decoder.model.Field;

public interface FieldService {

    void save(Field f);
    void update(Field f);
    void delete(Field f);
    Field getFieldById(Integer id) throws Exception;
}
