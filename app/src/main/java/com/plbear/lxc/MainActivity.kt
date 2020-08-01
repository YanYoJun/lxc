package com.plbear.lxc

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.plbear.lxc.base.BaseActivity
import com.plbear.lxc.databinding.ActivityMainBinding
import com.plbear.lxc.module.bus.EventBusActivity
import com.plbear.lxc.module.glide.GlideActivity
import com.plbear.lxc.module.view.OnDrawActivity

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onActivityCreate(savedInstanceState: Bundle?) {
        initClickListener()
        getDisplay().showUpButton(false)
    }

    override fun initContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun initClickListener() {
        binding.btnEventBus.setOnClickListener { EventBusActivity.start(this) }
        binding.btnOnDraw.setOnClickListener { OnDrawActivity.start(this) }
        binding.btnGlide.setOnClickListener { GlideActivity.start(this)}
    }
}