package com.zc.wearablealpha

import androidx.fragment.app.Fragment

open interface FragmentChangeListener {
    fun replaceFragment(fragment: Fragment) { }
}