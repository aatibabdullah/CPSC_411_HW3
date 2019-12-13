package com.example.homeworktwo.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Course {

    protected String courseid;
    protected String grade;

    public Course(String course, String letter)
    {
        courseid = course;
        grade = letter;
    }

    public Course() {}

    public String getCourseid() {
        return courseid;
    }
    public String getGrade() {
        return grade;
    }

    public void setCourseid(String courseiden){courseid = courseiden;}
    public void setGrade(String Grade){grade = Grade;}

   // @Override
    public void insert(SQLiteDatabase db) {
        ContentValues vals = new ContentValues();
        vals.put("Grade", grade);
        vals.put("CourseID", courseid);

        db.insert("Student", null, vals);
    }
  //  @Override
    public void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Course (Grade Text, CourseID Text)");
    }

   // @Override
    public void initFrom(SQLiteDatabase db, Cursor c) {
        grade = c.getString(c.getColumnIndex("Grade"));
        courseid = c.getString(c.getColumnIndex("CourseID"));
        }

}
