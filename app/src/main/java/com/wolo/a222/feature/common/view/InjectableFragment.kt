package com.wolo.a222.feature.common.view

import com.wolo.a222.feature.common.di.injector.Injector
import com.wolo.a222.feature.common.di.injector.InjectorProvider


abstract class InjectableFragment : BaseFragment() {

    protected val injector: Injector
        get() = (requireContext().applicationContext as InjectorProvider).injector
}
