package com.zc.wearablealpha

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class ActivitySignup : FragmentChangeListener,AppCompatActivity() {

    override fun replaceFragment(bundle: Bundle) {

        supportFragmentManager.commit {

            val frag = ActivityFragmentSignUpPhone()
            frag.arguments = bundle
            replace(R.id.fragmentSignUp, frag)
        }
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