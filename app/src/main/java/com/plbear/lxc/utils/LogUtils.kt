package com.plbear.lxc.utils

import android.util.Log

/**
 * created by yanyongjun on 2020/6/27
 */
fun logcat(str: String?) {
    if (str == null) {
        Log.d("lxclog", "null")
        return
    }
    Log.d("lxclog", str)
}

fun logcat(throwable: Throwable?){
    if (throwable == null) {
        logcat("throwable == null")
        return
    }
    logcat(throwable.message)
    throwable.stackTrace.forEach {
        logcat(it.toString())
    }
}
