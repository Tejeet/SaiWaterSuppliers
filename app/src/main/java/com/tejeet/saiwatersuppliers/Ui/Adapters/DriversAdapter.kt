package com.tejeet.saiwatersuppliers.Ui.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.tejeet.saiwatersuppliers.Data.ModelDTO.MyCustomer
import com.tejeet.saiwatersuppliers.Data.ModelDTO.TankerDriver
import com.tejeet.saiwatersuppliers.R

class DriversAdapter(private var dataList:MutableList<TankerDriver>, private val itemClickListener: DriverItemClickListner)
    :RecyclerView.Adapter<DriversAdapter.DriverViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_drivers,parent,false)
        return   DriverViewHolder(view)
    }

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {

        holder.apply {

            driverName.text = dataList[position].name
            driverStatus.text = "Present"

            driverCard.setOnClickListener {
                itemClickListener.OnDriverItemClickListner(dataList[position])

            }
        }
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    fun updateData( newData:MutableList<TankerDriver>){
        dataList = newData
        notifyDataSetChanged()
    }


    class DriverViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

         val driverName = itemView.findViewById<TextView>(R.id.tvDriverName)
         val driverStatus = itemView.findViewById<TextView>(R.id.tvDriverStatus)
         val driverCard = itemView.findViewById<CardView>(R.id.cvDriverItem)

    }




}