package com.example.homeworktwo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.io.File;


public class DatabaseActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        File dbFile = this.getDatabasePath("test02.db");
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS Student(FirstName Test, LastName Text, SSN INTEGER)");

        db.execSQL("DELETE FROM Student WHERE SSN = ?", new String[]{"889500617"});
        db.execSQL("INSERT INTO Student VALUES ('John', 'Marston',889500617)");

        db.execSQL("DELETE FROM Student WHERE SSN = ?", new String[]{"201151000"});
        db.execSQL("INSERT INTO Person VALUES (?,?,?)", new String[]{"Javier", "Machoman", "201151000"});
        db.delete("Person", "SSN=?", new String[]{"757484499"});

        ContentValues vals = new ContentValues();
        vals.put("FirstName", "Jerry");
        vals.put("LastName", "Ramos");
        vals.put("SSN", 757484499);
        db.insert("Student", null, vals);

        Cursor cursor = db.query("Student",null, null, null, null,null,null);
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()) {
                String fName = cursor.getString(cursor.getColumnIndex("FirstName"));
                int ssn = cursor.getInt(cursor.getColumnIndex("SSN"));
                //
                Log.d("Database Log", fName + " : " + ssn);
            }

            db.execSQL("UPDATE Student SET FirstName =? WHERE SSN=?", new String[]{"John", "889500617"});
            //
            vals = new ContentValues();
            vals.put("FirstName", "Rudy");
            db.update("Student", vals, "SSN=?", new String[]{"757484499"});

            db.close();
        }

    }
}
