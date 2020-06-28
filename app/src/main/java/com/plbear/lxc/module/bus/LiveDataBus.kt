package com.plbear.lxc.module.bus

import androidx.lifecycle.BusLiveData
import com.plbear.lxc.module.bus.core.LiveDataBusCore

/**
 * 事件总线的入口
 * created by yanyongjun on 2020/6/27
 */
object LiveDataBus {
    fun <T> get(key:String) : BusLiveData<T>{
        return LiveDataBusCore.instance.getChannel(key)
    }
}