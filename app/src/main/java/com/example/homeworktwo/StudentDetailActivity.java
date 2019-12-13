package com.example.homeworktwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.homeworktwo.model.Student;
import com.example.homeworktwo.model.StudentDB;


public class  StudentDetailActivity extends AppCompatActivity{

    protected Menu detailMenu;
    protected int studentIndex;
    protected final String TAG = "Detail Screen";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_student_detail);
        studentIndex = getIntent().getIntExtra("studentIndex",0);

        Student pObj = new StudentDB(this).getStudent().get(studentIndex);
        EditText fNameView = (EditText) findViewById(R.id.first_name);
        EditText lNameView = (EditText) findViewById(R.id.last_name);
        EditText studentID = (EditText) findViewById(R.id.stud_id);
        fNameView.setText(pObj.getFirstName());
        lNameView.setText(pObj.getLastName());
        studentID.setText(Integer.toString(pObj.getIdentification()));
        fNameView.setEnabled(false);
        lNameView.setEnabled(false);
        studentID.setEnabled(false);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_student_screen_menu, menu);
        detailMenu = menu;

        menu.findItem(R.id.action_student_edit).setVisible(true);
        menu.findItem(R.id.action_done).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        EditText fNameView;
        EditText lNameView;
        EditText studView;


        switch(item.getItemId()) {
            case R.id.action_student_edit:
                fNameView = (EditText) findViewById(R.id.first_name);
                lNameView = (EditText) findViewById(R.id.last_name);
                studView = (EditText) findViewById(R.id.stud_id);
                fNameView.setEnabled(true);
                lNameView.setEnabled(true);
                studView.setEnabled(true);
                detailMenu.findItem(R.id.action_student_edit).setVisible(false);
                detailMenu.findItem(R.id.action_student_done).setVisible(true);
                detailMenu.findItem(R.id.action_add_student).setVisible(false);
                break;
     case R.id.action_student_done:
                fNameView = (EditText) findViewById(R.id.first_name);
                lNameView = (EditText) findViewById(R.id.last_name);
                studView = (EditText) findViewById(R.id.stud_id);
                new StudentDB(this).getStudent().get(studentIndex).setFirstName(fNameView.getText().toString());
                new StudentDB(this).getStudent().get(studentIndex).setLastName(lNameView.getText().toString());
                new StudentDB(this).getStudent().get(studentIndex).setCWID(Integer.parseInt(studView.getText().toString()));
                fNameView.setEnabled(false);
                lNameView.setEnabled(false);
                studView.setEnabled(false);
                detailMenu.findItem(R.id.action_student_edit).setVisible(true);
                detailMenu.findItem(R.id.action_student_done).setVisible(false);
                detailMenu.findItem(R.id.action_add_student).setVisible(false);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
