package android.demo.weatherapplication.feature.gallery.lobby.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.demo.weatherapplication.R
import android.demo.weatherapplication.feature.gallery.lobby.model.ImageModel

import android.widget.ImageView
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class GalleryImageRecyclerViewAdapter() :
    RecyclerView.Adapter<GalleryImageRecyclerViewAdapter.ViewHolder>() {

    private val values: MutableList<ImageModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_gallery_lobby, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .centerCrop()
            .apply(RequestOptions.placeholderOf(R.color.light_gray))
            .error(R.drawable.ic_loading)
            .into(holder.imageView)
        ViewCompat.setTransitionName(holder.imageView, "image$position")
        holder.imageView.setOnClickListener {
            item.imageClickListener.onImageClicked(
                item.imageUrl,
                holder.imageView to "image$position"
            )
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image)
    }

    fun setValues(images: List<ImageModel>) {
        values.clear()
        values.addAll(images)
        notifyDataSetChanged()
    }

}