package com.example.laba6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.laba6.database.DatabaseHelper

internal class CourseListAdapter(
    private var itemsList: ArrayList<CourseModel>,
    private val onCourseClickListener: OnCourseClickListener,
    private var databaseHelper: DatabaseHelper,
) :
    RecyclerView.Adapter<CourseListAdapter.CourseViewHolder>() {

    init {
        setResults()
    }

    internal inner class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTextView: TextView = view.findViewById(R.id.course_name)
        val courseDelete: ImageView = itemView.findViewById(R.id.delete_course)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        val holder = CourseViewHolder(itemView)

        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val model = itemsList[position]

            onCourseClickListener.onUpdate(position, model)
        }

        holder.courseDelete.setOnClickListener {
            val position = holder.adapterPosition
            val model = itemsList[position]
            onCourseClickListener.onDelete(model)
        }

        return holder
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val item = itemsList[position]
        holder.itemTextView.text = item.name
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    private fun setResults() {
        itemsList = databaseHelper.getAllCourses()
        notifyDataSetChanged()
    }

    fun addCourse(course: CourseModel) {
        databaseHelper.addCourse(course)
        itemsList.add(course)

        notifyItemInserted(itemsList.size)
    }

    fun removeCourse(course: CourseModel) {
        val position = itemsList.indexOf(course)
        databaseHelper.deleteCourse(course)
        itemsList.remove(course)
        notifyItemRemoved(position)
    }

    fun updateCourse(model: CourseModel?) {
        if (model == null) return

        for (item in itemsList) {
            if (item.id == model.id) {
                val position = itemsList.indexOf(model)
                databaseHelper.updateCourse(model)
                itemsList[position] = model
                notifyItemChanged(position)
                break
            }
        }
    }

    fun getNextItemId(): Int {
        var id = 1
        if (itemsList.isNotEmpty()) {
            id = itemsList.last().id + 1
        }
        return id
    }
}
