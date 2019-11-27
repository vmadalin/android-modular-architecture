/*
 * Copyright 2019 vmadalin.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vmadalin.commons.views

import android.app.AlertDialog
import android.content.Context
import androidx.annotation.StringRes
import kotlinx.android.synthetic.main.view_progress_dialog.*

class ProgressBarDialog(
    context: Context
) : AlertDialog(context, R.style.CustomProgressDialog) {

    override fun show() {
        show(null)
    }

    fun show(@StringRes messageRes: Int?) {
        super.show()
        setContentView(R.layout.view_progress_dialog)
        setCanceledOnTouchOutside(false)
        setCancelable(false)

        messageRes?.toString()
//        progress_bar_loading.visibility = View.VISIBLE
//
//        messageRes?.let {
//            progress_bar_message.text = context.getString(it)
//            progress_bar_message.visibility = View.VISIBLE
//        } ?: run {
//            progress_bar_message.visibility = View.GONE
//        }
    }

    fun dismissWithErrorMessage(errorMessage: Int) {
        setCanceledOnTouchOutside(true)
        setCancelable(true)

        errorMessage.toString()

        // progress_bar_message.setText(errorMessage)
        // progress_bar_loading.visibility = View.GONE
    }
}
