package com.addictaf.kotlinyoutube.activities

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.addictaf.kotlinyoutube.R
import kotlinx.android.synthetic.main.course_detail.*

class CourseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.course_detail)

        recyclerview_course_detail.layoutManager = LinearLayoutManager(this)
        recyclerview_course_detail.adapter = CourseDetailAdapter()
    }

    private class CourseDetailAdapter : RecyclerView.Adapter<CourseLessonViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CourseLessonViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val customView = layoutInflater.inflate(R.layout.course_lesson_row, parent, false)

//            val blueView = View(parent.context)
//            blueView.setBackgroundColor(Color.BLUE)
//            blueView.minimumHeight = 50
            return CourseLessonViewHolder(customView)
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(p0: CourseLessonViewHolder, p1: Int) {

        }

    }

    private class CourseLessonViewHolder(val customView: View) : RecyclerView.ViewHolder(customView) {

    }

}