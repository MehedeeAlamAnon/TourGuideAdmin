package com.example.anon.user;

/**
 * Created by Anon on 02-Feb-18.
 */

public class Place {

    String id;
    String name;
    String description;
    String address;
    String openTime;
    String phone;
    String website;
    String imageUri;

    public Place(){

    }

    public Place(String id,String name, String description, String address, String openTime, String phone, String website, String imageUri) {
        this.id=id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.openTime = openTime;
        this.phone = phone;
        this.website = website;
        this.imageUri = imageUri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
