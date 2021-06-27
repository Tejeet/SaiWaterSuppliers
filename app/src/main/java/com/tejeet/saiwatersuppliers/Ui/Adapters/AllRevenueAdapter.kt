package com.tejeet.saiwatersuppliers.Ui.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.tejeet.saiwatersuppliers.Data.ModelDTO.AllOrder
import com.tejeet.saiwatersuppliers.Data.ModelDTO.MyCustomer
import com.tejeet.saiwatersuppliers.Data.ModelDTO.RevenueDetail
import com.tejeet.saiwatersuppliers.R

class AllRevenueAdapter(



    private var dataList: MutableList<RevenueDetail>,
    private val itemClickListener: RevenueItemClickListener
) : RecyclerView.Adapter<AllRevenueAdapter.RevenueDetailsHolder>() {

    private val TAG = "tag"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RevenueDetailsHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_revenue, parent, false)
        return RevenueDetailsHolder(view)
    }

    override fun onBindViewHolder(holder: RevenueDetailsHolder, position: Int) {

        holder.apply {

            societyName.text = dataList[position].societyName
            totalTanker.text = "Total Tanker : ${dataList[position].totalOrders}"
            tankerRate.text = "Tanker Rate : ${dataList[position].tankerRate}"
            total_bill.text = "500"

            val totalBill = (dataList[position].tankerRate.toInt()).times(dataList[position].totalOrders.toInt())

            total_bill.text = "₹ ${totalBill.toString()}"


            cvAllRevenueView.setOnClickListener {
                itemClickListener.onRevenueItemClick(dataList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(newData: MutableList<RevenueDetail>) {
        dataList = newData
        notifyDataSetChanged()
    }


    class RevenueDetailsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val societyName = itemView.findViewById<TextView>(R.id.society_name)
        val totalTanker = itemView.findViewById<TextView>(R.id.tv_total_tanker)
        val tankerRate = itemView.findViewById<TextView>(R.id.tv_tanker_rate)
        val total_bill = itemView.findViewById<TextView>(R.id.tv_total_amount)
        val cvAllRevenueView = itemView.findViewById<CardView>(R.id.cvAllRevenueItem)

    }


}