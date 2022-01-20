package com.ISO8583Decoder.ISO8583_Decoder.controller;

import com.ISO8583Decoder.ISO8583_Decoder.dto.DecodeMsgDto;
import com.ISO8583Decoder.ISO8583_Decoder.model.*;
import com.ISO8583Decoder.ISO8583_Decoder.services.FieldService;
import com.ISO8583Decoder.ISO8583_Decoder.services.MtiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DecodeController {

    @Autowired
    private FieldService fieldService;

    @Autowired
    private MtiService mtiService;

    @RequestMapping("decode/{acquirer}/{message}")
    public DecodeMsgDto decode(@PathVariable String message,@PathVariable int acquirer) throws Exception {
        String originalMsg = message.replaceAll("\\s+","");

        DecodeMsg new_decode = new DecodeMsg(originalMsg);
        new_decode.decodeHeader();

        MtiItem new_mtiItem = new MtiItem(mtiService.findByMtiNumber(new_decode.getMtiFromOriginalMsg()),new_decode);
        new_decode.setMti(new_mtiItem);

        List<Integer> fieldsWithData = new_decode.getFieldsWithData();

        System.out.println();

        List<DecodeField> decode_fields = new ArrayList<>();
        for (Integer f : fieldsWithData){
            Field f_data = fieldService.getFieldByFieldNumberAndAcquirer(f,acquirer);
            DecodeField new_decodeField = new_decode.decodeFields(f_data,new_decode.getData());
            decode_fields.add(new_decodeField);
        }
        new_decode.setDecodeFields(decode_fields);
        return new_decode.toDto();
    }

    private String hexToBin(String hex){
        hex = hex.replaceAll("0", "0000");
        hex = hex.replaceAll("1", "0001");
        hex = hex.replaceAll("2", "0010");
        hex = hex.replaceAll("3", "0011");
        hex = hex.replaceAll("4", "0100");
        hex = hex.replaceAll("5", "0101");
        hex = hex.replaceAll("6", "0110");
        hex = hex.replaceAll("7", "0111");
        hex = hex.replaceAll("8", "1000");
        hex = hex.replaceAll("9", "1001");
        hex = hex.replaceAll("A", "1010");
        hex = hex.replaceAll("B", "1011");
        hex = hex.replaceAll("C", "1100");
        hex = hex.replaceAll("D", "1101");
        hex = hex.replaceAll("E", "1110");
        hex = hex.replaceAll("F", "1111");
        return hex;
    }

}
