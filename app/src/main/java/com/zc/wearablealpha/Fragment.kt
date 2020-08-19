package com.zc.wearablealpha

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Fragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_rel)
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
}
