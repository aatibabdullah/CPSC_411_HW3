package com.example.homeworkthree;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeworkthree.model.CourseEnrollment;
import com.example.homeworkthree.model.StudentDB;

public class AddCourseActivity extends AppCompatActivity {

    protected Menu detail;
    protected int studIndex;
    protected final String TAG = "add course screen";

    private boolean wascoursesetprevious = false;
    private int IndexofNewInstanceCourse;

    @Override
    protected void onCreate(Bundle savdInstance){
        Log.d(TAG, "onCreate() called");
        super.onCreate(savdInstance);
        setContentView(R.layout.add_new_course_form);

        studIndex = getIntent().getIntExtra(SummaryDetailActivity.LocationOfStudentIndex, 0);


        EditText newCourseNameView = (EditText) findViewById(R.id.new_course_id);
        EditText newLetterGradeView = (EditText) findViewById(R.id.new_letter_id);

        newCourseNameView.setEnabled(true);
        newLetterGradeView.setEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_course_screen_menu, menu);
        detail = menu;

//        menu.findItem(R.id.action_student_edit).setVisible(false);
        menu.findItem(R.id.add_new_course_done).setVisible(true);
        menu.findItem(R.id.edit_new_course).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        EditText Newcoursename = (EditText) findViewById(R.id.new_course_id);
        EditText Newlettergrade = (EditText) findViewById(R.id.new_letter_id);



        if(item.getItemId() == R.id.add_new_course_done)
        {

            int boller;

            if(!wascoursesetprevious)
            {
                boller = 1;
            }
            else{
                boller = 0;
            }

            switch(boller)
            {
                case 1:
                    CourseEnrollment ceObject = new CourseEnrollment(Newcoursename.getText().toString(),
                            Newlettergrade.getText().toString());
                    StudentDB.getInstance().getStudents().get(studIndex).getCourseslist().add(ceObject);
                    wascoursesetprevious = true;
                    IndexofNewInstanceCourse = StudentDB.getInstance().getStudents().get(studIndex).getCourseslist().indexOf(ceObject);
                    break;
                case 0:
                    StudentDB.getInstance().getStudents().get(studIndex).getCourseslist().get(IndexofNewInstanceCourse).setClassID(Newcoursename.getText().toString());
                    StudentDB.getInstance().getStudents().get(studIndex).getCourseslist().get(IndexofNewInstanceCourse).setLetter(Newlettergrade.getText().toString());
                    break;
            }


            Newcoursename.setEnabled(false);
            Newlettergrade.setEnabled(false);

            detail.findItem(R.id.add_new_course_done).setVisible(false);
            detail.findItem(R.id.edit_new_course).setVisible(true);


        }
        else if(item.getItemId() == R.id.edit_new_course)
        {
            Newcoursename.setEnabled(true);
            Newlettergrade.setEnabled(true);

            detail.findItem(R.id.add_new_course_done).setVisible(true);
            detail.findItem(R.id.edit_new_course).setVisible(false);


        }

        return super.onOptionsItemSelected(item);
    }
}
