package com.example.gursharan23.comp304_002_assignment4;
/*
 * Name: Gursharan Singh
 * Id: 300931676
 * Description: This is student activity that shows the student's registration process
 * Version: 0.2- Added the programInfo and statusInfo method
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {
    // Declaring textviews
    TextView programCode;
    TextView studentNameText;
    TextView programName;
    TextView totalFees;
    TextView duration;
    TextView semester;
    TextView status;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        // Getting intent message from main activity
        Intent intent=getIntent();
        message=intent.getStringExtra(MainActivity.Send_Message);

        // Initializing the widgets
        studentNameText=findViewById(R.id.studentName_textview);
        programName=findViewById(R.id.programName_textview);
        programCode=findViewById(R.id.programCode_textview);
        totalFees=findViewById(R.id.totalFees_textView);
        duration=findViewById(R.id.duration_textView);
        semester=findViewById(R.id.semester_textView);
        status=findViewById(R.id.registrationStatus_textview);
        // Setting the values of the widget
        studentNameText.setText(message);
        programInfo();
        // Setting the status of student
  //      status.setText(statusInfo());

    }

    public void programInfo()
    {

        StudentDatabaseManager db=new StudentDatabaseManager(this);
        List table=db.getProgramsTable();
        for (Object o : table) {
            ArrayList row = (ArrayList)o;
            // Writing table to log
            String[] output=new String[10];
            for (int i=0;i<row.size();i++)
            {
                output[i]= row.get(i).toString() + " ";

            }
           Log.d("Output",output[4]);
            programName.setText(output[1]);
            programCode.setText(output[5]);
            totalFees.setText("$"+output[3]);
            duration.setText(output[2]+"yrs");
            semester.setText(output[4]);

        }
    }
/*
    public String statusInfo()
    {

        StudentDatabaseManager db=new StudentDatabaseManager(this);
        List table=db.getStatus(message);
        String[] output=new String[2];
        for (Object o : table) {
            ArrayList row = (ArrayList)o;
            // Writing table to log
            for (int i=0;i<row.size();i++)
            {
                output[i]= row.get(i).toString() + " ";

            }
            Log.d("Output",output[0]);

        }
        return output[0];
    }*/
}
