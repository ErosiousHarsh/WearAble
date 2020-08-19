package com.zc.wearablealpha

import android.R.attr.fragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit


class ActivitySignup : FragmentChangeListener,AppCompatActivity() {

    override fun replaceFragment(bundle: Bundle) {
        val fragment = ActivityFragmentSignUpPhone()
        fragment.arguments = bundle
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left
                                               ,R.anim.enter_left_to_right,R.anim.exit_left_to_right)
        fragmentTransaction.replace(R.id.fragmentSignUp, fragment, fragment.toString())
        fragmentTransaction.addToBackStack(fragment.toString())
        fragmentTransaction.commit()

//        supportFragmentManager.commit {
//
//            val frag = ActivityFragmentSignUpPhone()
//            frag.arguments = bundle
//            replace(R.id.fragmentSignUp, frag)
//        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = ActivityFragmentSignUpDetails()
        fragmentTransaction.add(R.id.fragmentSignUp, fragment)
        fragmentTransaction.commit()


    }
}