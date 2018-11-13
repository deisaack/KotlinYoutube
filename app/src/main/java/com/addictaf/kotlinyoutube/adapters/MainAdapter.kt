package com.addictaf.kotlinyoutube.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.addictaf.kotlinyoutube.R
import com.addictaf.kotlinyoutube.activities.CourseDetailActivity
import com.addictaf.kotlinyoutube.models.HomeFeed
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_row.view.*

import java.text.FieldPosition

class MainAdapter(val homeFeed: HomeFeed): RecyclerView.Adapter<CustomViewHolder>() {
    val videoTitles = listOf("Video one", "The second video", "Yet another here")

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val video = homeFeed.videos.get(position)
        holder.view.video_title_textview_main?.text = video.name
        holder.view.video_channel_name_text_view?.text = "${video.channel.name} * ${video.numberOfViews/1000}K Views"

        val thumbnailImageView = holder.view.video_thumbnail_imageview

//        Picasso.get(holder.view.context).load(video.imageUrl).into(thumbnailImageView)
        Picasso.get().load(video.imageUrl).into(thumbnailImageView)

        val channelProfileImage = holder.view.imageView_channel_profile

        Picasso.get().load(video.channel.profileImageUrl).into(channelProfileImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, CourseDetailActivity::class.java)

            view.context.startActivity(intent)
        }
    }

}
