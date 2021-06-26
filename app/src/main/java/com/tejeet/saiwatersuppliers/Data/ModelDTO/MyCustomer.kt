package com.tejeet.saiwatersuppliers.Data.ModelDTO


import com.google.gson.annotations.SerializedName

data class MyCustomer(
    @SerializedName("address")
    val address: String,
    @SerializedName("customername")
    val customername: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("profile_img")
    val profileImg: String,
    @SerializedName("rate")
    val rate: String,
    @SerializedName("societyname")
    val societyname: String,
    @SerializedName("uid")
    val uid: String,
    @SerializedName("waterlevel")
    val waterlevel: String
)