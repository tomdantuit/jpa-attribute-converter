package com.example.jpaconverterexample.service;

import com.example.jpaconverterexample.domain.Item;
import com.example.jpaconverterexample.repository.ItemRepository;
import com.example.jpaconverterexample.repository.model.ItemRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getItems() {

        List<ItemRecord> allItemRecords = (List)this.itemRepository.findAll();
        List<Item> items = allItemRecords
                .stream()
                .map(itemRecord -> new Item(itemRecord.getId(), itemRecord.getCode()))
                .collect(Collectors.toList());

        return items;
    }

    @Override
    public Item getItem(Long id) {

        Item result = null;
        ItemRecord itemRecord = this.itemRepository.findById(id).get();

        if(itemRecord != null) {
            result = new Item(itemRecord.getId(), itemRecord.getCode());
        }

        return result;
    }

    @Override
    public Long createItem(Item item) {

        ItemRecord recordToSave = new ItemRecord();
        recordToSave.setCode(item.getCode());
        ItemRecord savedItem = this.itemRepository.save(recordToSave);

        return savedItem.getId();
    }

    @Override
    public Long updateItem(Item item) {
        ItemRecord recordToUpdate = this.itemRepository.findById(item.getId()).get();

        recordToUpdate.setCode(item.getCode());

        recordToUpdate = this.itemRepository.save(recordToUpdate);
        return recordToUpdate.getId();
    }

    @Override
    public void deleteItem(Long id) {
        this.itemRepository.deleteById(id);
    }
}
