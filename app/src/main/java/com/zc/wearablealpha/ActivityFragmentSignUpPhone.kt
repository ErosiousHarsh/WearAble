package com.zc.wearablealpha

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment

class ActivityFragmentSignUpPhone(): FragmentChangeListener,Fragment() {

    private lateinit var matTxtPhone: com.google.android.material.textfield.TextInputLayout
    private lateinit var edtPhone: EditText
    private lateinit var bundle: Bundle


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_activity_signup_phone, container, false)

        bundle = Bundle()
        bundle.putString("fName",arguments?.getString("firstName"))
        bundle.putString("lName",arguments?.getString("lastName"))
        bundle.putString("email",arguments?.getString("email"))
        bundle.putString("pass",arguments?.getString("pass"))
        bundle.putString("confirmPass",arguments?.getString("confirmPass"))

        matTxtPhone = rootView.findViewById(R.id.materialTxtPhone)
        edtPhone = rootView.findViewById(R.id.edtPhone)

        edtPhone.requestFocus()

        edtPhone.setOnKeyListener { _, i, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                if (edtPhone.text.toString().length == 10) {
                    bundle.putString("phone",edtPhone.text.toString())
                    startActivity(Intent(activity?.baseContext, VerifyPhone::class.java).putExtra("details",bundle))
                } else {
                    edtPhone.error = "Invalid number"
                }
                true
            } else false
        }

        return rootView
    }

}