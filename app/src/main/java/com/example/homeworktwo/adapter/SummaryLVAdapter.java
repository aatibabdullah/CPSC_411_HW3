package com.example.homeworktwo.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.homeworktwo.StudentDetailActivity;
import com.example.homeworktwo.R;
import com.example.homeworktwo.model.Student;
import com.example.homeworktwo.model.StudentDB;

public class SummaryLVAdapter extends BaseAdapter {

    //protected int cnt = 0;
    StudentDB mstudentDB;

    @Override
    public int getCount() {
        return mstudentDB.getStudent().size();
    }

    @Override
    public Object getItem(int i) {
        return mstudentDB.getStudent().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row_view;

        if (view == null) {
            // cnt++;
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            row_view = inflater.inflate(R.layout.student_row, viewGroup, false);
        } else row_view = view;

        Student p = mstudentDB.getStudent().get(i);

        TextView firstNameView = (TextView) row_view.findViewById(R.id.first_name);
        firstNameView.setText(p.getFirstName());
        TextView lastNameView = (TextView) row_view.findViewById(R.id.last_name);
        lastNameView.setText(p.getLastName());
        TextView StudIDView = (TextView) row_view.findViewById(R.id.cwid);
        StudIDView.setText(Integer.toString(p.getIdentification()));
        row_view.setTag(new Integer(i));


        row_view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //
                        Intent intent = new Intent(view.getContext(), StudentDetailActivity.class);
                        intent.putExtra("studentIndex", ((Integer)view.getTag()).intValue());
                        view.getContext().startActivity(intent);
                    }
                }
        );

        return row_view;


    }
}
