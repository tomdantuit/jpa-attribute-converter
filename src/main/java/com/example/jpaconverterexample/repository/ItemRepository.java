package com.example.jpaconverterexample.repository;

import com.example.jpaconverterexample.repository.model.ItemRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<ItemRecord, Long> {
}
