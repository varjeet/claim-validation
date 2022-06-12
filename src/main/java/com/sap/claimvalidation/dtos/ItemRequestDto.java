package com.sap.claimvalidation.dtos;

public class ItemRequestDto {
    private String material;
    private String partCausingDamage;
    private String type;
    private String amount;



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
