package com.exp.android_user.canteenautomation;

/**
 * Created by user on 1/26/2018.
 */

public class Contact_OrderDetailsA {
    private String petId;
    private String item_qauntity;
    private String petCategory;
    private String email;

    public Contact_OrderDetailsA(String petId, String item_qauntity, String petCategory, String email) {
        this.petId = petId;
        this.item_qauntity = item_qauntity;
        this.petCategory = petCategory;
        this.email = email;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getItem_qauntity() {
        return item_qauntity;
    }

    public void setItem_qauntity(String item_qauntity) {
        this.item_qauntity = item_qauntity;
    }

    public String getPetCategory() {
        return petCategory;
    }

    public void setPetCategory(String petCategory) {
        this.petCategory = petCategory;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
