package com.plbear.lxc

import android.os.Bundle
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initClickListener()

        LiveDataBus.get<String>(LiveDataBusKey.EVENT_BUS_TEST).observe(this, Observer {
            logcat("main activity:$it")
            binding.tvEventBus.text = it
        })

    }

    private fun initClickListener() {
        binding.btnEventBus.setOnClickListener { EventBusActivity.start(this) }
    }
}