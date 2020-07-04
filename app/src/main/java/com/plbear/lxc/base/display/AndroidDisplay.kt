package com.plbear.lxc.base.display

import android.app.Activity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.plbear.lxc.R
import com.plbear.lxc.utils.logcat
import java.lang.ref.WeakReference

/**
 * created by yanyongjun on 2020/6/29
 */
class AndroidDisplay(activity: AppCompatActivity) : Display {
    private val mActivity = WeakReference(activity)
    private var mToolbar: Toolbar? = null
    private var mBackDrawable = activity.getDrawable(R.drawable.home_as_up)
    override fun setSupportToolbar(toolbar: Toolbar?) {
        logcat("setSupportToolbar")
        mToolbar = toolbar
        mToolbar?.let {
            mActivity.get()?.setSupportActionBar(toolbar)
            mActivity.get()?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            it.navigationIcon = mBackDrawable
            it.setNavigationOnClickListener {
                mActivity.get()?.onBackPressed()
            }
        }
    }

    override fun showUpButton(show: Boolean) {
        mActivity.get()?.supportActionBar?.setDisplayHomeAsUpEnabled(show)
    }

    override fun setTitle(title: String) {
        logcat("setTitle:$title")
        mActivity.get()?.supportActionBar?.setDisplayShowTitleEnabled(false)
        mToolbar?.findViewById<TextView>(R.id.tv_toolbar_title)?.text = title
        logcat("set title result:${mToolbar}")
    }
}