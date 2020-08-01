package com.plbear.lxc.module.glide

import android.content.Context
import android.content.Intent
import android.graphics.Matrix
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.plbear.lxc.R
import com.plbear.lxc.base.BaseActivity
import com.plbear.lxc.databinding.ActivityGlideBinding
import com.plbear.lxc.utils.logcat

/**
 * created by yanyongjun on 2020/7/11
 */
class GlideActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            val i = Intent(context, GlideActivity::class.java)
            context.startActivity(i)
        }

        const val IMAGE_URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594445689385&di=f531a3c27f1b67d3a6516b73fb4c87a5&imgtype=0&src=http%3A%2F%2Fbpic.588ku.com%2Fback_pic%2F05%2F73%2F27%2F115bc5b65f1d43e.jpg"
        const val IMAGE_URL_V1 = "http://img1.lukou.com/static/p/upload/icon/home_portal_2.png"
    }

    private lateinit var binding: ActivityGlideBinding
    override fun onActivityCreate(savedInstanceState: Bundle?) {
        val matrix = Matrix().apply {
            this.postScale(0.5f,2f)
            this.postTranslate(20f,30f)
        }
        binding.iv1.imageMatrix = matrix
//        Glide.with(this)
//            .load(IMAGE_URL_V1)
//            .centerCrop()
//            .into(binding.iv1)
    }

    override fun initContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_glide)
    }
}