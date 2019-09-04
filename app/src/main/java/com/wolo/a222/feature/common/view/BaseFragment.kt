package com.wolo.a222.feature.common.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment : Fragment()
{

    private val disposablesMap = mutableMapOf<FragmentState, CompositeDisposable>()
    private var stateSaved: Boolean = false
    protected var isFinishing: Boolean = false
        private set

    @get:LayoutRes
    protected open val layoutResId: Int
        get() = 0

    protected val args by lazy { arguments!! }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (layoutResId > 0) {
            inflater.inflate(layoutResId, container, false)
        } else {
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    private fun putDisposable(state: FragmentState, disposable: Disposable) {
        disposablesMap[state].let { compositeDisposable ->
            compositeDisposable ?: CompositeDisposable().also { disposablesMap[state] = it }
        }.add(disposable)
    }

    protected fun disposeOnPause(disposable: Disposable) = putDisposable(FragmentState.PAUSE, disposable)

    protected fun disposeOnStop(disposable: Disposable) = putDisposable(FragmentState.STOP, disposable)

    protected fun disposeOnDestroyView(disposable: Disposable) = putDisposable(FragmentState.DESTROY_VIEW, disposable)

    protected fun disposeOnDestroy(disposable: Disposable) = putDisposable(FragmentState.DESTROY, disposable)

    protected fun disposeOnDetach(disposable: Disposable) = putDisposable(FragmentState.DETACH, disposable)

    override fun onStart() {
        super.onStart()
        stateSaved = false
    }

    override fun onResume() {
        super.onResume()
        stateSaved = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        stateSaved = true
    }

    override fun onPause() {
        disposablesMap[FragmentState.PAUSE]?.clear()
        super.onPause()
    }

    override fun onStop() {
        disposablesMap[FragmentState.STOP]?.clear()
        super.onStop()
    }

    override fun onDestroyView() {
        disposablesMap[FragmentState.DESTROY_VIEW]?.clear()
        super.onDestroyView()
    }

    override fun onDestroy() {
        disposablesMap[FragmentState.DESTROY]?.clear()
        super.onDestroy()

        if (activity?.isFinishing != false) {
            isFinishing = true
            return
        }

        if (stateSaved){
            stateSaved = false
            return
        }

        var anyParentIsRemoving = false
        var parent = parentFragment
        while (!anyParentIsRemoving && parent != null) {
            anyParentIsRemoving = parent.isRemoving
            parent = parent.parentFragment
        }

        if (isRemoving || anyParentIsRemoving) {
            isFinishing = true
        }
    }

    override fun onDetach() {
        disposablesMap[FragmentState.DETACH]?.clear()
        super.onDetach()
    }

    protected val hostParent: Any?
        get() = if (parentFragment != null) {
            parentFragment
        } else {
            activity
        }

}

enum class FragmentState {
    PAUSE,
    STOP,
    DESTROY_VIEW,
    DESTROY,
    DETACH
}
