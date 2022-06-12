package com.sap.claimvalidation.dtos;

public class ClaimRequestDto {

    private ItemRequestDto item;
    private VersionRequestDto version;
    private HeaderRequestDto header;

    public HeaderRequestDto getHeader() {
        return header;
    }

    public void setHeader(HeaderRequestDto header) {
        this.header = header;
    }

    public ItemRequestDto getItem() {
        return item;
    }

    public void setItem(ItemRequestDto item) {
        this.item = item;
    }

    public VersionRequestDto getVersion() {
        return version;
    }

    public void setVersion(VersionRequestDto version) {
        this.version = version;
    }


}
