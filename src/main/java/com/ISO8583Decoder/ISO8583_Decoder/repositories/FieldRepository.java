package com.ISO8583Decoder.ISO8583_Decoder.repositories;

import com.ISO8583Decoder.ISO8583_Decoder.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field,Integer> {

}
