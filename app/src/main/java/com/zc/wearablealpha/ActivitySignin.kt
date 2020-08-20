package com.zc.wearablealpha

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


class ActivitySignin : AppCompatActivity() {

    private lateinit var edtPhoneOrEmail: EditText
    private lateinit var edtPasswordLogin: EditText
    private lateinit var btnSigninNext: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        auth = FirebaseAuth.getInstance()

        edtPhoneOrEmail = findViewById(R.id.edtPhoneOrEmail)
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin)
        btnSigninNext = findViewById(R.id.btnSigninNext)

        btnSigninNext.setOnClickListener {
            if (edtPhoneOrEmail.text.isNotEmpty() && edtPasswordLogin.text.isNotEmpty() ) {
                auth.signInWithEmailAndPassword(edtPhoneOrEmail.text.toString(),edtPasswordLogin.text.toString())
                    .addOnCompleteListener(this,
                        OnCompleteListener<AuthResult?> { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                val user: FirebaseUser? = auth.currentUser
                                startActivity(Intent(this,Fragment::class.java).putExtra("username",user?.displayName.toString()))
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(
                                    this@ActivitySignin, "Authentication failed.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        })
            }
        }

    }

}