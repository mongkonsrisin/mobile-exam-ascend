package com.ascend.mobile.exam

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import com.ascend.mobile.exam.databinding.DialogErrorBinding

typealias OnButtonClickListener = (ErrorDialog) -> Unit

class ErrorDialog(
    context: Context,
    private var title: String?,
    private var body: String?,
    private var button: String?,
    private var buttonListener: OnButtonClickListener?,

) : Dialog(context) {

    private val binding: DialogErrorBinding by lazy {
        DialogErrorBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.title.text = title
        binding.body.text = body
        binding.button.text = button


        binding.button.setOnClickListener {
            buttonListener?.invoke(this)
        }

        val params = window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
        setCanceledOnTouchOutside(false)

    }


    class Builder(private var context: Context) {

        private var buttonListener: OnButtonClickListener? = null
        private var title: String? = null
        private var body: String? = null
        private var button: String? = null

        fun withTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun withBody(body: String): Builder {
            this.body = body
            return this
        }

        fun withButton(button: String): Builder {
            this.button = button
            return this
        }

        fun withOnButtonClick(listener: OnButtonClickListener): Builder {
            this.buttonListener = listener
            return this
        }


        fun show() = ErrorDialog(
            context,
            title,
            body,
            button,
            buttonListener,
        ).show()
    }
}
