package com.exp.android_user.canteenautomation;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import java.util.List;
import java.util.Observable;

import retrofit2.Call;


public interface ApiService {
    @GET("register.php")

    Call<InsertResponseModel> insertData(@Query("name") String name,
                                         @Query("address") String address,
                                         @Query("contact") String contact,
                                         @Query("email") String email,
                                         @Query("password") String password);
@Multipart
@POST("insertInfo.php")
    Call<InsertResponseModel> insertFood(@Part("ngoId") RequestBody ngoId,
                                        @Part("petId") RequestBody petId,
                                        @Part("petCategory") RequestBody petCategory,
                                        @Part("petDescription") RequestBody petDescription,
                                        @Part("petQuantity") RequestBody petQuantity,
                                        @Part MultipartBody.Part petImage
                                        );


    @GET("readUserDetail.php")
    Call<List<Contact_userDetail>> getContacts();

    @GET("readPetInfo.php")
    Call<List<Contact_FoodList>> getFood(@Query("ngoId") String ngoId);

    @GET("get_pet.php")
    Call<String> Saveorder(@Query("data") String data);

    @GET("readOrderDetail.php")
    Call<List<Contact_OrderDetailsA>> getOrdrerDetails();
}
