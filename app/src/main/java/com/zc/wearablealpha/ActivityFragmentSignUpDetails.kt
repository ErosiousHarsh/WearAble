package com.zc.wearablealpha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class ActivityFragmentSignUpDetails : FragmentChangeListener,Fragment() {

    //Variables declaration
    private lateinit var matTxtFirstName: com.google.android.material.textfield.TextInputLayout
    private lateinit var edtFirstName: EditText

    private lateinit var matTxtLastName: com.google.android.material.textfield.TextInputLayout
    private lateinit var edtLastName: EditText

    private lateinit var matTxtEmail: com.google.android.material.textfield.TextInputLayout
    private lateinit var edtEmail: EditText

    private lateinit var matTxtPassword: com.google.android.material.textfield.TextInputLayout
    private lateinit var edtPassword: EditText

    private lateinit var matTxtPasswordConfirm: com.google.android.material.textfield.TextInputLayout
    private lateinit var edtPasswordConfirm: EditText

    private lateinit var btnDetailsNext: Button
    private lateinit var bundle: Bundle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_activity_signup_details, container, false)

        //Variables initialization
        matTxtFirstName = rootView.findViewById(R.id.materialTxtFirstName)
        edtFirstName = rootView.findViewById(R.id.edtFirstName)

        matTxtLastName = rootView.findViewById(R.id.materialTxtLastName)
        edtLastName = rootView.findViewById(R.id.edtLastName)

        matTxtEmail = rootView.findViewById(R.id.materialTxtEmail)
        edtEmail = rootView.findViewById(R.id.edtEmail)

        matTxtPassword = rootView.findViewById(R.id.materialTxtPassword)
        edtPassword = rootView.findViewById(R.id.edtPassword)

        matTxtPasswordConfirm = rootView.findViewById(R.id.materialTxtPasswordConfirm)
        edtPasswordConfirm = rootView.findViewById(R.id.edtPasswordConfirm)

        btnDetailsNext = rootView.findViewById(R.id.btnDetailsNext)

        bundle = Bundle()

        btnDetailsNext.setOnClickListener {
            //User Details validation (lot's of if-else)
            if(edtFirstName.text.toString().isNotEmpty()) {

                bundle.putString("firstName",edtFirstName.text.toString())
                matTxtFirstName.isErrorEnabled = false

                if (edtLastName.text.toString().isNotEmpty()) {

                    bundle.putString("lastName",edtLastName.text.toString())
                    matTxtLastName.isErrorEnabled = false

                    if(edtPassword.text.toString().length >7) {

                        bundle.putString("pass",edtPassword.text.toString())
                        matTxtPassword.isErrorEnabled = false

                        if(edtPassword.text.toString().equals(edtPasswordConfirm.text.toString())) {
                            bundle.putString("passConfirm",edtPasswordConfirm.text.toString())
                            matTxtPasswordConfirm.isErrorEnabled = false
                            showOtherFragment()
                        } else {
                            matTxtPasswordConfirm.error = "Password's do not match"
                        }

                    } else {
                        matTxtPassword.error = "Password's length must be greater than 7"
                    }

                } else {
                    matTxtLastName.error = "Enter valid last name"
                }

            } else {
                matTxtFirstName.error = "Enter valid first name"
            }
            if(edtEmail.text.toString().isNotEmpty()) {
                bundle.putString("email", edtEmail.text.toString())
            }

        }

        return rootView
    }

    private fun showOtherFragment() {
        val fc = activity as FragmentChangeListener?
        fc?.replaceFragment(bundle)
    }

}