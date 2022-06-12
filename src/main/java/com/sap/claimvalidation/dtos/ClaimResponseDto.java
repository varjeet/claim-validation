package com.sap.claimvalidation.dtos;

public class ClaimResponseDto {

    private String id;

    private ItemResponseDto item;

    private VersionResponseDto version;
    private HeaderResponseDto header;

    public HeaderResponseDto getHeader() {
        return header;
    }

    public void setHeader(HeaderResponseDto header) {
        this.header = header;
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
