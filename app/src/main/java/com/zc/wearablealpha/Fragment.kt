package com.zc.wearablealpha

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Fragment : AppCompatActivity() {

    private lateinit var username: TextView
    private lateinit var btnLogout: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_rel)

        sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)

        btnLogout = findViewById(R.id.btnLogout)
        username = findViewById(R.id.username)
        username.text = intent.getStringExtra("username")

        btnLogout.setOnClickListener {
            sharedPreferences.edit().putBoolean("isLoggedIn",false).apply()
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
    override fun onBackPressed() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setMessage("Do you want to Exit?")
        builder.setPositiveButton("Yes"
        ) { _, _ -> finish() }
        builder.setNegativeButton("No"
        ) { dialog, _ -> dialog.cancel() }
        val alert: AlertDialog = builder.create()
        alert.show()
    }
    override fun onRestart() {
        super.onRestart()
        finishAffinity()
    }
}
