package com.ISO8583Decoder.ISO8583_Decoder.controller;

import com.ISO8583Decoder.ISO8583_Decoder.model.Acquirer;
import com.ISO8583Decoder.ISO8583_Decoder.model.Field;
import com.ISO8583Decoder.ISO8583_Decoder.services.AcquirerService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @Autowired
    private AcquirerService acquirerService;

    @PostMapping("/fields")
    public void createFields() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document wikipedia = db.parse(new File("src/main/resources/wikipedia_table.xml"));
            this.readXml(wikipedia,1);
            Document emvamex = db.parse(new File("src/main/resources/emvamex_table.xml"));
            this.readXml(emvamex,3);
            Document emvvisaprisma = db.parse(new File("src/main/resources/emvvisaprisma_table.xml"));
            this.readXml(emvvisaprisma,2);


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private void readXml(Document doc, Integer acquirerId) throws Exception {
        Acquirer a = acquirerService.findById(acquirerId);
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

                Field f = new Field(Integer.valueOf(id),type,Integer.valueOf(longitud),name,a);
                fieldService.save(f);
            }
        }
    }



}
