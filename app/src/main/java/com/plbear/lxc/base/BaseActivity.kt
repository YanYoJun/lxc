package com.plbear.lxc.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.plbear.lxc.R
import com.plbear.lxc.base.display.AndroidDisplay
import com.plbear.lxc.base.display.Display
import com.plbear.lxc.utils.logcat

/**
 * created by yanyongjun on 2020/6/27
 */
abstract class BaseActivity : AppCompatActivity() {
    private lateinit var mDisplay: Display
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView()
        init()
        onActivityCreate(savedInstanceState)
    }

    abstract fun onActivityCreate(savedInstanceState: Bundle?)

    abstract fun initContentView()

    private fun init() {
        mDisplay = AndroidDisplay(this@BaseActivity)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        mDisplay.setSupportToolbar(toolbar)
    }

    override fun onTitleChanged(title: CharSequence?, color: Int) {
        super.onTitleChanged(title, color)
        logcat("onTitleChanged:$title")
        mDisplay.setTitle(title.toString())
    }
}