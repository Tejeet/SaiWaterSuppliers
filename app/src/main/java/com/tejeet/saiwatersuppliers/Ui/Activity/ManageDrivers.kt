package com.tejeet.saiwatersuppliers.Ui.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.tejeet.saiwatersuppliers.Data.ModelDTO.TankerDriver
import com.tejeet.saiwatersuppliers.R
import com.tejeet.saiwatersuppliers.databinding.ActivityAddDriverBinding
import com.tejeet.saiwatersuppliers.databinding.ActivityAddUserBinding
import com.tejeet.saiwatersuppliers.databinding.ActivityManageDriversBinding

class ManageDrivers : AppCompatActivity() {
    private  val TAG = "tag"

    lateinit var binding: ActivityManageDriversBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_drivers)

        binding = ActivityManageDriversBinding.inflate(layoutInflater)
        setContentView(binding.root)

        assert(
            supportActionBar != null //null check
        )
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        Log.d(TAG, "Data is ${intent.getStringExtra("DATA")}")

        val inData = intent.getStringExtra("DATA")



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }


    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@ManageDrivers, DriversActivity::class.java))
        overridePendingTransition(R.anim.exit_first, R.anim.exit_second)
        finish()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@ManageDrivers, DriversActivity::class.java))
        overridePendingTransition(R.anim.exit_first, R.anim.exit_second)
        finish()
    }
}