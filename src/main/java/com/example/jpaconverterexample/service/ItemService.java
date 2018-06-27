package com.example.jpaconverterexample.service;

import com.example.jpaconverterexample.domain.Item;

import java.util.List;

public interface ItemService {

    List<Item> getItems();
    Item getItem(Long id);
    Long createItem(Item item);
    Long updateItem(Item item);
    void deleteItem(Long id);
}
