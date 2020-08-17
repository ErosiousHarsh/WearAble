package com.zc.wearablealpha

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_user.view.*

class MainActivity : Loading, AppCompatActivity() {

    private lateinit var btnSignUp: Button
    private lateinit var btnSignIn: Button
    private lateinit var rlUser: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

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
        builder.setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, which -> //if user pressed "yes", then he is allowed to exit from application
                    finish()
                })
        builder.setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, which -> //if user select "No", just cancel this dialog and continue with app
                    dialog.cancel()
                })
        val alert: AlertDialog = builder.create()
        alert.show()
    }

    private fun startLoading(v: View) {
        v.alpha = 0.7F
        v.progress_circular.visibility = View.VISIBLE
    }

    private fun stopLoading(v: View) {
        v.alpha = 1F
        v.progress_circular.visibility = View.INVISIBLE
    }
}
