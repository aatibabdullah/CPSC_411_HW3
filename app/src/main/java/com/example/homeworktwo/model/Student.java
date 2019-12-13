package com.example.homeworktwo.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Student {
    protected String FirstName;
    protected String LastName;
    protected int CWID;
    protected ArrayList<Course> nCourse;

    public Student(String fName, String lName, int id)
    {
        FirstName = fName;
        LastName = lName;
        CWID = id;
    }

    public Student(){}
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getIdentification(){return CWID;}

    public void setCWID(int number){ CWID = number;}



    public ArrayList<Course> getCourses() {
        return nCourse;
    }

    public void setCourses(ArrayList<Course> courses) {
        nCourse = courses;
    }

  //  @Override
    public void insert(SQLiteDatabase db)
    {
        ContentValues vals = new ContentValues();
        vals.put("FirstName", FirstName);
        vals.put("LastName",LastName);
        vals.put("CWID", CWID);

        db.insert("Student",null,vals);
        for(int i = 0; i<nCourse.size(); i++)
        {
            nCourse.get(i).insert(db);
        }

    }

   // @Override
    public void initFrom(SQLiteDatabase db, Cursor c)
    {
        FirstName= c.getString(c.getColumnIndex("FirstName"));
        LastName = c.getString(c.getColumnIndex("LastName"));
        CWID = c.getInt(c.getColumnIndex("SSN"));

        nCourse = new ArrayList<Course>();
        Cursor cursor = db.query("Vehicle",null, "Owner=?",new String[]{new Integer(CWID).toString()},null,null,null);
        if(cursor.getCount() >0)
        {
            while(cursor.moveToNext())
            {
                Course cObj = new Course();
                cObj.initFrom(db,cursor);
            }
        }
    }

    //@Override
    public void createTable(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS Student (FirstName Text, LastName Text, SSN INTEGER)");
    }


}
