//package com.zc.wearablealpha
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.KeyEvent
//import android.widget.*
//import androidx.appcompat.app.AppCompatActivity
//
//
//class LoginPhone : AppCompatActivity() {
//
//    private lateinit var edtPhone: EditText
//    private lateinit var imgBack: ImageView
//    private lateinit var txtIdkNumber: TextView
//    private lateinit var btnPhoneNext: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.phone_login)
//
//        imgBack = findViewById(R.id.imgBack)
//        edtPhone = findViewById(R.id.edtPhone)
//        txtIdkNumber = findViewById(R.id.txtIdkNumber)
//        btnPhoneNext = findViewById(R.id.btnPhoneNext)
//
//
//        //showSoftKeyboard(edtPhone)
//        //edtPhone.requestFocus()
//
//        txtIdkNumber.setOnClickListener {
//            toast("You don't know your number")
//        }
//
//        imgBack.setOnClickListener {
//            startActivity(Intent(this, MainActivity::class.java))
//        }
//        edtPhone.setOnKeyListener { _, i, keyEvent ->
//            if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
//                if (edtPhone.text.toString().length == 10) {
//                    startActivity(Intent(this, VerifyPhone::class.java).putExtra("phone",edtPhone.text.toString()))
//                } else {
//                    edtPhone.error = "Invalid number"
//                }
//                true
//            } else false
//        }
//
//        btnPhoneNext.setOnClickListener {
//            if(edtPhone.text.toString().length < 10) {
//                edtPhone.error = "Enter correct number"
//            }
//        }
//
//    }
//
////    private fun toastException(msg: Exception) {
////        Toast.makeText(this@LoginPhone, "$msg", Toast.LENGTH_LONG).show()
////    }
//
//    private fun toast(msg: String) {
//        Toast.makeText(this@LoginPhone, msg, Toast.LENGTH_LONG).show()
//    }
//}