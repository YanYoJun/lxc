package com.plbear.lxc.module.bus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.plbear.lxc.R
import com.plbear.lxc.base.BaseActivity
import com.plbear.lxc.databinding.ActivityEventBusBinding
import com.plbear.lxc.utils.logcat

/**
 * 事件总线的Activity
 * created by yanyongjun on 2020/6/27
 */
class EventBusActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            val i = Intent(context, EventBusActivity::class.java)
            context.startActivity(i)
        }
    }

    private lateinit var binding: ActivityEventBusBinding

    override fun onActivityCreate(savedInstanceState: Bundle?) {
        LiveDataBus.get<String>(LiveDataBusKey.EVENT_BUS_TEST).observe(this, Observer {
            logcat("bus event:$it")
            binding.tvEvent.text = it
        })
        initClickListener()
    }

    override fun initContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_bus)
    }

    private fun initClickListener() {
        binding.btnEvent.setOnClickListener {
            val curTime = System.currentTimeMillis()
            LiveDataBus.get<String>(LiveDataBusKey.EVENT_BUS_TEST)
                .postValue("sendTime:$curTime")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        logcat("EventBusActivity onDestroy")
    }
}