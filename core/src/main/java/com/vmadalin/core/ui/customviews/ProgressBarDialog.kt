package com.vmadalin.core.ui.customviews

import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import com.vmadalin.core.R
import kotlinx.android.synthetic.main.view_progress_dialog.*

class ProgressBarDialog(context: Context) : AlertDialog(context, R.style.CustomProgressDialog) {

    override fun show() {
        show(null)
    }

    fun show(@StringRes messageRes: Int?) {
        super.show()
        setContentView(R.layout.view_progress_dialog)
        setCanceledOnTouchOutside(false)
        setCancelable(false)

        progress_bar_loading.visibility = View.VISIBLE

        messageRes?.let {
            progress_bar_message.text = context.getString(it)
            progress_bar_message.visibility = View.VISIBLE
        } ?: run {
            progress_bar_message.visibility = View.GONE
        }
    }

    fun dismissWithErrorMessage(errorMessage: Int) {
        setCanceledOnTouchOutside(true)
        setCancelable(true)

        progress_bar_message.setText(errorMessage)
        progress_bar_loading.visibility = View.GONE
    }
}
