package com.example.homeworkthree.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.homeworkthree.R;
import com.example.homeworkthree.SummaryDetailActivity;
import com.example.homeworkthree.model.CourseEnrollment;
import com.example.homeworkthree.model.StudentDB;

public class CoursesLVAdapter extends BaseAdapter {

    private int studIndex;

    public CoursesLVAdapter(int studentIndex){this.studIndex = studentIndex;}

    @Override
    public int getCount(){return StudentDB.getInstance().getStudents().get(studIndex).getCourseslist().size();}

    @Override
    public Object getItem(int i){return StudentDB.getInstance().getStudents().get(studIndex).getCourseslist().get(i);}

    @Override
    public long getItemId(int i){return i;}

    @Override
    public View getView(int h, View looker, ViewGroup Group){

        View row_view;

        if (looker == null) {
            // cnt++;
            LayoutInflater inflater = LayoutInflater.from(Group.getContext());
            row_view = inflater.inflate(R.layout.course_row, Group, false);
        } else row_view = looker;

        CourseEnrollment c = StudentDB.getInstance().getStudents().get(studIndex).getCourseslist().get(h);

        TextView firstName = (TextView) row_view.findViewById(R.id.course_id);
        firstName.setText(c.getClassID());

        TextView lastName = (TextView) row_view.findViewById(R.id.grade);
        lastName.setText(c.getLetter());


        row_view.setTag(new Integer(h));

        row_view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //
                        Intent intent = new Intent(view.getContext(), SummaryDetailActivity.class);
                        intent.putExtra("StudentIndex", ((Integer)view.getTag()).intValue());
                        view.getContext().startActivity(intent);
                    }
                }
        );

        return row_view;


    }
}
