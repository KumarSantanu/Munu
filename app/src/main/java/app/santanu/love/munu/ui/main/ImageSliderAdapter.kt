package app.santanu.love.munu.ui.main

import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import app.santanu.love.munu.R
import com.squareup.picasso.Picasso

class ImageSliderAdapter(private val imageUrls: List<String>) :
    RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(val imageView: AppCompatImageView) :
        RecyclerView.ViewHolder(imageView)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder {
        val imageView = AppCompatImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        return ImageViewHolder(imageView)
    }

    override fun onBindViewHolder(
        holder: ImageViewHolder,
        position: Int
    ) {
        Picasso.get()
            .load(imageUrls[position])
            .placeholder(R.drawable.image_placeholder)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = imageUrls.size
}