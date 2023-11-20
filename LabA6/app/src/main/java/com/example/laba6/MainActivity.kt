package com.example.laba6

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laba6.database.DatabaseHelper
import java.util.Stack

class MainActivity : AppCompatActivity() {
    private lateinit var courseListAdapter: CourseListAdapter

    private val itemsList = ArrayList<CourseModel>()
    private var modelToBeUpdated: Stack<CourseModel> = Stack()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Courses list"

        val courseName: EditText = findViewById(R.id.course_name)
        val recyclerView: RecyclerView = findViewById(R.id.course_list_recycler_view)

        courseListAdapter =
            CourseListAdapter(itemsList, onCourseClickListener, DatabaseHelper.getInstance(this))
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = courseListAdapter

        findViewById<Button>(R.id.update_course).setOnClickListener {
            if (modelToBeUpdated.isEmpty()) return@setOnClickListener

            val name = courseName.text.toString()

            if (name.isNotBlank()) {
                val model = modelToBeUpdated.pop()
                model.name = name
                courseListAdapter.updateCourse(model)

                it.isEnabled = false
                courseName.setText("")
            }
        }

        findViewById<Button>(R.id.add_course).setOnClickListener {
            val name = courseName.text.toString()
            val id = courseListAdapter.getNextItemId()
            val model = CourseModel(id, name)
            courseListAdapter.addCourse(model)
            courseName.setText("")
        }
    }

    private val onCourseClickListener = object : OnCourseClickListener {
        override fun onUpdate(position: Int, model: CourseModel) {
            modelToBeUpdated.add(model)
            findViewById<EditText>(R.id.course_name).setText(model.name)
            findViewById<Button>(R.id.update_course).isEnabled = true
        }

        override fun onDelete(model: CourseModel) {
            courseListAdapter.removeCourse(model)
        }
    }
}
