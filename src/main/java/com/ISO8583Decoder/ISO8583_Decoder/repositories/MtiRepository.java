package com.ISO8583Decoder.ISO8583_Decoder.repositories;

import com.ISO8583Decoder.ISO8583_Decoder.model.Mti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MtiRepository extends JpaRepository<Mti,Integer> {
}
