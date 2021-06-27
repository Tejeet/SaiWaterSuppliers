package com.tejeet.saiwatersuppliers.Ui.Activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.tejeet.beets.data.constant.AppPreferences
import com.tejeet.saiwatersuppliers.R
import com.tejeet.saiwatersuppliers.Viewmodel.MainViewModel
import com.tejeet.saiwatersuppliers.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val mToggle: ActionBarDrawerToggle? = null

    private val TAG = "tag"

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sideNavigationView.setNavigationItemSelectedListener(this)


        AppPreferences.init(this)


        //setSupportActionBar()
        val toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()

        navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.orderFragment,
                R.id.profileFragment
            )
        )

        binding.bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

        loadProfile()
        init()


    }

    fun loadProfile(){


    }


    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START)
        } else {
            showCloseAppDialogue()
            super.onBackPressed()
        }
    }

    private fun showCloseAppDialogue(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Close App ?")
        builder.setMessage("Do You Wants to Close the App")

        builder.setPositiveButton("YES", DialogInterface.OnClickListener { dialog, id ->
            finish()
        })

        builder.setNegativeButton("NO", DialogInterface.OnClickListener { dialog, id ->
            dialog.dismiss()

        })

        builder.setCancelable(true)
        builder.show()
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {

            R.id.homeFragment -> {
                binding.drawer.closeDrawer(GravityCompat.START)
                overridePendingTransition(R.anim.enter_first, R.anim.enter_second)
                true
            }

            R.id.societyFragment -> {

                binding.drawer.closeDrawer(GravityCompat.START)
                startActivity(Intent(this@MainActivity, SocietiesActivity::class.java))
                overridePendingTransition(R.anim.enter_first, R.anim.enter_second)
                finish()
                true
            }
            R.id.driversFragment -> {
                binding.drawer.closeDrawer(GravityCompat.START)
                startActivity(Intent(this@MainActivity, DriversActivity::class.java))
                overridePendingTransition(R.anim.enter_first, R.anim.enter_second)
                finish()
                true
            }
            R.id.revenueFargment -> {
                binding.drawer.closeDrawer(GravityCompat.START)
                startActivity(Intent(this@MainActivity, RevenueActivity::class.java))
                overridePendingTransition(R.anim.enter_first, R.anim.enter_second)
                finish()
                true
            }
            R.id.settingFragment -> {
                binding.drawer.closeDrawer(GravityCompat.START)
                startActivity(Intent(this@MainActivity, SettingActivity::class.java))
                overridePendingTransition(R.anim.enter_first, R.anim.enter_second)
                finish()
                true
            }
            R.id.logout -> {
                binding.drawer.closeDrawer(GravityCompat.START)
                showLogoutDiaogue()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showLogoutDiaogue(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout")
        builder.setMessage("Are sure wants to logout")

        builder.setPositiveButton("YES", DialogInterface.OnClickListener { dialog, id ->
            AppPreferences.isLoggedIn = "NO"
            logout()
        })

        builder.setNegativeButton("NO", DialogInterface.OnClickListener { dialog, id ->
            dialog.dismiss()

        })

        builder.setCancelable(true)
        builder.show()
    }

    fun logout() {

        finish()
        System.exit(0)
        finishAffinity()

    }

    fun init(){

        if (!AppPreferences.isTokenUpdated.equals("YES")){
            CoroutineScope(Dispatchers.Main).launch {
                val response = mainViewModel.userFBTokenUpdate(
                    AppPreferences.userEmail.toString(),
                    AppPreferences.userID.toString(),
                    AppPreferences.userFirebaseToken.toString()
                )

                Log.d(TAG, "Firebase Token Update response : ${response.body()}")
            }
        }

    }

}