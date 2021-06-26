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
import com.tejeet.saiwatersuppliers.Data.ModelDTO.TankerDriver
import com.tejeet.saiwatersuppliers.R
import com.tejeet.saiwatersuppliers.Ui.Adapters.DriverItemClickListner
import com.tejeet.saiwatersuppliers.Ui.Adapters.DriversAdapter
import com.tejeet.saiwatersuppliers.Ui.Adapters.SocietyAdapter
import com.tejeet.saiwatersuppliers.Viewmodel.MainViewModel
import com.tejeet.saiwatersuppliers.databinding.ActivityDriversBinding
import com.tejeet.saiwatersuppliers.databinding.ActivitySovietiesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DriversActivity : AppCompatActivity() , DriverItemClickListner{

    private val mainViewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityDriversBinding
    lateinit var driversAdapter: DriversAdapter
    var driversList :MutableList<TankerDriver> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDriversBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerview()

        assert(
            supportActionBar != null //null check
        )
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mainViewModel.getAllDrivers("1","tejeetm@gmail.com")
            .observe(this, Observer {response->

                when(response){
                    is ResultData.Loading -> {
                        binding.lottieLoading.visibility = View.VISIBLE
                    }
                    is ResultData.Success -> {
                        binding.lottieLoading.visibility = View.GONE
                        response.data?.let {
                            driversAdapter.updateData(it)
                        }
                    }
                    is ResultData.Exception ->{

                    }
                }
            })

        binding.floatingBtnAddDriver.setOnClickListener {
            startActivity(Intent(this@DriversActivity, AddDriverActivity::class.java))
            overridePendingTransition(R.anim.enter_first, R.anim.enter_second)
            finish()
        }
    }

    private fun setUpRecyclerview() {

        driversAdapter = DriversAdapter(driversList,this)
        binding.driversRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.driversRecyclerView.adapter = driversAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }


    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@DriversActivity, MainActivity::class.java))
        overridePendingTransition(R.anim.exit_first, R.anim.exit_second)
        finish()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@DriversActivity, MainActivity::class.java))
        overridePendingTransition(R.anim.exit_first, R.anim.exit_second)
        finish()
    }

    override fun OnDriverItemClickListner(tankerDriver: TankerDriver) {

        val intent = Intent(this@DriversActivity, ManageDrivers::class.java)
        intent.putExtra("DATA", "OK")
        startActivity(intent)
        overridePendingTransition(R.anim.exit_first, R.anim.exit_second)
        finish()

    }
}