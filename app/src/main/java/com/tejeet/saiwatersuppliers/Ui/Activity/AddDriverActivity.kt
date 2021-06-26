package com.tejeet.saiwatersuppliers.Ui.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.tejeet.saiwatersuppliers.Constant.ResultData
import com.tejeet.saiwatersuppliers.R
import com.tejeet.saiwatersuppliers.Viewmodel.MainViewModel
import com.tejeet.saiwatersuppliers.databinding.ActivityAddDriverBinding
import com.tejeet.saiwatersuppliers.databinding.ActivityAddUserBinding
import com.tejeet.saiwatersuppliers.databinding.ActivityDriversBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddDriverActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddDriverBinding
    private val mainViewModel: MainViewModel by viewModels()
    private var progressDialog: ProgressDialog? = null
    private val TAG = "tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_driver)

        binding = ActivityAddDriverBinding.inflate(layoutInflater)
        setContentView(binding.root)



        assert(
            supportActionBar != null //null check
        )
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        progressDialog = ProgressDialog(this)

        binding.addNewDriver.setOnClickListener {

            if (isNetworkConnected()) {


                if (!binding.driverName.text.toString().isEmpty() && !binding.driverMobile.text.toString().isEmpty() && !binding.driverPassword.text.toString().isEmpty()) {

                    vibrateSense()
                    progressDialog?.setTitle("Creating Driver Account")
                    progressDialog?.show()

                    CoroutineScope(Dispatchers.Main).launch {
                        val response = mainViewModel.addDriver(binding.driverName.text.toString(),binding.driverEmail.text.toString(),binding.driverEmail.text.toString(),binding.driverPassword.text.toString())

                        Log.d(TAG, " Response is ${response.body()}")

                        if (response.body()?.requeststatus == 1){
                            progressDialog!!.dismiss()
                            startActivity(Intent(this@AddDriverActivity, DriversActivity::class.java))
                            overridePendingTransition(R.anim.enter_first, R.anim.enter_second)
                            finish()
                        }
                    }



                } else {
                    vibrateSense()

                    val snack = Snackbar.make(
                        findViewById(android.R.id.content),
                        "Please Check the Network",
                        Snackbar.LENGTH_LONG
                    )
                    snack.setTextColor(Color.WHITE)
                    snack.setAction("Close", View.OnClickListener {
                        snack.dismiss()
                    })
                    snack.setActionTextColor(Color.WHITE)
                    snack.show()
                }
            }

            else {
                val snack = Snackbar.make(
                    findViewById(android.R.id.content),
                    "Please Check the Network",
                    Snackbar.LENGTH_LONG
                )
                snack.setAction("Close", View.OnClickListener {
                    snack.dismiss()
                })
                snack.setActionTextColor(Color.GREEN)
                snack.show()
            }



        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }


    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@AddDriverActivity, DriversActivity::class.java))
        overridePendingTransition(R.anim.exit_first, R.anim.exit_second)
        finish()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@AddDriverActivity, DriversActivity::class.java))
        overridePendingTransition(R.anim.exit_first, R.anim.exit_second)
        finish()
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }

    private fun vibrateSense() {
        val v = getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            //deprecated in API 26
            v.vibrate(50)
        }
    }
}