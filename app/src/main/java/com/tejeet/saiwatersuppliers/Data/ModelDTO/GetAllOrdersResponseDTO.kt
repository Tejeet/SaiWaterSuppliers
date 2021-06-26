package com.tejeet.saiwatersuppliers.Data.ModelDTO

data class GetAllOrdersResponseDTO(
    val AllOrders: List<AllOrder>,
    val nooforders: Int,
    val requeststatus: Int,
    val success: Boolean,
    val tid: String
)