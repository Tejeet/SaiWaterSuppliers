package com.tejeet.saiwatersuppliers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.tejeet.saiwatersuppliers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sideNavigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, binding.drawer,binding.myToolbar,R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        binding.drawer.addDrawerListener(toggle)

        navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.orderFragment,
                R.id.profileFragment
            )
        )

        binding.bottomNavigationView.setupWithNavController(navController)
        setSupportActionBar(binding.myToolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {

            R.id.societyFragment -> {

                binding.drawer.closeDrawer(GravityCompat.START)
                startActivity(Intent(this@MainActivity, SocietiesActivity::class.java))
                //overridePendingTransition(R.anim.enter_first, R.anim.enter_second)
                true
            }
            R.id.driversFragment -> {
                binding.drawer.closeDrawer(GravityCompat.START)
                startActivity(Intent(this@MainActivity, DriversActivity::class.java))
                //overridePendingTransition(R.anim.enter_first, R.anim.enter_second)
                true
            }
            R.id.settingFragment -> {
                binding.drawer.closeDrawer(GravityCompat.START)
                startActivity(Intent(this@MainActivity, SettingActivity::class.java))
               // overridePendingTransition(R.anim.enter_first, R.anim.enter_second)
                true
            }
            R.id.logout -> {
                binding.drawer.closeDrawer(GravityCompat.START)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}