package com.example.homeworkthree;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeworkthree.model.Student;
import com.example.homeworkthree.model.StudentDB;

public class AddStudentActivity extends AppCompatActivity {

    protected Menu detail;
    protected int studIndex;
    protected final String TAG = "add student screen";



    @Override
    protected void onCreate(Bundle savdInstance){
        Log.d(TAG, "onCreate() called");
        super.onCreate(savdInstance);
        setContentView(R.layout.activity_student_detail);

        studIndex = getIntent().getIntExtra("StudentIndex", 0);


        EditText fName = (EditText) findViewById(R.id.first_name_val_id);
        EditText lName = (EditText) findViewById(R.id.last_name_val_id);
        EditText id = (EditText) findViewById(R.id.cwid_val_id);

        fName.setEnabled(true);
        lName.setEnabled(true);
        id.setEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_student_screen_menu, menu);
        detail = menu;

        menu.findItem(R.id.action_student_done).setVisible(true);
        menu.findItem(R.id.action_add_student).setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.action_student_edit:
                EditText fName = (EditText) findViewById(R.id.first_name_val_id);
                EditText lName = (EditText) findViewById(R.id.last_name_val_id);
                EditText id = (EditText) findViewById(R.id.cwid_val_id);
                fName.setEnabled(true);
                lName.setEnabled(true);
                id.setEnabled(true);

                detail.findItem(R.id.action_student_done).setVisible(true);
                detail.findItem(R.id.action_add_student).setVisible(false);
                break;
            case R.id.action_student_done:
                fName = (EditText) findViewById(R.id.first_name_val_id);
                lName = (EditText) findViewById(R.id.last_name_val_id);
                id = (EditText) findViewById(R.id.cwid_val_id);


                Student sObj = new Student(fName.getText().toString(),lName.getText().toString(),
                        Integer.parseInt(id.getText().toString()));

                StudentDB.getInstance().getStudents().add(sObj);

                fName.setEnabled(false);
                lName.setEnabled(false);
                id.setEnabled(false);

                detail.findItem(R.id.action_student_done).setVisible(false);
                detail.findItem(R.id.action_add_student).setVisible(false);
                break;
            case R.id.action_add_student:
                Log.d(TAG, "Unintended addition option selected");
                break;

        }


        return super.onOptionsItemSelected(item);
    }

}
