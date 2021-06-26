package com.tejeet.saiwatersuppliers.Data.ModelDTO


import com.google.gson.annotations.SerializedName

data class GetAllUserDTO(
    @SerializedName("MyCustomers")
    val myCustomers: List<MyCustomer>,
    @SerializedName("noofusers")
    val noofusers: Int,
    @SerializedName("requeststatus")
    val requeststatus: Int
)