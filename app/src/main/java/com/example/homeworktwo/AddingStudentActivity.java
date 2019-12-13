package com.example.homeworktwo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeworktwo.model.Student;
import com.example.homeworktwo.model.StudentDB;

public class AddingStudentActivity extends AppCompatActivity {

    protected Menu detailedmenu;
    protected int studentsindex;
    protected final String TAG = "Adding Student Screen";

    @Override
    protected void onCreate(Bundle savdInstance){
        Log.d(TAG, "onCreate() called");
        super.onCreate(savdInstance);
        setContentView(R.layout.activity_student_detail);

        studentsindex = getIntent().getIntExtra("studentIndex", 0);


        EditText fNameView = (EditText) findViewById(R.id.first_name);
        EditText lNameView = (EditText) findViewById(R.id.last_name);
        EditText studidView = (EditText) findViewById(R.id.stud_id);

        fNameView.setEnabled(true);
        lNameView.setEnabled(true);
        studidView.setEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_student_screen_menu, menu);
        detailedmenu = menu;


        menu.findItem(R.id.action_student_done).setVisible(true);
        menu.findItem(R.id.action_add_student).setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        EditText fNameView;
        EditText lNameView;
        EditText studidview;
    switch(item.getItemId()){

        case R.id.action_student_edit:
                fNameView = (EditText) findViewById(R.id.first_name);
                lNameView = (EditText) findViewById(R.id.last_name);
                studidview = (EditText) findViewById(R.id.stud_id);
                fNameView.setEnabled(true);
                lNameView.setEnabled(true);
                studidview.setEnabled(true);

                detailedmenu.findItem(R.id.action_student_done).setVisible(true);
                detailedmenu.findItem(R.id.action_add_student).setVisible(false);
                break;

        case R.id.action_student_done:

            fNameView = (EditText) findViewById(R.id.first_name);
            lNameView = (EditText) findViewById(R.id.last_name);
            studidview = (EditText) findViewById(R.id.stud_id);

            Student sObj = new Student(fNameView.getText().toString(),lNameView.getText().toString(), Integer.parseInt(studidview.getText().toString()));

            new StudentDB(this).getStudent().add(sObj);

            fNameView.setEnabled(false);
            lNameView.setEnabled(false);
            studidview.setEnabled(false);

            detailedmenu.findItem(R.id.action_student_done).setVisible(false);
            detailedmenu.findItem(R.id.action_add_student).setVisible(false);
            break;
        case R.id.action_add_student:
            Log.d(TAG, "Unintended \"adding student\" button clicked");
            break;
        };


        return super.onOptionsItemSelected(item);
    }
}
