package com.example.gursharan23.comp304_002_assignment4;

/*
* Name: Gursharan Singh
* Id: 300931676
* Description: This is the admin activity class that handles the student registration status and payment
* Version: 0.1-
*/

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {

    // Declaring views
    EditText searchString;
    TextView studentName;
    TextView program;
    TextView totalAmount;
    TextView amountPaid;
    TextView balance;
    // Creating tables
    private static final String tables[]={"tbl_payment"};
    // Executing create table command
    private static final String tableCreatorString[] =
            {"CREATE TABLE tbl_payment (Id INTEGER, Foreign KEY(Id) REFERENCES tbl_student(studentId) ,program Text," +
                    "FOREIGN KEY(program) REFERENCES tbl_program(programCode), totalAmount TEXT, amountPaid TEXT,balance Text,status Text,studentName Text," +
                    "FOREIGN KEY(studentName) REFERENCES tbl_student(userName));"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        StudentDatabaseManager db=new StudentDatabaseManager(getApplicationContext());
        db.dbInitialize(tables,tableCreatorString);
        final String fieldsPayment[]={"studentName","program","totalAmount","amountPaid","balance","status"};
        final String recordPayment[]=new String[6];
        //Setting amount balance
        double totalDouble=8000.00;
        double amountPaidDouble=5000.00;
        double balanceDouble=totalDouble-amountPaidDouble;
        // Retrieving username
        Intent intent=getIntent();
        String getUsername=intent.getStringExtra(MainActivity.Send_Message);
        TextView adminUsername=findViewById(R.id.adminUsername_textView);
        adminUsername.setText(getUsername);

        //Initializing views
        searchString=findViewById(R.id.searchUsername_editText);
        studentName=findViewById(R.id.name_textView);
        program=findViewById(R.id.program_textView);
        totalAmount=findViewById(R.id.totalAmount_textView);
        amountPaid=findViewById(R.id.amtPaid_textView);
        balance=findViewById(R.id.balance_textView);


        // Assigning values to spinner
        Spinner spinner=findViewById(R.id.status_spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.status_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

     //   programInfo();
        // Setting record values
        recordPayment[0]="";
        recordPayment[1]="";
        recordPayment[2]=Double.toString(totalDouble);
        recordPayment[3]=Double.toString(amountPaidDouble);
        recordPayment[4]=Double.toString(balanceDouble);
        recordPayment[5]=spinner.getSelectedItem().toString();

        ContentValues paymentValues=new ContentValues();
        for (int i=0;i<recordPayment.length;i++)
        {
            paymentValues.put(fieldsPayment[i],recordPayment[i]);
        }
        db.addRecord(paymentValues,"tbl_payment",fieldsPayment,recordPayment);
        program.setText(recordPayment[1]);
        totalAmount.setText(recordPayment[2]);
        amountPaid.setText(recordPayment[3]);
        balance.setText(recordPayment[4]);

    }
/*
    public String studentInfo()
    {

        StudentDatabaseManager db=new StudentDatabaseManager(this);
        List table=db.getStudentName(searchString.getText().toString());
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
    }

    public String programInfo()
    {

        StudentDatabaseManager db=new StudentDatabaseManager(this);
        List table=db.getStudentName(searchString.getText().toString());
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
