package com.ISO8583Decoder.ISO8583_Decoder.controller;

import com.ISO8583Decoder.ISO8583_Decoder.model.Field;
import com.ISO8583Decoder.ISO8583_Decoder.services.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

@RestController
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @PostMapping("/fields")
    public void createFields(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File("src/main/resources/data_elements.xml"));
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("isofield");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    // get text
                    String id = element.getAttribute("id");
                    String longitud = element.getAttribute("length");
                    String name = element.getAttribute("name");
                    String type = element.getAttribute("class");

                    Field f = new Field(Integer.valueOf(id),type,Integer.valueOf(longitud),name);
                    fieldService.save(f);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("decode/{message}")
    public String decode(@PathVariable String message){
        String originalMsg = message.replaceAll("\\s+","");
        if (originalMsg.substring(0,2).compareTo("60") == 0){
            //Without length at the beginning
            System.out.println("TPDU: "+originalMsg.substring(0,10));
        } else {
            //With length at the beginning
            System.out.println("Length: "+originalMsg.substring(0,4));
            System.out.println("TPDU: "+originalMsg.substring(4,14));
        }

        return "";
    }

}
