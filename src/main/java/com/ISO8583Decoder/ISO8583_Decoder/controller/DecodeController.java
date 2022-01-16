package com.ISO8583Decoder.ISO8583_Decoder.controller;

import com.ISO8583Decoder.ISO8583_Decoder.model.Field;
import com.ISO8583Decoder.ISO8583_Decoder.services.FieldService;
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

    @RequestMapping("decode/{message}")
    public String decode(@PathVariable String message) throws Exception {
        String originalMsg = message.replaceAll("\\s+","");
        String bitmap = "";
        String data = "";
        if (originalMsg.substring(0,2).compareTo("60") == 0){
            //Without length at the beginning
            System.out.println("TPDU: "+originalMsg.substring(0,10));
            System.out.printf("MTI: "+originalMsg.substring(10,14));
            bitmap = originalMsg.substring(14,28);
            data = originalMsg.substring(28,originalMsg.length() - 1);
        } else {
            //With length at the beginning
            System.out.println("Length: "+originalMsg.substring(0,4));
            System.out.println("TPDU: "+originalMsg.substring(4,14));
            System.out.println("MTI: "+originalMsg.substring(14,18));
            bitmap = originalMsg.substring(18,34);
            data = originalMsg.substring(34,originalMsg.length() - 1);

        }

        //Convert bitmap (Hexa) to Binary
        System.out.println("BITMAP: "+bitmap);
        String bitmapInBinary = this.convertBitmapToBinary(bitmap);

        //Fields with data
        System.out.println("Fields with data: ");
        List<Integer> field_data = this.getFieldsWithData(bitmapInBinary);

        System.out.println("Data");
        System.out.println(data);

        int current_position = 0;

        for (Integer f : field_data){
            Field f_data = fieldService.getFieldByFieldNumber(f);
            String decoded_data = "";
            System.out.println("CURRENT POSITION "+current_position);
            if (f_data.getType().substring(0,3).compareTo("LLL") == 0){
                //LLLVAR
                System.out.print(f_data.getField_number() + " -- " + f_data.getName() + " -- ");

                //Primeros dos digitos indica la longitud
                /*int longitud = Integer.valueOf(data.substring(0,4));
                if (Integer.compare(longitud,f_data.getLength()) < 0 || Integer.compare(longitud,f_data.getLength()) == 0) {
                    if (longitud % 2 != 0){
                        //Hay que agregar uno mas que es el relleno
                        decoded_data = data.substring(4,4 + longitud + 1);
                    } else {
                        decoded_data = data.substring(4,4 + longitud);
                    }
                    data = data.substring(longitud + 4 + 1);

                    System.out.println(decoded_data);
                } else {
                    throw new Exception("La longitud excede la longitud maxima del campo ( "+f_data.getLength()+" )");
                }*/
            } else if (f_data.getType().substring(0,2).compareTo("LL") == 0){
                //LLVAR

                System.out.print(f_data.getField_number() + " -- " + f_data.getName() + " -- ");

                //Primeros dos digitos indica la longitud
                int longitud = Integer.valueOf(data.substring(0,2));
                if (Integer.compare(longitud,f_data.getLength()) < 0 || Integer.compare(longitud,f_data.getLength()) == 0) {
                    if (longitud % 2 != 0){
                        //Hay que agregar uno mas que es el relleno
                        decoded_data = data.substring(2,2 + longitud + 1);
                    } else {
                        decoded_data = data.substring(2,2 + longitud);
                    }
                    data = data.substring(longitud + 2 + 1);

                    System.out.println(decoded_data);
                } else {
                    throw new Exception("La longitud excede la longitud maxima del campo ( "+f_data.getLength()+" )");
                }


            } else {
                //FIXED
                if (f_data.getLength() % 2 != 0){
                    decoded_data = data.substring(0,f_data.getLength() + 1);
                    data = data.substring(f_data.getLength() + 1);
                } else {
                    decoded_data = data.substring(0,f_data.getLength());
                    data = data.substring(f_data.getLength());
                }
                System.out.print(f_data.getField_number() + " -- " + f_data.getName() + " -- ");
                System.out.println(decoded_data);

            }

            System.out.println(data);
        }

        return "";
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
}
