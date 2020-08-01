package com.plbear.lxc.module.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.plbear.lxc.R
import com.plbear.lxc.base.BaseActivity
import com.plbear.lxc.databinding.ActivityOnDrawBinding
import com.plbear.lxc.module.view.fragment.OnDrawFragment

/**
 * 练习自定义View的OnDraw方法
 * created by yanyongjun on 2020/7/4
 */
class OnDrawActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            val i = Intent(context, OnDrawActivity::class.java)
            context.startActivity(i)
        }
    }

    private lateinit var binding: ActivityOnDrawBinding
    private val mAdapter = MyAdapter()
    override fun onActivityCreate(savedInstanceState: Bundle?) {
//        binding.tabLayout.addTab(binding.tabLayout.newTab().apply {
//            this.text = "OnDraw"
//        })
        mAdapter.fragmentList.add(OnDrawFragment())
        mAdapter.fragmentList.add(OnDrawFragment())
        mAdapter.fragmentList.add(OnDrawFragment())
        binding.viewPager.adapter = mAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
//        binding.tabLayout.removeAllTabs()
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("OnDrawV2"))
    }

    override fun initContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_on_draw)
    }

    inner class MyAdapter : FragmentPagerAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        val fragmentList = mutableListOf<Fragment>()
        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return "OnDraw"
        }
    }
}
