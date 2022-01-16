package com.ISO8583Decoder.ISO8583_Decoder.model;

import com.ISO8583Decoder.ISO8583_Decoder.dto.DecodeFieldDto;
import com.ISO8583Decoder.ISO8583_Decoder.dto.DecodeMsgDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "decodeMsgs")
public class DecodeMsg {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String originalMsg;
    private String length;
    private String tpdu;
    private String bitmap;

    @OneToOne(mappedBy = "decodeMsg", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private MtiItem mti;

    @OneToMany(mappedBy = "decodeMsg", cascade = CascadeType.ALL, orphanRemoval = true)
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

    public DecodeMsgDto toDto(){
        List<DecodeFieldDto> decodeFieldDtos = new ArrayList<DecodeFieldDto>();
        for (DecodeField d : this.decodeFields){
            decodeFieldDtos.add(d.toDto());
        }
        return new DecodeMsgDto(this.originalMsg,this.length,this.tpdu,this.bitmap,this.mti.getMti().getMti(),decodeFieldDtos);
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
