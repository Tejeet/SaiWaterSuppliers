package com.tejeet.saiwatersuppliers.Data.ModelDTO

data class AllOrder(
    val customerName: String,
    val filledby: String,
    val filltime: String,
    val oid: String,
    val orderStatus: String,
    val orderdispatchtime: String,
    val ordertime: String,
    val societyName: String,
    val uid: String
)