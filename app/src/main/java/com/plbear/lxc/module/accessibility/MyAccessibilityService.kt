package com.plbear.lxc.module.accessibility

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.plbear.lxc.utils.logcat

/**
 * created by yanyongjun on 2020/7/1
 */
class MyAccessibilityService : AccessibilityService() {
    override fun onInterrupt() {
        logcat("onInterrupt")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return
        logcat("onAccessibilityEvent:$event")
        when (event.packageName) {
            "com.dingda.app" -> {
                if (event.eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
                    findViewById("com.dingda.app:id/tt_splash_skip_btn")?.let {
                        logcat("is not null click")
                        it.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    }
                }
            }
        }
    }

    fun findViewByText(text: String): AccessibilityNodeInfo? {
        val nodeInfo = rootInActiveWindow ?: return null
        val list = nodeInfo.findAccessibilityNodeInfosByText(text)
        if (list.isNotEmpty()) return list[0]
        return null
    }

    fun findViewById(id: String): AccessibilityNodeInfo? {
        val nodeInfo = rootInActiveWindow ?: return null
        val list = nodeInfo.findAccessibilityNodeInfosByViewId(id)
        if (list.isNotEmpty()) return list[0]
        return null
    }
}