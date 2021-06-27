package com.tejeet.saiwatersuppliers.Data.ModelDTO

data class RevenueResponseDTO(
    val RevenueDetails: List<RevenueDetail>,
    val noofsociety: Int,
    val requeststatus: Int,
    val success: Boolean,
    val tid: String
)