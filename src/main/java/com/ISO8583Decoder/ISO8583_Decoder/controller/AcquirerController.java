package com.ISO8583Decoder.ISO8583_Decoder.controller;

import com.ISO8583Decoder.ISO8583_Decoder.model.entities.Acquirer;
import com.ISO8583Decoder.ISO8583_Decoder.services.AcquirerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AcquirerController {

    @Autowired
    private AcquirerService acquirerService;

    @PostMapping("/acquirers")
    public void loadAcquirers(){
        Acquirer a1 = new Acquirer("WIKIPEDIA");
        Acquirer a2 = new Acquirer("EMV PRISMA");
        Acquirer a3 = new Acquirer("EMV AMEX");

        acquirerService.save(a1);
        acquirerService.save(a2);
        acquirerService.save(a3);
    }
}
