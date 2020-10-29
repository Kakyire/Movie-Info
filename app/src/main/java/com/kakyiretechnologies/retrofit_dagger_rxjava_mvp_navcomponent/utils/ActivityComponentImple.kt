package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils

import androidx.fragment.app.Fragment
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.component.ActivityComponent
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.component.DaggerActivityComponent
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.module.AdapterModule
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.module.FragmentContextModule
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.module.ViewModule
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.BaseContract

object ActivityComponentImple {

    fun implementComponent(
        fragment: Fragment,
        clickListener: ClickListener,
        view: BaseContract.BaseView
    ): ActivityComponent {
        return DaggerActivityComponent.builder()
            .fragmentContextModule(FragmentContextModule(fragment))
            .adapterModule(fragment.context?.let { AdapterModule(clickListener, it) })
            .viewModule(ViewModule(view))
            .applicationComponent(
                (fragment.context?.applicationContext as BaseApp)
                    .getApplicationComponent()
            )
            .build()
    }
}

