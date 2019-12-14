package com.example.homeworkthree.model;

import com.example.homeworkthree.Database.DatabaseActivity;
import java.util.ArrayList;

public class StudentDB {
    private static final StudentDB ourInstance = new StudentDB();

    private ArrayList<Student> classmates;

    public static StudentDB getInstance() {return ourInstance;}

    private StudentDB(){createstudObjects();}

    public ArrayList<Student> getStudents(){return classmates;}

    public void setStudents(ArrayList<Student> students){classmates = students;}

    private void createstudObjects(){
        classmates = DatabaseActivity.getInstance().retrieveStudentObjects();
    }
}
