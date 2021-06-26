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
import com.google.android.material.snackbar.Snackbar
import com.tejeet.saiwatersuppliers.R
import com.tejeet.saiwatersuppliers.Viewmodel.MainViewModel
import com.tejeet.saiwatersuppliers.databinding.ActivityAddUserBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddUserActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddUserBinding

    private val mainViewModel: MainViewModel by viewModels()
    private var progressDialog: ProgressDialog? = null
    private val TAG = "tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        assert(
            supportActionBar != null //null check
        )
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        progressDialog = ProgressDialog(this)

        binding.addNewSociety.setOnClickListener {

            vibrateSense()
            progressDialog?.setTitle("Creating User Account")
            progressDialog?.show()

            binding.apply {
                val customerName = inchargeName.text.toString()
                val customerSocietyName = societyName.text.toString()
                val customerEmail = customerEmail.text.toString()
                val customerMobile = inchargeMobile.text.toString()
                val customerAddress = societyAddress.text.toString()
                val customerPass = inchargePassword.text.toString()
                val tankerRate = tankerRate.text.toString()



                CoroutineScope(Dispatchers.Main).launch {
                    val response = mainViewModel.addUser(
                        customerSocietyName.toString(),
                        customerName.toString(),
                        customerEmail.toString(),
                        customerMobile.toString(),
                        customerAddress.toString(),
                        tankerRate.toString(),
                        customerPass.toString(),
                        "admin"
                    )

                    Log.d(TAG, "Response ${response.body()}")

                    if (response.body()?.requeststatus == 1){
                        progressDialog!!.dismiss()
                        startActivity(Intent(this@AddUserActivity, SocietiesActivity::class.java))
                        overridePendingTransition(R.anim.enter_first, R.anim.enter_second)
                        finish()
                    }
                    else if (response.body()?.requeststatus == 22){
                        progressDialog!!.dismiss()
                        vibrateSense()

                        val snack = Snackbar.make(
                            findViewById(android.R.id.content),
                            "Customer Already Exist",
                            Snackbar.LENGTH_LONG
                        )
                        snack.setTextColor(Color.WHITE)
                        snack.setAction("Close", View.OnClickListener {
                            snack.dismiss()
                        })
                        snack.setActionTextColor(Color.WHITE)
                        snack.show()
                    }
                    else {
                        progressDialog!!.dismiss()
                        vibrateSense()

                        val snack = Snackbar.make(
                            findViewById(android.R.id.content),
                            "Error In Creating User Acoount",
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


            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }


    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@AddUserActivity, SocietiesActivity::class.java))
        overridePendingTransition(R.anim.exit_first, R.anim.exit_second)
        finish()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@AddUserActivity, SocietiesActivity::class.java))
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
