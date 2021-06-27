package com.tejeet.saiwatersuppliers.Ui.Adapters

import com.tejeet.saiwatersuppliers.Data.ModelDTO.MyCustomer
import com.tejeet.saiwatersuppliers.Data.ModelDTO.RevenueDetail

interface RevenueItemClickListener {
    fun onRevenueItemClick(revenueDetail: RevenueDetail)
}