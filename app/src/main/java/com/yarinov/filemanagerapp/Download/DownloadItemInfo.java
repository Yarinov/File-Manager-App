package com.yarinov.filemanagerapp.Download;

public class DownloadItemInfo {

    private String itemName;
    private String itemSize;
    private String itemType;

    public DownloadItemInfo() {
    }

    public DownloadItemInfo(String itemName, String itemSize, String itemType) {
        this.itemName = itemName;
        this.itemSize = itemSize;
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }


}
