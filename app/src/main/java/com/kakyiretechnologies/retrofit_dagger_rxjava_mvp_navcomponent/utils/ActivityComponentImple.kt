package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils

import android.app.Activity
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.component.ActivityComponent
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.component.DaggerActivityComponent
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.module.ActivityContextModule
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.module.AdapterModule
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.module.ViewModule
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.BaseContract

object ActivityComponentImple {

    fun implementComponent(
        activity: Activity,
        clickListener: ClickListener,
        view: BaseContract.BaseView
    ): ActivityComponent {
        return DaggerActivityComponent.builder()
            .activityContextModule(ActivityContextModule(activity))
            .adapterModule(AdapterModule(clickListener, activity))
//            .activityContextModule(ActivityContextModule(activity.activity as AppCompatActivity))
//            .adapterModule(activity.context?.let { AdapterModule(clickListener, it) })
            .viewModule(ViewModule(view))
            .applicationComponent(
                (activity.applicationContext as BaseApp)
//                (activity.context?.applicationContext as BaseApp)
                    .getApplicationComponent()
            )
            .build()
    }
}

