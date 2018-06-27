package com.example.jpaconverterexample.controller;

import com.example.jpaconverterexample.domain.Item;
import com.example.jpaconverterexample.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/item")
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> getAllItems() {
        return new ResponseEntity(this.itemService.getItems(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Item> getItem(@PathVariable(name = "id") Long id) {
        Item item = this.itemService.getItem(id);

        if(item != null) {
            return new ResponseEntity(item, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Long> createItem(@RequestBody Item item) {
        Long createdItemId = this.itemService.createItem(item);

        return new ResponseEntity(createdItemId, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<Long> updateItem(@RequestBody Item item) {
        Long updatedItemId = this.itemService.updateItem(item);

        return new ResponseEntity(updatedItemId, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteItem(@PathVariable(name = "id") Long id) {
        this.itemService.deleteItem(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
