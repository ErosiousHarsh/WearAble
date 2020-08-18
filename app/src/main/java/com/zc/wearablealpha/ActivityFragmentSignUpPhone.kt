package com.zc.wearablealpha

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_activity_signup_phone.*

class ActivityFragmentSignUpPhone(): FragmentChangeListener,Fragment() {

    private lateinit var matTxtPhone: com.google.android.material.textfield.TextInputLayout
    private lateinit var edtPhone: EditText


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_activity_signup_phone, container, false)

        matTxtPhone = rootView.findViewById(R.id.materialTxtPhone)
        edtPhone = rootView.findViewById(R.id.edtPhone)

        edtPhone.requestFocus()

        edtPhone.setOnKeyListener { _, i, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                if (edtPhone.text.toString().length == 10) {
                    startActivity(Intent(activity?.baseContext, VerifyPhone::class.java).putExtra("phone",edtPhone.text.toString()))
                } else {
                    edtPhone.error = "Invalid number"
                }
                true
            } else false
        }

        return rootView
    }

}