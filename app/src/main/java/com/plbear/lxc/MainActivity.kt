package com.plbear.lxc

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.plbear.lxc.base.BaseActivity
import com.plbear.lxc.databinding.ActivityMainBinding
import com.plbear.lxc.module.bus.EventBusActivity
import com.plbear.lxc.module.bus.LiveDataBus
import com.plbear.lxc.module.bus.LiveDataBusKey
import com.plbear.lxc.utils.logcat

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onActivityCreate(savedInstanceState: Bundle?) {
        initClickListener()

        LiveDataBus.get<String>(LiveDataBusKey.EVENT_BUS_TEST).observe(this, Observer {
            logcat("main activity:$it")
            binding.tvEventBus.text = it
        })
        getDisplay().showUpButton(false)
    }

    override fun initContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun initClickListener() {
        binding.btnEventBus.setOnClickListener { EventBusActivity.start(this) }
    }
}