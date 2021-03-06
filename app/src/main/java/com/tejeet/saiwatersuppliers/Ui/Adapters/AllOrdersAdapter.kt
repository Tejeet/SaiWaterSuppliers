package com.tejeet.saiwatersuppliers.Ui.Adapters

import android.graphics.Color
import android.provider.CalendarContract
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
import com.tejeet.saiwatersuppliers.R

class AllOrdersAdapter(
    private var dataList: MutableList<AllOrder>,
    private val itemClickListener: AllOrdersItemClickListener
) : RecyclerView.Adapter<AllOrdersAdapter.AllOrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllOrderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_orders, parent, false)
        return AllOrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllOrderViewHolder, position: Int) {

        holder.apply {

            societyName.text = dataList[position].societyName
            orderTime.text = "Order Time : ${dataList[position].ordertime}"

            if (dataList[position].orderStatus.equals("0")){
                orderStatus.text = "Placed"
                orderStatus.setTextColor(Color.parseColor("#FF0000"))
            }
            else if (dataList[position].orderStatus.equals("1")){
                orderStatus.text = "Dispatched"
                orderStatus.setTextColor(Color.parseColor("#0000FF"))
            }
            else{
                orderStatus.text = "Done"
                orderStatus.setTextColor(Color.parseColor("#00FF00"))
            }


            cvAllOrdersView.setOnClickListener {
                itemClickListener.OnOrderCardViewClick(dataList[position])

            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(newData: MutableList<AllOrder>) {
        dataList = newData
        notifyDataSetChanged()
    }


    class AllOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val societyName = itemView.findViewById<TextView>(R.id.tvSocietyName)
        val orderTime = itemView.findViewById<TextView>(R.id.tvOrderTime)
        val orderStatus = itemView.findViewById<TextView>(R.id.tv_order_status)
        val tvDeliveryTime = itemView.findViewById<TextView>(R.id.tvDeliveryTime)
        val cvAllOrdersView = itemView.findViewById<CardView>(R.id.cvAllOrdersItem)

    }


}