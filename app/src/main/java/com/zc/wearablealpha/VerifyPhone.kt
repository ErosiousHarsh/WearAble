package com.zc.wearablealpha

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chaos.view.PinView
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class VerifyPhone : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var code: String
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var pinPhoneOtp: PinView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.phone_verify)

        auth = FirebaseAuth.getInstance();

        pinPhoneOtp = findViewById(R.id.pinPhoneOtp)

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                toast("Verification completed")
                signIn(credential)
                code = credential.smsCode.toString()
                toast(code)
                startActivity(Intent(this@VerifyPhone, Fragment::class.java))
            }
            override fun onVerificationFailed(e: FirebaseException) {
                toast("Server error, try again later, verification failed")

                if (e is FirebaseAuthInvalidCredentialsException) {
                    toast("Server error, try again later, credentials exception")

                    // [END_EXCLUDE]
                } else if (e is FirebaseTooManyRequestsException) {
                    toast("Too many requests")

                }
            }
            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                storedVerificationId = verificationId
                resendToken = token
            }
        }

        pinPhoneOtp.setOnKeyListener { _, i, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                if (pinPhoneOtp.text.toString().length == 6) {
                    val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                        storedVerificationId!!,
                        pinPhoneOtp.text.toString()
                    )
                    signInWithPhoneAuthCredential(credential)
                } else {

                }
                true
            } else false
        }
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+91$phoneNumber",
            60,
            TimeUnit.SECONDS,
            this,
            callbacks)
        toast("+91$phoneNumber")
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    toast("Logged in successfully")
                    startActivity(Intent(this,Fragment::class.java))
                    // Sign in success, update UI with the signed-in user's information
                    val user = task.result?.user
                    // ...
                } else {
                    toast("Verified but sign in failed")
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        toast("Wrong OTP")
                    }
                }
            }
    }

    private fun signIn(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                    task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    toast("Logged in successfully")
                    startActivity(Intent(this,Fragment::class.java))
                }
            }
    }

    private fun toastException(msg: Exception) {
        Toast.makeText(this@VerifyPhone, "$msg", Toast.LENGTH_LONG).show()
    }

    private fun toast(msg: String) {
        Toast.makeText(this@VerifyPhone, msg, Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        val phone1 = intent.getStringExtra("phone")!!
        startPhoneNumberVerification(phone1)

    }
}