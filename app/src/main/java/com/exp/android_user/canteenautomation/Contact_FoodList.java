package com.exp.android_user.canteenautomation;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 1/17/2018.
 */

public class Contact_FoodList implements Parcelable {
    private String ngoId;
    private String petId;
    private String petCategory;
   // private String foodPrice;
    private String petDescription;
    public String item_qauntity;
    private String petImage;

    public String getItem_qauntity() {
        return item_qauntity;
    }

    public void setItem_qauntity(String item_qauntity) {
        this.item_qauntity = item_qauntity;
    }


    public Contact_FoodList(String petId, String petCategory, String item_qauntity) {
        this.petId = petId;
        this.petCategory = petCategory;
       // this.foodPrice = foodPrice;
        this.item_qauntity = item_qauntity;
        //this.petImagePath = petImagePath;
    }

    public String getPetImage() {
        return petImage;
    }

    public void setPetImage(String petImage) {
        this.petImage = petImage;
    }

    public static Creator<Contact_FoodList> getCREATOR() {
        return CREATOR;
    }

    public Contact_FoodList() {
    }

    protected Contact_FoodList(Parcel in) {
        ngoId = in.readString();
        petId = in.readString();
        petCategory = in.readString();
       // foodPrice = in.readString();
        petDescription = in.readString();
        petImage = in.readString();
        item_qauntity=in.readString();
    }

    public static final Creator<Contact_FoodList> CREATOR = new Creator<Contact_FoodList>() {
        @Override
        public Contact_FoodList createFromParcel(Parcel in) {
            return new Contact_FoodList(in);
        }

        @Override
        public Contact_FoodList[] newArray(int size) {
            return new Contact_FoodList[size];
        }
    };

    public String getNgoId() {
        return ngoId;
    }

    public void setNgoId(String ngoId) {
        this.ngoId = ngoId;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getPetCategory() {
        return petCategory;
    }

    public void setPetCategory(String petCategory) {
        this.petCategory = petCategory;
    }



    public String getPetDescription() {
        return petDescription;
    }

    public void setPetDescription(String petDescription) {
        this.petDescription = petDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ngoId);
        parcel.writeString(petId);
        parcel.writeString(petCategory);
        parcel.writeString(petDescription);
        parcel.writeString(petImage);
        parcel.writeString(item_qauntity);
    }
}
