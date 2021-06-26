package com.tejeet.saiwatersuppliers.Data.ModelDTO


import com.google.gson.annotations.SerializedName

data class LoginResponseDTO(
    @SerializedName("email")
    val email: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("pass")
    val pass: String,
    @SerializedName("requeststatus")
    val requeststatus: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("userid")
    val userid: String,
    @SerializedName("username")
    val username: String
)