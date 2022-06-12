package com.sap.claimvalidation.dtos;

public class HeaderResponseDto {
    private String id;

    private String registartion_date;
    private String damage_date;
    private String  repair_start_Date;
    private String repair_end_date;

    private String customer;
    private String object_type;
    private String object_number;
    private String source;
    private String source_object_number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegistartion_date() {
        return registartion_date;
    }

    public void setRegistartion_date(String registartion_date) {
        this.registartion_date = registartion_date;
    }

    public String getDamage_date() {
        return damage_date;
    }

    public void setDamage_date(String damage_date) {
        this.damage_date = damage_date;
    }

    public String getRepair_start_Date() {
        return repair_start_Date;
    }

    public void setRepair_start_Date(String repair_start_Date) {
        this.repair_start_Date = repair_start_Date;
    }

    public String getRepair_end_date() {
        return repair_end_date;
    }

    public void setRepair_end_date(String repair_end_date) {
        this.repair_end_date = repair_end_date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getObject_type() {
        return object_type;
    }

    public void setObject_type(String object_type) {
        this.object_type = object_type;
    }

    public String getObject_number() {
        return object_number;
    }

    public void setObject_number(String object_number) {
        this.object_number = object_number;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource_object_number() {
        return source_object_number;
    }

    public void setSource_object_number(String source_object_number) {
        this.source_object_number = source_object_number;
    }
}
