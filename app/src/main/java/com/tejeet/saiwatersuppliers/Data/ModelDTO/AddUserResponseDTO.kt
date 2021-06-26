package com.tejeet.saiwatersuppliers.Data.ModelDTO


import com.google.gson.annotations.SerializedName

data class AddUserResponseDTO(
    @SerializedName("email")
    val email: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("requeststatus")
    val requeststatus: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("tid")
    val tid: String
)