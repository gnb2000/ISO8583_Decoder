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

    @RequestMapping("decode/{message}")
    public DecodeMsgDto decode(@PathVariable String message) throws Exception {
        String originalMsg = message.replaceAll("\\s+","");
        String bitmap = "";
        String data = "";
        String tpdu = "";
        String msg_length = "";
        String mti = "";
        List<DecodeField> decode_fields = new ArrayList<DecodeField>();
        if (originalMsg.substring(0,2).compareTo("60") == 0){
            //Without length at the beginning
            System.out.println("TPDU: "+originalMsg.substring(0,10));
            tpdu = originalMsg.substring(0,10);

            System.out.println("MTI: "+originalMsg.substring(10,14));
            mti = originalMsg.substring(10,14);

            bitmap = originalMsg.substring(14,30);
            data = originalMsg.substring(30,originalMsg.length());
        } else {
            //With length at the beginning
            System.out.println("Length: "+originalMsg.substring(0,4));
            System.out.println("TPDU: "+originalMsg.substring(4,14));
            System.out.println("MTI: "+originalMsg.substring(14,18));

            msg_length = originalMsg.substring(0,4);
            tpdu = originalMsg.substring(4,14);
            mti = originalMsg.substring(14,18);
            bitmap = originalMsg.substring(18,34);
            data = originalMsg.substring(34,originalMsg.length());

        }

        DecodeMsg new_decode = new DecodeMsg(msg_length,bitmap,tpdu);
        MtiItem new_mtiItem = new MtiItem(mtiService.findByMtiNumber(mti),new_decode);
        new_decode.setMti(new_mtiItem);

        //Convert bitmap (Hexa) to Binary
        System.out.println("BITMAP: "+bitmap);
        String bitmapInBinary = this.convertBitmapToBinary(bitmap);

        //Fields with data
        System.out.print("Fields with data: ");
        List<Integer> field_data = this.getFieldsWithData(bitmapInBinary);

        System.out.println();

        for (Integer f : field_data){
            Field f_data = fieldService.getFieldByFieldNumber(f);
            boolean isAscii = this.isAscii(f_data.getField_number());

            String decoded_data = "";
            if (f_data.getType().substring(0,3).compareTo("LLL") == 0){
                //LLLVAR
                System.out.print("Field "+f_data.getField_number() + " -- " + f_data.getName() + " -- ");

                //Primeros cuatro digitos indica la longitud
                int longitud = Integer.valueOf(data.substring(0,4));
                int incremento = 1;
                if (isAscii) {
                    longitud = longitud * 2;
                    incremento = 0;
                }

                if (Integer.compare(longitud,f_data.getLength()) < 0 || Integer.compare(longitud,f_data.getLength()) == 0) {
                    if (longitud % 2 != 0){
                        //Hay que agregar uno mas que es el relleno
                        decoded_data = data.substring(4,4 + longitud  + incremento);
                    } else {
                        decoded_data = data.substring(4,4 + longitud);
                    }
                    data = data.substring(longitud + 4 + incremento);

                    //System.out.println(decoded_data);

                } else {
                    throw new Exception("La longitud excede la longitud maxima del campo ( "+f_data.getLength()+" )");
                }
            } else if (f_data.getType().substring(0,2).compareTo("LL") == 0){
                //LLVAR

                System.out.print("Field "+f_data.getField_number() + " -- " + f_data.getName() + " -- ");

                //Primeros dos digitos indica la longitud
                int longitud = Integer.valueOf(data.substring(0,2));
                int incremento = 1;
                if (isAscii) {
                    longitud = longitud * 2;
                    incremento = 0;
                }
                if (Integer.compare(longitud,f_data.getLength()) < 0 || Integer.compare(longitud,f_data.getLength()) == 0) {
                    if (longitud % 2 != 0){
                        //Hay que agregar uno mas que es el relleno
                        decoded_data = data.substring(2,2 + longitud  + incremento);
                    } else {
                        decoded_data = data.substring(2,2 + longitud);
                    }
                    data = data.substring(longitud + 2 + incremento);

                    //                    System.out.println(decoded_data);

                } else {
                    throw new Exception("La longitud excede la longitud maxima del campo ( "+f_data.getLength()+" )");
                }

            } else {
                //FIXED
                int longitud = f_data.getLength();
                int incremento = 1;
                if (isAscii) {
                    longitud = longitud * 2;
                    incremento = 0;
                }

                if (f_data.getLength() % 2 != 0){
                    decoded_data = data.substring(0,longitud + incremento);
                    data = data.substring(longitud + incremento);
                } else {
                    decoded_data = data.substring(0,longitud);
                    data = data.substring(longitud);
                }
                System.out.print("Field "+f_data.getField_number() + " -- " + f_data.getName() + " -- ");
               // System.out.println(decoded_data);

            }

            DecodeField new_decodeField = new DecodeField(new_decode ,decoded_data);
            FieldItem fieldItem = new FieldItem(f_data,new_decodeField);
            new_decodeField.setField(fieldItem);
            decode_fields.add(new_decodeField);
            System.out.println(decoded_data);

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

    private String convertBitmapToBinary(String bitmap){
        String bitmapInBinary = "";
        for(int i = 0; i < bitmap.length() ; i++){
            //System.out.println("Hex "+bitmap.charAt(i) + "-- Bin: "+this.hexToBin(String.valueOf(bitmap.charAt(i))));
            bitmapInBinary += this.hexToBin(String.valueOf(bitmap.charAt(i)));
        }
        return bitmapInBinary;
    }

    private List<Integer> getFieldsWithData(String bitmapInBinary){
        List<Integer> field_data = new ArrayList<Integer>();
        for (int i = 0 ; i < bitmapInBinary.length() ; i++){
            if (bitmapInBinary.charAt(i) == '1'){
                field_data.add(i+1);
                System.out.print((i+1)+" - ");
            }
        }
        return field_data;
    }

    private boolean isAscii(int field_number){
        switch(field_number){
            case 34 :
            case 37 :
            case 38 :
            case 39 :
            case 41 :
            case 42 :
            case 45 :
            case 46 :
            case 48 :
            case 49 :
            case 55 :
            case 59 :
            case 60 :
            case 62 :
            case 63 :
                return true;
            default:
                return false;

        }
    }


}
