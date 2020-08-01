package com.plbear.lxc.module.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.plbear.lxc.R
import com.plbear.lxc.databinding.FragmentOnDrawBinding
import com.plbear.lxc.utils.logcat

/**
 * created by yanyongjun on 2020/7/4
 */
class OnDrawFragment : Fragment() {
    private lateinit var binding: FragmentOnDrawBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logcat("onCreate:$this")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_on_draw, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        logcat("onResume $this")
    }

    override fun onStart() {
        super.onStart()
        logcat("onStart $this")
    }
}