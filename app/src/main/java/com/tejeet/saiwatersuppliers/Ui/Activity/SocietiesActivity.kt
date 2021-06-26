package com.tejeet.saiwatersuppliers.Ui.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tejeet.saiwatersuppliers.Constant.ResultData
import com.tejeet.saiwatersuppliers.Data.ModelDTO.MyCustomer
import com.tejeet.saiwatersuppliers.Ui.Adapters.SocietyAdapter
import com.tejeet.saiwatersuppliers.Ui.Adapters.SocietyItemClickListener
import com.tejeet.saiwatersuppliers.Viewmodel.MainViewModel
import com.tejeet.saiwatersuppliers.databinding.ActivitySovietiesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SocietiesActivity : AppCompatActivity(), SocietyItemClickListener {

    private val mainViewModel: MainViewModel by viewModels()
    lateinit var binding:ActivitySovietiesBinding
    lateinit var societyAdapter: SocietyAdapter
    var customerList:MutableList<MyCustomer> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySovietiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerview()

        mainViewModel.getAllUser("1","tejeetm@gmail.com")
            .observe(this, Observer {response->

                when(response){
                    is ResultData.Loading -> {

                    }
                    is ResultData.Success -> {
                        response.data?.let {
                            societyAdapter.updateData(it)
                        }
                    }
                    is ResultData.Exception ->{

                    }
                }
        })


    }

    private fun setUpRecyclerview() {

            societyAdapter = SocietyAdapter(customerList,this)
            binding.userRecyclerview.layoutManager = LinearLayoutManager(this)
            binding.userRecyclerview.adapter = societyAdapter

    }

    override fun onSocietyItemClicked(customer: MyCustomer) {

    }
}