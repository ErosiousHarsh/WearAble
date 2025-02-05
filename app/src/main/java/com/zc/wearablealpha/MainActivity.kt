package com.zc.wearablealpha

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var btnSignUp: Button
    private lateinit var btnSignIn: Button
    private lateinit var rlUser: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn",false)
        setContentView(R.layout.activity_main)

        if(isLoggedIn) {
            startActivity(Intent(this, Fragment::class.java))
            finish()
        }

        rlUser = findViewById(R.id.rlUser)
        btnSignIn = findViewById(R.id.btnSignIn)
        btnSignUp = findViewById(R.id.btnSignUp)

        btnSignIn.setOnClickListener {
            startActivity(Intent(this, ActivitySignin::class.java))
        }

        btnSignUp.setOnClickListener {
            startActivity(Intent(this, ActivitySignup::class.java))

        }

    }
    private fun toast(msg: String) {
        Toast.makeText(this@MainActivity, msg,Toast.LENGTH_LONG).show()
    }
    override fun onBackPressed() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setMessage("Do you want to Exit?")
        builder.setPositiveButton("Yes"
        ) { _, _ -> //if user pressed "yes", then he is allowed to exit from application
            finish()
        }
        builder.setNegativeButton("No"
        ) { dialog, _ -> //if user select "No", just cancel this dialog and continue with app
            dialog.cancel()
        }
        val alert: AlertDialog = builder.create()
        alert.show()
    }

}
