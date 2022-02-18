package com.ISO8583Decoder.ISO8583_Decoder.repositories;

import com.ISO8583Decoder.ISO8583_Decoder.model.entities.Acquirer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquirerRepository extends JpaRepository<Acquirer,Integer> {

}
