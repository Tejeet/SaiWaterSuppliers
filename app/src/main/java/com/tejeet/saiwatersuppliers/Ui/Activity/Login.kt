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
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.tejeet.beets.data.constant.AppPreferences
import com.tejeet.saiwatersuppliers.R
import com.tejeet.saiwatersuppliers.Viewmodel.MainViewModel
import com.tejeet.saiwatersuppliers.databinding.ActivityAddDriverBinding
import com.tejeet.saiwatersuppliers.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Login : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val mainViewModel: MainViewModel by viewModels()

    private var progressDialog: ProgressDialog? = null
    private val TAG = "tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppPreferences.init(this)

        progressDialog = ProgressDialog(this)

        binding.addNewDriver.setOnClickListener {

            if (isNetworkConnected()) {


                if (!binding.userId.text.toString().isEmpty() && !binding.userPass.text.toString().isEmpty() ) {

                    vibrateSense()
                    progressDialog?.setTitle("Logging In")
                    progressDialog?.show()

                    CoroutineScope(Dispatchers.Main).launch {
                        val response = mainViewModel.userLogin(
                            binding.userId.text.toString(),
                            binding.userPass.text.toString(),
                            AppPreferences.userFirebaseToken.toString(),
                            Build.MANUFACTURER.toString(),
                            Build.MODEL.toString(),
                            Build.VERSION.SDK_INT.toString(),
                            Build.VERSION.RELEASE
                            )

                        Log.d(TAG, " Response is ${response.body()}")

                        if (response.body()?.requeststatus == 1){
                            progressDialog!!.dismiss()
                            AppPreferences.isLoggedIn = "YES"
                            AppPreferences.userID = response?.body()?.userid.toString().toInt()
                            AppPreferences.setUser(response?.body()?.username.toString(),response?.body()?.email.toString(),response?.body()?.pass.toString(),response?.body()?.mobile.toString())
                            startActivity(Intent(this@Login, MainActivity::class.java))
                            overridePendingTransition(R.anim.enter_first, R.anim.enter_second)
                            finish()
                        }
                        else{
                            vibrateSense()

                            val snack = Snackbar.make(
                                findViewById(android.R.id.content),
                                "Wrong User ID & Password",
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

    override fun onBackPressed() {
        super.onBackPressed()

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