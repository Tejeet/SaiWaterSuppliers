package com.tejeet.saiwatersuppliers.Data.ModelDTO

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RevenueDetail(
    val customerName: String,
    val societyName: String,
    val tankerRate: String,
    val totalOrders: String,
    val uid: String
) : Parcelable