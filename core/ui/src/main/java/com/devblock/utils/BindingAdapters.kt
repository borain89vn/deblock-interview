package com.devblock.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.devblock.GlideApp
import com.devblock.callback.OneClickListener

class BindingAdapters {

    companion object {


        @JvmStatic
        @BindingAdapter("dividerItemDecoration")
        fun setDividerItemDecoration(recyclerView: RecyclerView, orientation: Int) {
            val itemDecoration = DividerItemDecoration(recyclerView.context, orientation)
            recyclerView.addItemDecoration(itemDecoration)
        }


        @JvmStatic
        @BindingAdapter("srcImageUrl")
        fun setImageSrc(imageView: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {
                GlideApp.with(imageView.context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView)
            }
        }

        @JvmStatic
        @BindingAdapter("srcImageAny")
        fun setImageSrcAny(imageView: ImageView, url: Any?) {
            when (url) {
                is String -> {
                    if (!url.isNullOrEmpty()) {
                        GlideApp.with(imageView.context).load(url)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(imageView)
                    }
                }
                is Int -> {

                    GlideApp.with(imageView.context).load(url).into(imageView)

                }
            }

        }


        @JvmStatic
        @BindingAdapter("goneUnless")
        fun goneUnless(view: View, visible: Boolean) {
            view.visibility = if (visible) View.VISIBLE else View.GONE
        }


        @JvmStatic @BindingAdapter("oneClick")
        fun setOnClickListener(view: View, listener: View.OnClickListener?) {
            when (listener) {
                null -> view.setOnClickListener(null)
                else -> view.setOnClickListener(OneClickListener(listener))
            }
        }

    }

}