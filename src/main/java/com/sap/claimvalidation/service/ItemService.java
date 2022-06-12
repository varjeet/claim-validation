package com.sap.claimvalidation.service;

import com.sap.claimvalidation.dtos.ItemRequestDto;
import com.sap.claimvalidation.entities.Item;
import com.sap.claimvalidation.entities.Version;
import com.sap.claimvalidation.repository.ItemRepository;
import com.sap.claimvalidation.utils.ObjectMapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    ItemService(ItemRepository itemRepository){
        this.itemRepository=itemRepository;
    }

    public Item addItem(ItemRequestDto item, Version version)
    {
        Random r = new Random();
        int low = 10;
        int high = 100;
        int result = r.nextInt(high-low) + low;

        Item item1= ObjectMapperUtils.map(item,Item.class);
        item1.setItem_number(result);
        item1.setVersion(version);
        Item item11= this.itemRepository.save(item1);
        return item11;

    }

    public List<Item> getAllItems(){
        return this.itemRepository.findAll();
    }
    public Item getItemById(String id){
        Optional<Item> Optionalitem=this.itemRepository.findById(id);
          Item item= Optionalitem.get();
          return item;
    }

    public Item updateItem(ItemRequestDto item1,String itemId)
    {
        Item item= ObjectMapperUtils.map(item1,Item.class);
        Item itemResponse=getItemById(itemId);
        itemResponse.setId(itemResponse.getId());
        itemResponse.setAmount(item.getAmount());
        itemResponse.setMaterial(item.getMaterial());
        itemResponse.setType(item.getType());
        itemResponse.setPartCausingDamage(item.getPartCausingDamage());
        this.itemRepository.save(itemResponse);
        return itemResponse;
    }



}
