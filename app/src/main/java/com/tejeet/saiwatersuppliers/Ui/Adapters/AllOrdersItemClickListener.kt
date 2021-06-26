package com.tejeet.saiwatersuppliers.Ui.Adapters

import com.tejeet.saiwatersuppliers.Data.ModelDTO.AllOrder
import com.tejeet.saiwatersuppliers.Data.ModelDTO.MyCustomer

interface AllOrdersItemClickListener {
    fun OnOrderCardViewClick(allOrder: AllOrder)
}