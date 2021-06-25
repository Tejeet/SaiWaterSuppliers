package com.tejeet.saiwatersuppliers.Data.ModelDTO


import com.google.gson.annotations.SerializedName

data class TankerDriver(
    @SerializedName("address")
    val address: Any,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("isEnabled")
    val isEnabled: String,
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("pass")
    val pass: String
)