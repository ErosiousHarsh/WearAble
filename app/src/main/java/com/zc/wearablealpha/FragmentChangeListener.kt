package com.zc.wearablealpha

import android.os.Bundle
import androidx.fragment.app.Fragment

open interface FragmentChangeListener {

    fun replaceFragment(bundle: Bundle) { }

}