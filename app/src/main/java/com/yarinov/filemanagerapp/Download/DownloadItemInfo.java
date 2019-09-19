package com.yarinov.filemanagerapp.Download;

public class DownloadItemInfo {

    private String itemName;
    private String itemSize;

    public DownloadItemInfo() {
    }

    public DownloadItemInfo(String itemName, String itemSize) {
        this.itemName = itemName;
        this.itemSize = itemSize;
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




}
