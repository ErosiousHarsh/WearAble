package com.zc.wearablealpha

import android.view.View
import kotlinx.android.synthetic.main.activity_user.view.*

open interface Loading {

    private fun startLoading(v: View) {
        v.alpha = 0.7F
        v.progress_circular.visibility = View.VISIBLE
    }

    private fun stopLoading(v: View) {
        v.alpha = 1F
        v.progress_circular.visibility = View.INVISIBLE
    }
}