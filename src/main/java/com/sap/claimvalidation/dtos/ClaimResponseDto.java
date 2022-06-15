package com.sap.claimvalidation.dtos;

public class ClaimResponseDto {

    private String id;
    private Integer claim_number;

    private ItemResponseDto item;

    private VersionResponseDto version;
    private HeaderResponseDto header;


    public HeaderResponseDto getHeader() {
        return header;
    }

    public void setHeader(HeaderResponseDto header) {
        this.header = header;
    }

    public Integer getClaim_number() {
        return claim_number;
    }

    public void setClaim_number(Integer claim_number) {
        this.claim_number = claim_number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ItemResponseDto getItem() {
        return item;
    }

    public void setItem(ItemResponseDto item) {
        this.item = item;
    }

    public VersionResponseDto getVersion() {
        return version;
    }

    public void setVersion(VersionResponseDto version) {
        this.version = version;
    }
}
