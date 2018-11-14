package com.addictaf.kotlinyoutube.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.addictaf.kotlinyoutube.R
import com.addictaf.kotlinyoutube.adapters.CustomViewHolder
import com.addictaf.kotlinyoutube.models.CourseLesson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.course_detail.*
import kotlinx.android.synthetic.main.course_lesson_row.view.*
import okhttp3.*
import java.io.IOException
import java.text.FieldPosition


class CourseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.course_detail)

        recyclerview_course_detail.layoutManager = LinearLayoutManager(this)

        val navBarTitle = intent.getStringExtra(CustomViewHolder.VIDEO_TITLE_KEY)
        supportActionBar?.title = navBarTitle

        fetchJson()

    }

    private fun fetchJson() {

        val videoId = intent.getIntExtra(CustomViewHolder.VIDEO_ID, -1)
        val url = "https://api.letsbuildthatapp.com/youtube/course_detail?id=$videoId"

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                val gson = GsonBuilder().create()
                val courseLessons = gson.fromJson(body, Array<CourseLesson>::class.java)
                runOnUiThread {
                    recyclerview_course_detail.adapter = CourseDetailAdapter(courseLessons)
                }
            }
        })
    }

    private class CourseDetailAdapter(val courseLessons: Array<CourseLesson>) : RecyclerView.Adapter<CourseLessonViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CourseLessonViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val customView = layoutInflater.inflate(R.layout.course_lesson_row, parent, false)


//            val blueView = View(parent.context)
//            blueView.setBackgroundColor(Color.BLUE)
//            blueView.minimumHeight = 50
            return CourseLessonViewHolder(customView)
        }

        override fun getItemCount(): Int {
            return courseLessons.size
        }

        override fun onBindViewHolder(holder: CourseLessonViewHolder, position: Int) {
            val courseLesson = courseLessons[position]
            holder.customView.course_lesson_title?.text = courseLesson.name
            holder.customView.textView_course_lesson_duration?.text = courseLesson.duration
            val imageView = holder.customView.imageView_course_lesson_thumbnail
            Picasso.get().load(courseLesson.imageUrl).into(imageView)
            holder.courseLesson = courseLesson
        }
    }

    class CourseLessonViewHolder(val customView: View, var courseLesson: CourseLesson? = null) : RecyclerView.ViewHolder(customView) {

        companion object {
            val COURSE_LESSON_LINK_KEY = "COURSE_LESSON_LINK_KEY"
        }

        init {
            customView.setOnClickListener {
                val intent = Intent(customView.context, CourseLessonActivity::class.java)
                intent.putExtra(COURSE_LESSON_LINK_KEY, courseLesson?.link)
                customView.context.startActivity(intent)
            }
        }
    }
}
