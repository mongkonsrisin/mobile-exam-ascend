package com.ascend.mobile.exam

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.Window
import android.view.WindowManager


class LoadingDialog {

    private var dialog: Dialog? = null

    fun show(context: Context) {
        if (dialog != null && dialog?.isShowing == true) {
            return
        }

        dialog = Dialog(context)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(R.layout.dialog_loading)
        val params = dialog?.window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog?.show()
    }

    fun dismiss() {
        try {
            if (dialog != null && dialog?.isShowing == true) {
                dialog?.dismiss()
            }
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e("LoadingDialog", "Cannot dismiss loading dialog", e)
            }
        }
    }

    companion object {
        private var mInstance: LoadingDialog? = null

        @get:Synchronized
        val instance: LoadingDialog?
            get() {
                if (mInstance == null) {
                    mInstance = LoadingDialog()
                }
                return mInstance
            }
    }
}