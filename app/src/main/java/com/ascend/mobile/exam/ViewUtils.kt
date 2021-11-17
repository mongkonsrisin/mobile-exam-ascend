package com.ascend.mobile.exam

import android.content.Context

object ViewUtils {

    fun showLoading(context: Context, isShow: Boolean) {
        if (isShow) {
            LoadingDialog.instance?.show(context)
        } else {
            LoadingDialog.instance?.dismiss()
        }
    }


}