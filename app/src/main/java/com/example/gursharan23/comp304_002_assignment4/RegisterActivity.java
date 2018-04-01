package com.example.gursharan23.comp304_002_assignment4;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    // Creating tables
    private static final String tables[]={"tbl_student","tbl_program","tbl_admin"};
    // Executing create table command
    private static final String tableCreatorString[] =
            {"CREATE TABLE tbl_student (studentId Integer PRIMARY KEY AUTOINCREMENT ,userName Text," +
                    "password Text,firstName TEXT, lastName TEXT,address Text,city Text,postalCode Text);",
                    "CREATE TABLE tbl_program (programCode Text PRIMARY KEY,programName Text," +
                            "duration Text,tuitionFee TEXT, semester TEXT);",
                    "CREATE TABLE tbl_admin (employeeId INTEGER PRIMARY KEY AUTOINCREMENT,userName Text," +
                            "password Text,firstName TEXT, lastName TEXT);"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final StudentDatabaseManager stdDB=new StudentDatabaseManager(this);

        stdDB.createDatabase(getApplicationContext());
        stdDB.dbInitialize(tables,tableCreatorString);
        // Creating fields of the student table
        final String fieldsStudents[]={"studentId","userName","password","firstName","lastName","" +
                "address","city","postalCode"};
        // Creating fields of the program table
        final String fieldsPrograms[]={"programCode","programName","tuitionFee","duration","semester"};
        // Creating fields of the admin table
        final String fieldsAdmin[]={"employeeId","userName","password","firstName","lastName"};
        // Initializing records
        final String recordPrograms[]=new String[5];
        final String recordStudents[]=new String[8];
        final String recordAdmin[]=new String[5];

        // Initializing widgets
        final Button submitButton=findViewById(R.id.submitBtn);
        final EditText userName=findViewById(R.id.userName_editText);
        final EditText password=findViewById(R.id.password_editText);
        final EditText firstName=findViewById(R.id.firstName_editText);
        final EditText lastName=findViewById(R.id.lastName_editText);
        final EditText address=findViewById(R.id.address_editText);
        final EditText city=findViewById(R.id.city_editText);
        final EditText pCode=findViewById(R.id.pCode_editText);
        final EditText programCode=findViewById(R.id.programCode_editText);
        final EditText programName=findViewById(R.id.programName_editText);

        // This method is an event handler for handling submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Assigning values of edit text to recordStudents
                recordStudents[1]=userName.getText().toString();
                recordStudents[2]=firstName.getText().toString();
                recordStudents[3]=lastName.getText().toString();
                recordStudents[4]=password.getText().toString();
                recordStudents[5]=address.getText().toString();
                recordStudents[6]=city.getText().toString();
                recordStudents[7]=pCode.getText().toString();

              // Assigning values of Program Code and Name to recordPrograms
                recordPrograms[0]=programCode.getText().toString();
                recordPrograms[1]=programName.getText().toString();
                recordPrograms[2]="8000";
                recordPrograms[3]="3";
                recordPrograms[4]="6";

                // Assigning values to recordAdmin
                recordAdmin[1]="admin";
                recordAdmin[2]="password";
                recordAdmin[3]="Admin";
                recordAdmin[4]="Singh";

                Log.d("User Name:",recordStudents[1]);
                ContentValues studentValues=new ContentValues();
                ContentValues programValues=new ContentValues();
                ContentValues adminValues=new ContentValues();
                for (int i=0;i<recordStudents.length;i++)
                {
                    studentValues.put(fieldsStudents[i],recordStudents[i]);
                }
                for (int k=0;k<recordPrograms.length;k++)
                {
                    programValues.put(fieldsPrograms[k],recordPrograms[k]);
                }
                for (int j=0;j<recordAdmin.length;j++)
                {
                    adminValues.put(fieldsAdmin[j],recordAdmin[j]);
                }
                stdDB.addRecord(studentValues,"tbl_student",fieldsStudents,recordStudents);
                stdDB.addRecord(programValues,"tbl_program",fieldsPrograms,recordPrograms);
                stdDB.addRecord(adminValues,"tbl_admin",fieldsAdmin,recordAdmin);
                Toast.makeText(getApplicationContext(),"Account created",Toast.LENGTH_LONG).show();
                // Starts the login activity
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
