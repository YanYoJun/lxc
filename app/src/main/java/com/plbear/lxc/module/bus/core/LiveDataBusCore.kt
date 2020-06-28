package com.plbear.lxc.module.bus.core

import androidx.lifecycle.BusLiveData

/**
 * 事件总线的核心实现
 * created by yanyongjun on 2020/6/27
 */
class LiveDataBusCore private constructor() {
    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            LiveDataBusCore()
        }
    }

    internal val busMap: MutableMap<String, BusLiveData<*>> by lazy {
        mutableMapOf<String, BusLiveData<*>>()
    }

    fun <T> getChannel(key: String): BusLiveData<T> {
        return busMap.getOrPut(key) {
            BusLiveData<T>(key)
        } as BusLiveData<T>
    }
}