package com.vmadalin.android.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import dagger.android.DaggerActivity
import timber.log.Timber

abstract class BaseActivity : DaggerActivity() {

    @get:LayoutRes
    protected abstract val layoutResId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.v("Activity onCreate: ${javaClass.simpleName}")
        setContentView(layoutResId)
    }
}
