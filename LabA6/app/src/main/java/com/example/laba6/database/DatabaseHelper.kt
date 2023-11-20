package com.example.laba6.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.core.database.getStringOrNull
import com.example.laba6.CourseModel


class DatabaseHelper private constructor(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val TAG: String = this::class.java.name

    companion object {
        private var instance: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null)
                instance = DatabaseHelper(context)

            return instance!!
        }

        // Database Info
        private const val DATABASE_NAME = "coursesDatabase"
        private const val DATABASE_VERSION = 1

        // Table Names
        private const val TABLE_COURSES = "courses"

        // Course Table Columns
        private const val KEY_COURSE_ID = "id"
        private const val KEY_COURSE_NAME = "name"
    }

    // Called when the database connection is being configured.
    // Configure database settings for things like foreign key support, write-ahead logging, etc.
    override fun onConfigure(db: SQLiteDatabase) {
        super.onConfigure(db)
        db.setForeignKeyConstraintsEnabled(true)
    }

    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    override fun onCreate(db: SQLiteDatabase) {
        val createCoursesTable = "CREATE TABLE " + TABLE_COURSES +
                "(" +
                KEY_COURSE_ID + " INTEGER PRIMARY KEY," +  // Define a primary key
                KEY_COURSE_NAME + " TEXT" +
                ")"
        db.execSQL(createCoursesTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_COURSES")
            onCreate(db)
        }
    }

    fun addCourse(course: CourseModel) {
        val db = writableDatabase

        db.beginTransaction()
        try {
            val values = ContentValues()
            values.put(KEY_COURSE_NAME, course.name)

            db.insertOrThrow(TABLE_COURSES, null, values)
            db.setTransactionSuccessful()
            Log.d(TAG, "Course added to the database")
        } catch (e: Exception) {
            Log.d(TAG, "Error while trying to add course to the database")
        } finally {
            db.endTransaction()
        }
    }

    fun getAllCourses(): ArrayList<CourseModel> {
        val courses: ArrayList<CourseModel> = ArrayList()

        val selectCoursesQuery = String.format(
            "SELECT * FROM %s",
            TABLE_COURSES
        )

        val db = readableDatabase
        val cursor: Cursor = db.rawQuery(selectCoursesQuery, null)
        try {
            if (cursor.moveToFirst()) {
                do {
                    val newCourse = CourseModel()
                    newCourse.name = cursor.getStringOrNull(cursor.getColumnIndex(KEY_COURSE_NAME))
                    courses.add(newCourse)
                } while (cursor.moveToNext())
            }
        } catch (e: java.lang.Exception) {
            Log.d(TAG, "Error while trying to get courses from database")
        } finally {
            if (!cursor.isClosed) {
                cursor.close()
            }
        }
        return courses
    }

    fun updateCourse(course: CourseModel): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_COURSE_NAME, course.name)

        return db.update(
            TABLE_COURSES,
            values,
            "$KEY_COURSE_ID = ?",
            arrayOf((course.id).toString())
        )
    }

    fun deleteCourse(course: CourseModel) {
        val db = writableDatabase

        db.beginTransaction()
        try {
            db.delete(TABLE_COURSES, "$KEY_COURSE_ID = ?", arrayOf((course.id).toString()))
            db.setTransactionSuccessful()
        } catch (e: java.lang.Exception) {
            Log.d(TAG, "Error while trying to delete course")
        } finally {
            db.endTransaction()
        }
    }
}
