package com.example.homeworkthree.model;

import java.util.ArrayList;

public class Student {
    private String FirstName;
    private String Lastname;
    private int CWID;
    private ArrayList<CourseEnrollment> Courses;

    public Student(String first, String last, int id){
        FirstName = first;
        Lastname = last;
        CWID = id;
    }

    public String getFirst(){return FirstName;}
    public void setFirst(String first){FirstName = first;}

    public String getLast(){return Lastname;}
    public void setLast(String last){Lastname = last;}

    public int getid(){return CWID;}
    public void setid(int id){CWID = id;}

    public void setCourselist(ArrayList<CourseEnrollment> classes){Courses = classes;}
    public ArrayList<CourseEnrollment> getCourseslist(){return Courses;}

}
