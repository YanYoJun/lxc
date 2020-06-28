package com.plbear.lxc.module.bus.core

import androidx.lifecycle.*

/**
 * created by yanyongjun on 2020/6/27
 */
class BusLifecycleObserver<T>(
    private val observer: Observer<in T>,
    private val owner: LifecycleOwner,
    private val liveData: BusLiveData<T>
) : BaseBusObserverWrapper<T>(observer, liveData), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        liveData.removeObserver(observer)
        owner.lifecycle.removeObserver(this) //这一步只是顺便怎么做 没有太大的意义.
        //经过试验, 这里应该是没有什么用的. 应为LiveData本身就会在OnDestory的时候自动调用removeObserver方法.
        // 但是加上也没有什么坏处
    }

    override fun isAttachedTo(owner: LifecycleOwner): Boolean {
        return owner == this.owner
    }
}