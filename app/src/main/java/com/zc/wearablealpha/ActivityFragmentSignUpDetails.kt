package com.zc.wearablealpha

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_activity_signup_details.*

class ActivityFragmentSignUpDetails : FragmentChangeListener,Fragment() {

    private lateinit var edtFirstName: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_activity_signup_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnDetailsNext.setOnClickListener {
            Toast.makeText(activity?.baseContext, "btnDeails", Toast.LENGTH_SHORT).show()
            showOtherFragment()
//            startActivity(
//                Intent(activity?.baseContext,ActivityFragmentSignUpPhone::class.java)
//                    .putExtra("firstName",edtFirstName.text.toString())
//                    .putExtra("lastName",edtLastName.text.toString())
//                    .putExtra("email",edtEmail.text.toString())
//                    .putExtra("password",edtPassword.text.toString())
//                    .putExtra("confirmPassword",edtPasswordConfirm.text.toString()))
        }
//        btnDetailsNext.setOnClickListener {
//            Toast.makeText(activity?.baseContext, "btnDeails", Toast.LENGTH_SHORT).show()
//            var bundle = Bundle()
//            bundle.putString("firstName",edtFirstName.text.toString())
//            var secondFrag = ActivityFragmentSignUpPhone()
//            secondFrag.arguments = bundle
//            fragmentManager?.beginTransaction()?.replace(R.id.fragmentSignUp,secondFrag)?.commit()
//        }

    }

    private fun showOtherFragment() {
        val fr: Fragment = ActivityFragmentSignUpPhone()
        val fc = activity as FragmentChangeListener?
        fc?.replaceFragment(fr)
    }
}