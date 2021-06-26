package com.tejeet.saiwatersuppliers.Ui.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tejeet.saiwatersuppliers.Constant.ResultData
import com.tejeet.saiwatersuppliers.Data.ModelDTO.MyCustomer
import com.tejeet.saiwatersuppliers.R
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

        assert(
            supportActionBar != null //null check
        )
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        setUpRecyclerview()

        mainViewModel.getAllUser("1","tejeetm@gmail.com")
            .observe(this, Observer {response->

                when(response){
                    is ResultData.Loading -> {
                        binding.lottieLoading.visibility = View.VISIBLE
                    }
                    is ResultData.Success -> {
                        binding.lottieLoading.visibility = View.GONE

                        response.data?.let {
                            societyAdapter.updateData(it)
                        }
                    }
                    is ResultData.Exception ->{

                    }
                }
        })

        binding.floatingBtnAddSociety.setOnClickListener {
            startActivity(Intent(this@SocietiesActivity, AddUserActivity::class.java))
            overridePendingTransition(R.anim.enter_first, R.anim.enter_second)
            finish()
        }


    }

    private fun setUpRecyclerview() {

            societyAdapter = SocietyAdapter(customerList,this)
            binding.userRecyclerview.layoutManager = LinearLayoutManager(this)
            binding.userRecyclerview.adapter = societyAdapter

    }

    override fun onSocietyItemClicked(customer: MyCustomer) {



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }


    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@SocietiesActivity, MainActivity::class.java))
        overridePendingTransition(R.anim.exit_first, R.anim.exit_second)
        finish()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@SocietiesActivity, MainActivity::class.java))
        overridePendingTransition(R.anim.exit_first, R.anim.exit_second)
        finish()
    }
}