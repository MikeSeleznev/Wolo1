package com.wolo.a222.feature.selecttask.view.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.wolo.a222.feature.selecttask.presenter.SelectTaskVM


class SelectTaskAdapter : AsyncListDifferDelegationAdapter<SelectTaskVM>(SelectTaskDiffCallBack()){
    fun addDelegate(delegate: AdapterDelegate<List<SelectTaskVM>>): AdapterDelegatesManager<MutableList<SelectTaskVM>>? =
        delegatesManager.addDelegate(delegate)
}

class SelectTaskDiffCallBack : DiffUtil.ItemCallback<SelectTaskVM>() {
    override fun areItemsTheSame(oldItem: SelectTaskVM, newItem: SelectTaskVM): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: SelectTaskVM, newItem: SelectTaskVM): Boolean {
        return oldItem == newItem
    }

}