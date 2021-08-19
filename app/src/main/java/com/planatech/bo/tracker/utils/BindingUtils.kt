package com.planatech.bo.tracker.utils

import android.text.method.LinkMovementMethod
import android.view.View.GONE
import android.widget.ImageView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.planatech.bo.tracker.R

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String?) {
    val url = createImageUrl(imageUrl)
    Glide.with(imageView.context).load(url).apply(
        RequestOptions().placeholder(R.drawable.ic_placeholder_svg)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    ).into(imageView)
}

@BindingAdapter("imageUrl")
fun loadImage(imageView: ShapeableImageView, imageUrl: String?) {
    val url = createImageUrl(imageUrl)
    Glide.with(imageView.context).load(url).apply(
        RequestOptions().placeholder(R.drawable.ic_placeholder_svg)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    ).into(imageView)
}

@BindingAdapter("imageUrlW500")
fun loadImageW500(imageView: ShapeableImageView, imageUrl: String?) {
    val url = createImageUrlW500(imageUrl)
    Glide.with(imageView.context).load(url).apply(
        RequestOptions().placeholder(R.drawable.ic_placeholder_svg)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    ).into(imageView)
}

@BindingAdapter("setAdapter")
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>?) {
    this.run {
        this.setHasFixedSize(true)
        this.adapter = adapter
    }
}

@BindingAdapter("textFromHtml")
fun MaterialTextView.textFromHtml(text: String?) {
    this.text = HtmlCompat.fromHtml(text ?: "", HtmlCompat.FROM_HTML_MODE_COMPACT)
    this.movementMethod = LinkMovementMethod.getInstance()
}

@BindingAdapter("checkAuthorVisibility")
fun MaterialTextView.checkAuthorVisibility(text: String?) {
    if (text.isNullOrEmpty())
        this.visibility = GONE
}