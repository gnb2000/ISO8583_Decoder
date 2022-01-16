package com.ISO8583Decoder.ISO8583_Decoder.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DecodeController {

    @RequestMapping("decode/{message}")
    public String decode(@PathVariable String message){
        String originalMsg = message.replaceAll("\\s+","");
        String bitmap = "";
        if (originalMsg.substring(0,2).compareTo("60") == 0){
            //Without length at the beginning
            System.out.println("TPDU: "+originalMsg.substring(0,10));
            System.out.printf("MTI: "+originalMsg.substring(10,14));
            bitmap = originalMsg.substring(14,28);
        } else {
            //With length at the beginning
            System.out.println("Length: "+originalMsg.substring(0,4));
            System.out.println("TPDU: "+originalMsg.substring(4,14));
            System.out.println("MTI: "+originalMsg.substring(14,18));
            bitmap = originalMsg.substring(18,34);
        }

        //Convert bitmap (Hexa) to Binary
        System.out.println("BITMAP: "+bitmap);
        String bitmapInBinary = this.convertBitmapToBinary(bitmap);

        //Fields with data
        System.out.print("Fields with data: ");
        List<Integer> field_data = this.getFieldsWithData(bitmapInBinary);


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
                field_data.add(Integer.valueOf(bitmapInBinary.charAt(i)));
                System.out.print((i+1)+" - ");
            }
        }
        return field_data;
    }
}
