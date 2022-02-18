package com.ISO8583Decoder.ISO8583_Decoder.model;

import com.ISO8583Decoder.ISO8583_Decoder.dto.DecodeFieldDto;
import com.ISO8583Decoder.ISO8583_Decoder.dto.DecodeMsgDto;
import com.ISO8583Decoder.ISO8583_Decoder.model.entities.Field;

import java.util.ArrayList;
import java.util.List;

public class DecodeMsg {

    private Integer id;
    private String originalMsg;
    private String length;
    private String tpdu;
    private String bitmap;
    private String data;

    private MtiItem mti;

    private List<DecodeField> decodeFields;

    public DecodeMsg(){}

    public DecodeMsg(String message){
        this.originalMsg = message;
    }

    public DecodeMsg(String message,String length,String bitmap,String tpdu){
        this.originalMsg = message;
        this.length = length;
        this.bitmap = bitmap;
        this.tpdu = tpdu;
    }

    public DecodeMsg(String length, String tpdu, MtiItem mti, List<DecodeField> decodeFields) {
        this.length = length;
        this.tpdu = tpdu;
        this.mti = mti;
        this.decodeFields = decodeFields;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getTpdu() {
        return tpdu;
    }

    public void setTpdu(String tpdu) {
        this.tpdu = tpdu;
    }

    public MtiItem getMti() {
        return mti;
    }

    public void setMti(MtiItem mti) {
        this.mti = mti;
    }

    public List<DecodeField> getDecodeFields() {
        return decodeFields;
    }

    public void setDecodeFields(List<DecodeField> decodeFields) {
        this.decodeFields = decodeFields;
    }

    public String getBitmap() {
        return bitmap;
    }

    public void setBitmap(String bitmap) {
        this.bitmap = bitmap;
    }

    public String getOriginalMsg() {
        return originalMsg;
    }

    public void setOriginalMsg(String originalMsg) {
        this.originalMsg = originalMsg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public DecodeMsgDto toDto(){
        List<DecodeFieldDto> decodeFieldDtos = new ArrayList<DecodeFieldDto>();
        for (DecodeField d : this.decodeFields){
            decodeFieldDtos.add(d.toDto());
        }
        return new DecodeMsgDto(this.originalMsg,this.length,this.tpdu,this.bitmap,this.mti.getMti().getMti(),decodeFieldDtos);
    }

    public void decodeHeader(){
        String data;
        if (this.originalMsg.substring(0,2).compareTo("60") == 0){
            //Without length at the beginning
            System.out.println("TPDU: "+this.originalMsg.substring(0,10));
            this.setTpdu(this.originalMsg.substring(0,10));

            System.out.println("MTI: "+this.originalMsg.substring(10,14));

            this.setBitmap(this.originalMsg.substring(14,30));
            this.data = this.originalMsg.substring(30,this.originalMsg.length());
        } else {
            //With length at the beginning
            System.out.println("Length: "+this.originalMsg.substring(0,4));
            System.out.println("TPDU: "+this.originalMsg.substring(4,14));
            System.out.println("MTI: "+this.originalMsg.substring(14,18));

            this.setLength(this.originalMsg.substring(0,4));
            this.setTpdu(this.originalMsg.substring(4,14));
            this.setBitmap(this.originalMsg.substring(18,34));
            this.data = this.originalMsg.substring(34,this.originalMsg.length());

        }

    }

    public String getMtiFromOriginalMsg(){
        String mti;
        if (this.originalMsg.substring(0,2).compareTo("60") == 0){
            //Without length at the beginning
            mti = this.originalMsg.substring(10,14);
        } else {
            //With length at the beginning
            mti = this.originalMsg.substring(14,18);
        }
        return mti;
    }

    public DecodeField decodeFields(Field f_data, String data) throws Exception {
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
                this.data = data.substring(longitud + 4 + incremento);

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
                this.data = data.substring(longitud + 2 + incremento);

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
                this.data = data.substring(longitud + incremento);
            } else {
                decoded_data = data.substring(0,longitud);
                this.data = data.substring(longitud);
            }
            System.out.print("Field "+f_data.getField_number() + " -- " + f_data.getName() + " -- ");
            // System.out.println(decoded_data);

        }

        DecodeField new_decodeField = new DecodeField(this ,decoded_data);
        FieldItem fieldItem = new FieldItem(f_data,new_decodeField);
        new_decodeField.setField(fieldItem);
        System.out.println(decoded_data);

        return new_decodeField;

    }

    public List<Integer> getFieldsWithData(){
        //Convert bitmap (Hexa) to Binary
        System.out.println("BITMAP: "+bitmap);
        String bitmapInBinary = this.convertBitmapToBinary(this.bitmap);

        //Fields with data
        System.out.print("Fields with data: ");
        List<Integer> field_data = this.getFieldsWithData(bitmapInBinary);
        return field_data;
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
        //TODO En AMEX el campo 47 es ASCII, PUEDE SER?

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
