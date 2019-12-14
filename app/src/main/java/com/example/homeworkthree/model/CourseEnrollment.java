package com.example.homeworkthree.model;

public class CourseEnrollment {
    private String ClassID;
    private String Letter;

    public CourseEnrollment(String IDclass, String letter){
        ClassID = IDclass;
       Letter = letter;
    }

    public String getClassID(){return ClassID;}
    public void setClassID(String IDclass){ClassID = IDclass;}

    public String getLetter(){return  Letter;}
    public void setLetter(String letter){Letter = letter;}
}
