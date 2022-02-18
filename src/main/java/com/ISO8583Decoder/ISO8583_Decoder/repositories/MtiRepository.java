package com.ISO8583Decoder.ISO8583_Decoder.repositories;

import com.ISO8583Decoder.ISO8583_Decoder.model.entities.Mti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MtiRepository extends JpaRepository<Mti,Integer> {

    @Query("SELECT m FROM Mti m WHERE m.mti = ?1")
    Mti findByMtiNumber(String mti_number);
}
