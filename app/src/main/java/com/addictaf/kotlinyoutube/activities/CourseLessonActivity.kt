package com.addictaf.kotlinyoutube.activities

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.addictaf.kotlinyoutube.R
import kotlinx.android.synthetic.main.course_lesson.*

class CourseLessonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.course_lesson)
        val url = intent.getStringExtra(CourseDetailActivity.CourseLessonViewHolder.COURSE_LESSON_LINK_KEY)

        webview_course_lesson.settings.javaScriptEnabled = true
        webview_course_lesson.settings.loadWithOverviewMode = true
        webview_course_lesson.settings.useWideViewPort = true
        webview_course_lesson.loadUrl(url)
    }
}
