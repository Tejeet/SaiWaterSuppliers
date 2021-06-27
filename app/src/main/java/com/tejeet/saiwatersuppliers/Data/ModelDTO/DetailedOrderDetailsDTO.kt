package com.tejeet.saiwatersuppliers.Data.ModelDTO

data class DetailedOrderDetailsDTO(
    val AllDetailedOrder: List<AllDetailedOrder>,
    val requeststatus: Int,
    val success: Boolean,
    val tid: String,
    val totaltankers: Int
)