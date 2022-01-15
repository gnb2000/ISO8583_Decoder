package com.ISO8583Decoder.ISO8583_Decoder.controller;

import com.ISO8583Decoder.ISO8583_Decoder.model.Field;
import com.ISO8583Decoder.ISO8583_Decoder.services.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @PostMapping("/fields")
    public void createFields(){
        Field field = new Field(3,"Numeric",6,"Fixed","Processing code");
        fieldService.save(field);
    }
}
