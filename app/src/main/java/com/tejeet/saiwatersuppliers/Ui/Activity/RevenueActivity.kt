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
import com.tejeet.saiwatersuppliers.Data.ModelDTO.RevenueDetail
import com.tejeet.saiwatersuppliers.Data.ModelDTO.TankerDriver
import com.tejeet.saiwatersuppliers.R
import com.tejeet.saiwatersuppliers.Ui.Adapters.AllRevenueAdapter
import com.tejeet.saiwatersuppliers.Ui.Adapters.DriversAdapter
import com.tejeet.saiwatersuppliers.Ui.Adapters.RevenueItemClickListener
import com.tejeet.saiwatersuppliers.Viewmodel.MainViewModel
import com.tejeet.saiwatersuppliers.databinding.ActivityDriversBinding
import com.tejeet.saiwatersuppliers.databinding.ActivityRevenueBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RevenueActivity : AppCompatActivity() , RevenueItemClickListener{
    private val TAG = "tag"

    private val mainViewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityRevenueBinding
    lateinit var revenueAdapter: AllRevenueAdapter
    var revenueList :MutableList<RevenueDetail> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revenue)

        binding = ActivityRevenueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        assert(
            supportActionBar != null //null check
        )
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        setUpRecyclerview()

        mainViewModel.getAllRevenue("1","tejeetm@gmail.com")
            .observe(this, Observer {response->

                when(response){
                    is ResultData.Loading -> {
                        binding.lottieLoading.visibility = View.VISIBLE
                    }
                    is ResultData.Success -> {
                        binding.lottieLoading.visibility = View.GONE
                        response.data?.let {
                            revenueAdapter.updateData(it)
                        }
                    }
                    is ResultData.Exception ->{

                    }
                }
            })



    }

    private fun setUpRecyclerview() {

        revenueAdapter = AllRevenueAdapter(revenueList,this)
        binding.revenueRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.revenueRecyclerView.adapter = revenueAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }


    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@RevenueActivity, MainActivity::class.java))
        overridePendingTransition(R.anim.exit_first, R.anim.exit_second)
        finish()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@RevenueActivity, MainActivity::class.java))
        overridePendingTransition(R.anim.exit_first, R.anim.exit_second)
        finish()
    }

    override fun onRevenueItemClick(revenueDetail: RevenueDetail) {

        val intent = Intent(this@RevenueActivity, SocietyBillDetails::class.java)
        intent.putExtra("CUSTOMER_ID", revenueDetail.uid)
        intent.putExtra("TANKER_RATE", revenueDetail.tankerRate)
        intent.putExtra("TANKER_COUNT", revenueDetail.totalOrders)
        startActivity(intent)
        overridePendingTransition(R.anim.exit_first, R.anim.exit_second)
        finish()

    }
}