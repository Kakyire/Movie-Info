package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils

import android.R
import android.app.Activity
import android.os.Build
import android.transition.Fade
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar

object TransitionFadeExclude {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    fun excludeOnTransition(
            activity: Activity, toolbar: Toolbar?
    ) {
        val fade = Fade()
        fade.excludeTarget(toolbar, true)
        fade.excludeTarget(R.id.statusBarBackground, true)
        fade.excludeTarget(R.id.navigationBarBackground, true)
        activity.window.enterTransition = fade
        activity.window.exitTransition = fade
    }
}