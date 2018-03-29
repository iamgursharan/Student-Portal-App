package com.example.gursharan23.comp304_002_assignment4;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    // Creating tables
    private static final String tables[]={"tbl_student","tbl_admin"};
    // Executing create table command
    private static final String tableCreatorString[] =
            {"CREATE TABLE tbl_student (studentId INTEGER PRIMARY KEY AUTOINCREMENT ,userName Text," +
                    "password Text,firstName TEXT, lastName TEXT,address Text,city Text,postalCode Text);",
                    "CREATE TABLE tbl_admin (employeeId INTEGER PRIMARY KEY AUTOINCREMENT ,userName Text," +
                            "password Text,firstName TEXT, lastName TEXT);"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final StudentDatabaseManager stdDB=new StudentDatabaseManager(this);
        stdDB.createDatabase(getApplicationContext());
        stdDB.dbInitialize(tables,tableCreatorString);
        final String fieldsStudents[]={"studentId","userName","password","firstName","lastName","" +
                "address","city","postalCode"};
        final String recordStudents[]=new String[8];

        // Initializing widgets
        final Button submitButton=findViewById(R.id.submitBtn);
        final EditText userName=findViewById(R.id.userName_editText);
        final EditText password=findViewById(R.id.password_editText);
        final EditText firstName=findViewById(R.id.firstName_editText);
        final EditText lastName=findViewById(R.id.lastName_editText);
        final EditText address=findViewById(R.id.address_editText);
        final EditText city=findViewById(R.id.city_editText);
        final EditText pCode=findViewById(R.id.pCode_editText);

        //
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Assigning values of edit text to
                recordStudents[0]="1";
                recordStudents[1]=userName.getText().toString();
                recordStudents[2]=firstName.getText().toString();
                recordStudents[3]=lastName.getText().toString();
                recordStudents[4]=password.getText().toString();
                recordStudents[5]=address.getText().toString();
                recordStudents[6]=city.getText().toString();
                recordStudents[7]=pCode.getText().toString();
                Log.d("User Name:",recordStudents[1]);
                ContentValues values=new ContentValues();
                for (int i=0;i<recordStudents.length;i++)
                {
                    values.put(fieldsStudents[i],recordStudents[i]);
                }
                stdDB.addRecord(values,"tbl_student",fieldsStudents,recordStudents);
                Toast.makeText(getApplicationContext(),recordStudents[0],Toast.LENGTH_LONG).show();
            }
        });

    }

}
