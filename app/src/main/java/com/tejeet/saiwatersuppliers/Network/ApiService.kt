package com.tejeet.saiwatersuppliers.Network

import com.tejeet.saiwatersuppliers.Data.ModelDTO.AddDriverResponseDTO
import com.tejeet.saiwatersuppliers.Data.ModelDTO.AddUserResponseDTO
import com.tejeet.saiwatersuppliers.Data.ModelDTO.LoginResponseDTO
import com.tejeet.saiwatersuppliers.Data.ModelDTO.getAllDriversDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    //https://saiwatertanker.tejeet.com/api/appAdmin.php?
    // adminLogin=OK&trustedAppKey=66fbe971faf76f9863eb564c567bd56ad9c39ba0b9eda8f0b45a410545df8631
    // &userEmail=tejeetm@gmail.com&userPass=tejeet123&firebasetoken=e23423
    // &smartphone=Samsung&smartphoneModel=23242&apilevel=23&androidversion=30

    @GET("api/appAdmin.php?")
    suspend fun userLogin(
        @Query("adminLogin") adminLogin : String,
        @Query("trustedAppKey") trustedAppKey:String,
        @Query("userEmail") userEmail : String,
        @Query("userPass") userPass: String,
        @Query("firebasetoken") firebaseToken : String,
        @Query("smartphoneModel") smartphoneModel : String,
        @Query("apilevel") apiLevel : String,
        @Query("androidversion") androidVersion : String
    ): Response<LoginResponseDTO>

    @POST("api/appAdmin.php")
    suspend fun addDriver(
        @Query("addDriver") addDriver : String,
        @Query("trustedAppKey") trustedAppKey:String,
        @Query("name") name : String,
        @Query("email") email: String,
        @Query("mobile") mobile : String,
        @Query("pass") pass : String,
        @Query("adminName") adminName : String
    ): Response<AddDriverResponseDTO>

    @GET("api/appAdmin.php")
    suspend fun getAllDrivers(
        @Query("getAllDrivers") getAllDriversResponse : String,
        @Query("trustedAppKey") trustedAppKey:String,
        @Query("userid") userid : String,
        @Query("useremail") useremail: String,

    ): Response<getAllDriversDTO>


    @GET("api/appAdmin.php")
    suspend fun addUser(
        @Query("addCustomer") addCustomer : String,
        @Query("trustedAppKey") trustedAppKey:String,
        @Query("societyName") societyName : String,
        @Query("customerName") customerName: String,
        @Query("customerEmail") customerEmail : String,
        @Query("customerMobile") customerMobile : String,
        @Query("customerAddress") customerAddress : String,
        @Query("tankerRate") tankerRate : String,
        @Query("adminName") adminName : String
    ): Response<AddUserResponseDTO>


}