package com.ISO8583Decoder.ISO8583_Decoder.repositories;

import com.ISO8583Decoder.ISO8583_Decoder.model.entities.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field,Integer> {

    @Query("select f from Field f where f.field_number = ?1 AND f.acquirer.id = ?2")
    Field findByFieldNumberAndAcquirer(int field,int acquirerId);

}
