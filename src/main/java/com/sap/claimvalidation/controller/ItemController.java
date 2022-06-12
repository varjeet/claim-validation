package com.sap.claimvalidation.controller;

import com.sap.claimvalidation.entities.Item;
import com.sap.claimvalidation.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    private final ItemService itemService;

    ItemController(ItemService itemService){
        this.itemService=itemService;
    }

//    @PostMapping("/claim/item")
//    public ResponseEntity<Item> addItem( @RequestBody Item item){
//        if(item != null){
//            return new ResponseEntity<>(itemService.addItem(item), HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }

    @GetMapping("/claim/item")
    public ResponseEntity<List<Item>> getAllItems(){
        return new ResponseEntity<>(itemService.getAllItems(),HttpStatus.OK);
    }

    @GetMapping("/claim/item/{itemId}")
    public ResponseEntity<Item> getItemById(@PathVariable(value = "itemID") String itemId){
        return new ResponseEntity<>(itemService.getItemById(itemId),HttpStatus.OK);
    }

//    @PutMapping("/claim/item/{itemId}")
//    public Item updateItem(@RequestBody Item item, @PathVariable(value = "itemId") String itemId)
//    {
//      return  this.itemService.updateItem(item,itemId);
//
//    }

}
