package com.tejeet.saiwatersuppliers.Ui.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addDietPlanBtn.setOnClickListener {


        binding.apply {
            val customerName = customerName.text
            val customerSocietyName = customerSocietyName.text
            val customerEmail = customerEmail.text
            val customerMobile = customerMobile.text
            val customerAddress = customerAddress.text
            val tankerRate = tankerRate.text



            if (customerName.isEmpty()) {
                binding.customerName.error = "please fill Name"

            } else if (customerSocietyName.isEmpty()) {
                binding.customerSocietyName.error = "please fill Customer Name"

            } else if (customerEmail.isEmpty()) {
                binding.customerEmail.error = "please enter hashtag"

            } else if (customerMobile.isEmpty()) {
                binding.customerMobile.error = "please enter hashtag"

            } else if (customerAddress.isEmpty()) {
                binding.customerAddress.error = "please enter hashtag"

            } else if (tankerRate.isEmpty()) {
                binding.tankerRate.error = "please enter hashtag"

            } else {

                CoroutineScope(Dispatchers.Main).launch {
                    val response = mainViewModel.addUser(
                        customerSocietyName.toString(),
                        customerName.toString(),
                        customerEmail.toString(),
                        customerMobile.toString(),
                        customerAddress.toString(),
                        tankerRate.toString(),
                        "admin"
                    )

                    val rr = response
                }


            }
        }
        }
    }
}