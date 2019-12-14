package com.example.homeworkthree;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeworkthree.adapter.StudentLVAdapter;



public class SummaryLVActivity extends AppCompatActivity {

    protected ListView studentView;
    protected final String TAG = "Summary Screen";
    protected StudentLVAdapter der;

    protected Menu detail;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstance){
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstance);
        context = this;
        setContentView(R.layout.student_summary_listview);

        studentView = findViewById(R.id.student_summary_listView_ID);
        der = new StudentLVAdapter();
        studentView.setAdapter(der);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart() called");
        der.notifyDataSetChanged();
        super.onStart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_student_screen_menu, menu);
        detail = menu;

        menu.findItem(R.id.action_student_done).setVisible(false);
        menu.findItem(R.id.action_add_student).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {



        switch(item.getItemId())
        {
            case R.id.action_student_done:
                Log.d(TAG, "Unintended done option selected");
                break;
            case R.id.action_add_student:
                Intent intent = new Intent(this, AddStudentActivity.class);
                this.startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}


