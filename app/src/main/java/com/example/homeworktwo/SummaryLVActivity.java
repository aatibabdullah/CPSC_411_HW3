package com.example.homeworktwo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeworktwo.R;
import com.example.homeworktwo.adapter.SummaryLVAdapter;
import com.example.homeworktwo.model.Student;
import com.example.homeworktwo.model.StudentDB;
import com.example.homeworktwo.model.Course;



public class SummaryLVActivity extends AppCompatActivity {

    protected ListView mSummaryView;
    protected final String TAG = "Summary Screen";
    protected SummaryLVAdapter ad;

    protected Menu dmenu;
    protected int IndexofStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);

        setContentView( R.layout.student_summary_listview);

        mSummaryView = findViewById(R.id.student_summary_listView_ID);
        ad = new SummaryLVAdapter();
        mSummaryView.setAdapter(ad);
    }


    @Override
    protected void onStart() {
        Log.d(TAG, "onStart() called");
        ad.notifyDataSetChanged();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu amenu) {
        getMenuInflater().inflate(R.menu.add_student_screen_menu, amenu);
        dmenu = amenu;

        amenu.findItem(R.id.action_student_done).setVisible(false);
        amenu.findItem(R.id.action_add_student).setVisible(true);
        return super.onCreateOptionsMenu(amenu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.action_student_edit) {
//            Log.d(TAG, "Unintended \"edit\" button clicked on menu");
//        } else
        if (item.getItemId() == R.id.action_student_done) {
            Log.d(TAG, "Unintended \"done\" option selected ed");
        } else if (item.getItemId() == R.id.action_add_student) {
            Intent intent = new Intent(this, AddingStudentActivity.class);
            this.startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
}
