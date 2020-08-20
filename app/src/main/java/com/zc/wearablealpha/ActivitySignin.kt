package com.zc.wearablealpha

import android.util.Log
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class ActivitySignin : AppCompatActivity() {

    private lateinit var edtPhoneOrEmail: EditText
    private lateinit var edtPasswordLogin: EditText
    private lateinit var btnSigninNext: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        edtPhoneOrEmail = findViewById(R.id.edtPhoneLogin)
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin)
        btnSigninNext = findViewById(R.id.btnSigninNext)


        btnSigninNext.setOnClickListener {

            if(edtPhoneOrEmail.text.toString().isNotEmpty() && edtPasswordLogin.text.toString().length > 7) {
                val phone = edtPhoneOrEmail.text.toString()
                val docRef = db.collection("users").document(phone)
                docRef.get().addOnSuccessListener { document ->
                    if(document != null) {
                        if(document.getString("password").toString() == edtPasswordLogin.text.toString()) {
                            username = document.getString("FirstName").toString()
                            toast("Success")
                            onPhoneSuccess()
                        }
                    } else {
                        toast("Not found")
                    }
                }.addOnFailureListener {
                    toast("Failure")
                }
            }

        }
    }

    private fun onPhoneSuccess() {
        startActivity(Intent(this, Fragment::class.java).putExtra("username",username))
    }
    private fun toast(msg: String) {
        Toast.makeText(this@ActivitySignin, msg,Toast.LENGTH_LONG).show()
    }

}