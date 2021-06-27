package com.tejeet.saiwatersuppliers.Ui.Activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AlertDialog
import com.tejeet.beets.data.constant.AppPreferences
import com.tejeet.saiwatersuppliers.R

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        assert(
            supportActionBar != null //null check
        )
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }


    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@SettingActivity, MainActivity::class.java))
        overridePendingTransition(R.anim.exit_first, R.anim.exit_second)
        finish()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@SettingActivity, MainActivity::class.java))
        overridePendingTransition(R.anim.exit_first, R.anim.exit_second)
        finish()
    }
}