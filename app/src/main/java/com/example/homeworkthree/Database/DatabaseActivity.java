package com.example.homeworkthree.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.homeworkthree.SummaryLVActivity;
import com.example.homeworkthree.model.CourseEnrollment;
import com.example.homeworkthree.model.Student;

import java.io.File;
import java.util.ArrayList;

public class DatabaseActivity {
    private String fileDB = "homeworkthree.db";
    private File FilePathDB;
    private SQLiteDatabase ourDB;

    private static final DatabaseActivity theInstance = new DatabaseActivity();

    public static DatabaseActivity getInstance(){return theInstance;}

    private DatabaseActivity(){


        FilePathDB = SummaryLVActivity.context.getDatabasePath(fileDB);

        if(!FilePathDB.exists()) {
            ourDB = SQLiteDatabase.openOrCreateDatabase(FilePathDB, null);

            ourDB.execSQL("CREATE TABLE IF NOT EXISTS Student (fName Text, lName, CWID INTEGER)");
            ourDB.execSQL("CREATE TABLE IF NOT EXISTS CourseEnrollment (CWID INTEGER, CouseID Text, Grade Text)");

            ourDB.execSQL("INSERT INTO Student VALUES ('John', 'Marston', 889500617)");
            ourDB.execSQL("INSERT INTO CourseEnrollment VALUES (889500617, 'MATH 338', 'B+')");
            ourDB.execSQL("INSERT INTO CourseEnrollment VALUES (889500617, 'CPSC 323', 'A')");
            ourDB.execSQL("INSERT INTO CourseEnrollment VALUES (889500617, 'ENG 101', 'C')");
            ourDB.execSQL("INSERT INTO CourseEnrollment VALUES (889500617, 'HIST 110A', 'C-')");

            ourDB.execSQL("INSERT INTO Student VALUES ('Harry', 'Callahan', 983215617)");
            ourDB.execSQL("INSERT INTO CourseEnrollment VALUES (983215617, 'CPSC 315', 'B')");
            ourDB.execSQL("INSERT INTO CourseEnrollment VALUES (983215617, 'CPSC 353', 'B-')");
            ourDB.execSQL("INSERT INTO CourseEnrollment VALUES (983215617, 'HCOM 101', 'C+')");
            ourDB.execSQL("INSERT INTO CourseEnrollment VALUES (983215617, 'MATH 338', 'A+')");
            ourDB.close();
        }
    }

    public ArrayList<Student> retrieveStudentObjects(){


        Student studentobj;
        ArrayList<Student> studentlist = new ArrayList<Student>();
        ArrayList<CourseEnrollment> Classes = new ArrayList<CourseEnrollment>();

        ourDB = SQLiteDatabase.openOrCreateDatabase(FilePathDB, null);

        Cursor curs = ourDB.query("Student",null,null,null,null,null,null);


        int i;

        if(curs.getCount() > 0)
        {
            i = 1;
        }
        else{i = 0;}


        switch(i)
        {
            case 1:
                while(curs.moveToNext()){
                    String first = curs.getString(curs.getColumnIndex("fName"));
                    String last = curs.getString(curs.getColumnIndex("lName"));
                    int id = curs.getInt(curs.getColumnIndex("CWID"));

                    studentobj = new Student(first, last,id);

                    Cursor innerCurs = ourDB.query("CourseEnrollment",null,null,null,null,null,null);
                    int j;

                    if (innerCurs.getCount() > 0){
                        j = 0;
                    }else{j = 1;}

                    switch(j) {
                        case 0:
                                while (innerCurs.moveToNext()) {
                                    int foriegnKey = innerCurs.getInt(innerCurs.getColumnIndex("CWID"));
                                    String courseName = innerCurs.getString(innerCurs.getColumnIndex("CourseID"));
                                    String gradeLetter = innerCurs.getString(innerCurs.getColumnIndex("Grade"));

                                    if (id == foriegnKey)
                                        Classes.add(new CourseEnrollment(courseName, gradeLetter));
                                }
                            break;
                        case 1:
                            break;
                    }

                    innerCurs.close();
                    studentobj.setCourselist(Classes);
                    studentlist.add(studentobj);
                }
                break;

            case 0:
                    break;
        }


        curs.close();
        ourDB.close();
        return studentlist;
    }

}
