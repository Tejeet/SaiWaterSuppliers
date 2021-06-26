package com.tejeet.saiwatersuppliers.Ui.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.tejeet.saiwatersuppliers.Data.ModelDTO.MyCustomer
import com.tejeet.saiwatersuppliers.R

class SocietyAdapter(private var dataList:MutableList<MyCustomer>, private val itemClickListener: SocietyItemClickListener)
    :RecyclerView.Adapter<SocietyAdapter.SocietyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocietyViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_society,parent,false)
        return   SocietyViewHolder(view)
    }

    override fun onBindViewHolder(holder: SocietyViewHolder, position: Int) {

        holder.apply {

            societyName.text = dataList[position].societyname
            customerName.text = "Chairman : ${dataList[position].customername}"

            constraintSociety.setOnClickListener {
                itemClickListener.onSocietyItemClicked(dataList[position])

            }
        }
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    fun updateData( newData:MutableList<MyCustomer>){
        dataList = newData
        notifyDataSetChanged()
    }


    class SocietyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

         val societyName = itemView.findViewById<TextView>(R.id.societyName)
         val customerName = itemView.findViewById<TextView>(R.id.customerName)
         val constraintSociety = itemView.findViewById<ConstraintLayout>(R.id.constraintSociety)

    }




}