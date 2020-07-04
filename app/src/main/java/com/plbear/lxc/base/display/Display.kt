package com.plbear.lxc.base.display

import androidx.appcompat.widget.Toolbar

/**
 * created by yanyongjun on 2020/6/29
 */
interface Display {
    fun setSupportToolbar(toolbar: Toolbar?)

    fun setTitle(title:String)

    fun showUpButton(show:Boolean)
}