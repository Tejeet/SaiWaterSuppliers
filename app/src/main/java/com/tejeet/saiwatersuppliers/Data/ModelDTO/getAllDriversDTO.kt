package com.tejeet.saiwatersuppliers.Data.ModelDTO


import com.google.gson.annotations.SerializedName

data class getAllDriversDTO(
    @SerializedName("noofDrivers")
    val noofDrivers: Int,
    @SerializedName("requeststatus")
    val requeststatus: Int,
    @SerializedName("TankerDrivers")
    val tankerDrivers: List<TankerDriver>
)