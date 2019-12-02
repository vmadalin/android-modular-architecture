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

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.vmadalin.commons.ui.extensions.getString
import com.vmadalin.commons.views.databinding.ViewProgressDialogBinding

/**
 * Custom progress dialog to display as alert during on long process user waiting.
 *
 * @see AlertDialog
 */
class ProgressBarDialog(
    context: Context
) : AlertDialog(context, R.style.CustomProgressDialog) {

    lateinit var viewBinding: ViewProgressDialogBinding

    /**
     * Start the dialog and display it on screen. The window is placed in the application
     * layer and opaque.
     *
     * @see AlertDialog.show
     */
    override fun show() {
        show(null)
    }

    /**
     * Start the dialog and display it on screen. The window is placed in the application
     * layer and opaque.
     *
     * @param messageRes Message resource identifier.
     * @see show
     */
    fun show(@StringRes messageRes: Int?) {
        super.show()
        viewBinding = ViewProgressDialogBinding.inflate(LayoutInflater.from(context))
        setContentView(viewBinding.root)
        setCanceledOnTouchOutside(false)
        setCancelable(false)

        viewBinding.isLoading = true
        viewBinding.message = context.getString(messageRes)
    }

    /**
     * Dismiss this dialog, removing it from the screen. This method can be invoked safely
     * from any thread.
     *
     * @param messageRes Message resource identifier.
     * @see AlertDialog.dismiss
     */
    fun dismissWithErrorMessage(@StringRes messageRes: Int) {
        setCanceledOnTouchOutside(true)
        setCancelable(true)

        viewBinding.isLoading = false
        viewBinding.message = context.getString(messageRes)
    }
}
