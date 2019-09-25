package com.vmadalin.android.utils

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

object ThemeUtils {

    fun setAppTheme(activity: AppCompatActivity, theme: AppCompatDelegate) {

        activity.delegate.applyDayNight()
    }

    /**
     * Whether the current configuration is a dark theme i.e. in Night configuration.
     */
    fun isDarkTheme(context: Context): Boolean {
        return context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }

    /**
     * Whether the current configuration is a light theme i.e. in Day configuration.
     */
    fun isLightTheme(context: Context): Boolean {
        return !isDarkTheme(context)
    }
}
