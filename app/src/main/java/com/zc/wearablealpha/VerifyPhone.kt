package com.zc.wearablealpha

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chaos.view.PinView
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.TimeUnit

class VerifyPhone : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var code: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var pinPhoneOtp: PinView
    private lateinit var fStore: FirebaseFirestore
    private var verificationCompleted = 0
    private var storedVerificationId: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.phone_verify)

        auth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance()

        pinPhoneOtp = findViewById(R.id.pinPhoneOtp)

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                toast("Verification completed")
                signIn(credential)
                code = credential.smsCode.toString()
                verificationCompleted = 1
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
    }

    //User manually enters OTP
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    toast("Logged in successfully 104")
                    verificationCompleted = 1
                    createUser()
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
                    toast("Logged in successfully 118")
                    createUser()
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

    private fun createUser() {
        if(verificationCompleted == 1) {

            val bundle: Bundle? = intent.getBundleExtra("details")
            val userId: String? = auth.currentUser?.uid
            lateinit var documentRef: DocumentReference
            val userHash: MutableMap<String, Any> = HashMap()
            userHash["ad"] = 1
            userHash["FirstName"] = bundle?.getString("fName").toString()
            userHash["LastName"] = bundle?.getString("lName").toString()
            userHash["Email"] = bundle?.getString("email").toString()
            userHash["phone"] = bundle?.getString("phone").toString()

            auth.createUserWithEmailAndPassword(bundle?.getString("email").toString(),bundle?.getString("pass").toString()).addOnCompleteListener {
                documentRef = fStore.collection("users").document(userId.toString())
                documentRef.set(userHash)
            }
            startActivity(Intent(this,Fragment::class.java))
        }
    }
}
