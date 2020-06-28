package com.plbear.lxc.module.bus.core

import androidx.lifecycle.BusLiveData
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.plbear.lxc.utils.logcat


/**
 * created by yanyongjun on 2020/6/27
 */
open class BaseBusObserverWrapper<T>(
    private val observer: Observer<in T>,
    private val busLiveData: BusLiveData<T>
) : Observer<T> {
    private val lastVersion = busLiveData.version
    override fun onChanged(t: T) {
        if (lastVersion >= busLiveData.version) {
            //这里是为了规避当刚observe的时候, 就接收到已经修改过的内容
            return
        }
        try {
            observer.onChanged(t)
        } catch (e: Exception) {
            logcat(e.message)
        }
    }

    open fun isAttachedTo(owner: LifecycleOwner) = false
}