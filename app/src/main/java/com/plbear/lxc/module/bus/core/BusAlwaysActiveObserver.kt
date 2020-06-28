package com.plbear.lxc.module.bus.core

import androidx.lifecycle.*


/**
 * created by yanyongjun on 2020/6/27
 */
class BusAlwaysActiveObserver<T>(
    private val observer: Observer<T>,
    private val busLiveData: BusLiveData<T>
) : BaseBusObserverWrapper<T>(observer, busLiveData)