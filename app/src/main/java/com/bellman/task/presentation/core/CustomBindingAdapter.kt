package com.bellman.task.presentation.core

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bellman.task.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object CustomBindingAdapter {

    @BindingAdapter("android:image")
    @JvmStatic
    fun setImage(view: ImageView?, url: String?) {
        if (view != null && !TextUtils.isEmpty(url)) {
            Glide.with(view)
                .load(url)
                .apply(RequestOptions().placeholder(R.drawable.no_image).error(R.drawable.no_image))
                .into(view)
        }
    }

    @BindingAdapter("android:imagearray")
    @JvmStatic
    fun setImageArray(view: ImageView?, url: List<String>?) {
        if (view != null && url!!.isNotEmpty()) {
            Glide.with(view)
                .load(url[0])
                .apply(RequestOptions().placeholder(R.drawable.no_image).error(R.drawable.no_image))
                .into(view)
        }
    }
}