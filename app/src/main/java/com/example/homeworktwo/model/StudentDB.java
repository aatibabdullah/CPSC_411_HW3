package com.example.homeworktwo.model;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;

public class StudentDB {
   // private static final StudentDB ourInstance = new StudentDB();

    protected ArrayList<Student> student;

    SQLiteDatabase mSQLiteDatabase;

    //static public StudentDB getInstance() { return ourInstance;}

    public StudentDB(Context context) {

        File dbFile = context.getDatabasePath("student.db");

        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);

        // Create the Person and Vehicle tables if necessary
        new Student().createTable(mSQLiteDatabase);
        new Course().createTable(mSQLiteDatabase);
        //createStudentObjects();
    }
    public ArrayList<Student> getStudent() {return student;}

    public void setStudent(ArrayList<Student> stud){ student = stud; }

    public ArrayList<Student> retrieveStudentObjects(){
    student = new ArrayList<Student>();
    Cursor cursor = mSQLiteDatabase.query("Student",null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Student pObj = new Student();
                pObj.initFrom(mSQLiteDatabase, cursor);
                student.add(pObj);
            }
        }
        return student;
    }


    protected void createStudentObjects(){
        Student s;

        ArrayList<Course> coursesenrolled = new ArrayList<Course>();
        student = new ArrayList<Student>();

        s = new Student("John", "Marston", 889500617);
        coursesenrolled.add(new Course("MATH 250","A"));
        coursesenrolled.add(new Course("CPSC 335","C"));
        s.setCourses(coursesenrolled);

        student.add(s);

        s = new Student("Javier", "Machoman", 201151000);
        coursesenrolled = new ArrayList<Course>();
        coursesenrolled.add(new Course("MATH 250","A"));
        coursesenrolled.add(new Course("CPSC 335","C"));
        s.setCourses(coursesenrolled);

        student.add(s);
    }


}
