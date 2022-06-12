package com.sap.claimvalidation.dtos;



public class ItemResponseDto {

    private String id;
    private String material;
    private String partCausingDamage;
    private String type;
    private String amount;
    private Integer item_number;


    public Integer getItem_number() {
        return item_number;
    }

    public void setItem_number(Integer item_number) {
        this.item_number = item_number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getPartCausingDamage() {
        return partCausingDamage;
    }

    public void setPartCausingDamage(String partCausingDamage) {
        this.partCausingDamage = partCausingDamage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
