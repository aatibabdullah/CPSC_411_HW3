package com.example.homeworkthree;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.homeworkthree.adapter.CoursesLVAdapter;
import com.example.homeworkthree.model.Student;
import com.example.homeworkthree.model.StudentDB;

public class SummaryDetailActivity extends AppCompatActivity {

    protected Menu detail;
    protected int studIndex;
    public static final String LocationOfStudentIndex = "com.example.homeworkthree.SummaryDetailActivity.java";
    protected final String TAG = "Detail Student Screen ";

    protected View root;

    protected ListView courseSummaryView;
    protected CoursesLVAdapter der;

    @Override
    protected void onCreate(Bundle savdInstance){
        Log.d(TAG, "onCreate() called");
        super.onCreate(savdInstance);
        setContentView(R.layout.activity_student_detail);

        studIndex = getIntent().getIntExtra("StudentIndex", 0);

        Student sObj = StudentDB.getInstance().getStudents().get(studIndex);
        EditText fName = (EditText) findViewById(R.id.first_name_val_id);
        EditText lName = (EditText) findViewById(R.id.last_name_val_id);
        EditText id = (EditText) findViewById(R.id.cwid_val_id);
        fName.setText(sObj.getFirst());
        lName.setText(sObj.getLast());
        id.setText(Integer.toString(sObj.getid()));
        fName.setEnabled(false);
        lName.setEnabled(false);
        id.setEnabled(false);

        // Need to implement to show courses
        Button addCourseButton = (Button) findViewById(R.id.button_add_course);

        addCourseButton.setOnClickListener(
            new Button.OnClickListener() {
                @Override
                public void onClick(View viewObject) {

            new  AddCourseActivity();
                }
            }
        );

        courseSummaryView = findViewById(R.id.courses_listview_id);
        der = new CoursesLVAdapter(studIndex);
        courseSummaryView.setAdapter(der);
    }


    @Override
    protected void onStart() {
        Log.d(TAG, "onStart() called");
        der.notifyDataSetChanged();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_student_screen_menu, menu);
        detail = menu;
        menu.findItem(R.id.action_student_edit).setVisible(true);
        menu.findItem(R.id.action_student_done).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem it) {

        switch(it.getItemId())
        {
            case R.id.action_student_edit:
                EditText fName = (EditText) findViewById(R.id.first_name_val_id);
                EditText lName = (EditText) findViewById(R.id.last_name_val_id);
                EditText id = (EditText) findViewById(R.id.cwid_val_id);
                fName.setEnabled(true);
                lName.setEnabled(true);
                id.setEnabled(true);
                detail.findItem(R.id.action_student_edit).setVisible(false);
                detail.findItem(R.id.action_student_done).setVisible(true);
                detail.findItem(R.id.action_add_student).setVisible(false);
                break;
            case R.id.action_student_done:
                fName = (EditText) findViewById(R.id.first_name_val_id);
                lName = (EditText) findViewById(R.id.last_name_val_id);
                id = (EditText) findViewById(R.id.cwid_val_id);
                StudentDB.getInstance().getStudents().get(studIndex).setFirst(fName.getText().toString());
                StudentDB.getInstance().getStudents().get(studIndex).setLast(lName.getText().toString());
                StudentDB.getInstance().getStudents().get(studIndex).setid(Integer.parseInt(id.getText().toString()));
                fName.setEnabled(false);
                lName.setEnabled(false);
                id.setEnabled(false);
                detail.findItem(R.id.action_student_edit).setVisible(true);
                detail.findItem(R.id.action_student_done).setVisible(false);
                detail.findItem(R.id.action_add_student).setVisible(false);
                break;
        }

        return super.onOptionsItemSelected(it);
    }
}
