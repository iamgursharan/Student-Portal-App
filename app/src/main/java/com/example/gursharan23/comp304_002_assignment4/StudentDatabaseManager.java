package com.example.gursharan23.comp304_002_assignment4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gursharan23 on 2018-03-28.
 * Name: Gursharan Singh
 * Description: This class creates the student databases that inherits SQLiteOpenHelper class
 * Version : 0.2 - Added the checkuserlogin method to authenticate the user
 */

public class StudentDatabaseManager extends SQLiteOpenHelper {

    //private instance variables
    private static final String Database_Name="RegisterPortal.db";
    private static final int Database_Version=14;
    private String tables[];
    private String tableCreatorString[];

    //+++++Constructor++++++
    public StudentDatabaseManager(Context context)
    {
        super(context,Database_Name,null,Database_Version);
    }

    public void dbInitialize(String[] tables, String tableCreaterString[])
    {
        this.tables=tables;
        this.tableCreatorString=tableCreaterString;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (int i=0;i<tables.length;i++)
        {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+" "+tables[i]);
        }
        for (int j=0;j<tables.length;j++)
        {
            sqLiteDatabase.execSQL(tableCreatorString[j]);
        }

    }

    public void createDatabase(Context context)
    {
        SQLiteDatabase database;
        database=context.openOrCreateDatabase(
                Database_Name,SQLiteDatabase.CREATE_IF_NECESSARY,null
        );
    }

    public void deleteDatabase(Context context)
    {
        context.deleteDatabase(Database_Name);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        for (int k=0; k<tables.length;k++)
        {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+" "+tables[k]);
            onCreate(sqLiteDatabase);
        }
    }

    // Database Operations
    public void addRecord(ContentValues values,String tableName,String fields[],String record[])
    {
        SQLiteDatabase db=this.getWritableDatabase();
        for (int i=1;i<record.length;i++)
            values.put(fields[i],record[i]);
        //Inserting rows
        db.insert(tableName,null, values);
        db.close();
    }

    //Read records
    public ArrayList getTable(String tableName)
    {
        ArrayList table=new ArrayList();
        // Selecting all rows
        String selectQuery="Select * From "+tableName;
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery(selectQuery,null);
        ArrayList row=new ArrayList();
        if(cursor.moveToFirst())
        {
            do
                {
                    for (int i=0;i<cursor.getColumnCount();i++)
                    {
                        row.add(cursor.getString(i));
                    }
                    table.add(row);
                }while(cursor.moveToFirst());
        }
        return  table;
    }

    //Update a record
    public int updateRecord(ContentValues values,String tableName,String fields[],String record[])
    {
        SQLiteDatabase database=this.getWritableDatabase();
        for (int i=1;i<record.length;i++)
            values.put(fields[i],record[i]);

        // Updating row
        return database.update(tableName,values,fields[0]+"=?",new String[]{record[0]});
    }

    public boolean checkStudentUser(String username,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String Query = "select * from tbl_student where userName='"+ username
                +"' and password='"+ password+"'";
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(Query, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(cursor!=null && cursor.getCount()>0){
            cursor.close();
            return true;
        }
        else{
            cursor.close();
            return false;
        }
    }
    public boolean checkAdminUser(String username,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String Query = "select * from tbl_Admin where userName='"+ username
                +"' and password='"+ password+"'";
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(Query, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(cursor!=null && cursor.getCount()>0){
            cursor.close();
            return true;
        }
        else{
            cursor.close();
            return false;
        }
    }
    // Delete a record
    public void deleteRecord(String tableName,String idName,String id)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        database.delete(tableName,idName+"= ?",new String[]{id});
        database.close();
    }
    public List getProgramsTable()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="select * from tbl_program";
        Cursor cursor=null;
        List row=new ArrayList();
        List table=new ArrayList();

        try
        {
            cursor=db.rawQuery(query,null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(cursor.moveToFirst())
        {
            do
                {
                   for(int i=0;i<cursor.getColumnCount();i++)
                   {
                       row.add(cursor.getString(i));
                   }
                   table.add(row);
                }while(cursor.moveToNext());
        }
        cursor.close();
        return table;
    }
/*
    public List getStudentName(String name)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="select * from tbl_student where userName='"+name;
        Cursor cursor=null;
        List table=new ArrayList();
        ArrayList row=new ArrayList();
        try
        {
            cursor=db.rawQuery(query,null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(cursor.moveToFirst())
        {
            do
            {
                for(int i=0;i<cursor.getColumnCount();i++)
                {
                    row.add(cursor.getString(i));
                }
                table.add(row);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return table;
    }

    public List getProgramName(String name)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="select programCode from tbl_program where programCode='"+name;
        Cursor cursor=null;
        List table=new ArrayList();
        ArrayList row=new ArrayList();
        try
        {
            cursor=db.rawQuery(query,null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(cursor.moveToFirst())
        {
            do
            {
                for(int i=0;i<cursor.getColumnCount();i++)
                {
                    row.add(cursor.getString(i));
                }
                table.add(row);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return table;
    }
*/
    public List getStatus(String name)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="select status from tbl_payment where studentName='"+name;
        Cursor cursor=null;
        List table=new ArrayList();
        ArrayList row=new ArrayList();
        try
        {
            cursor=db.rawQuery(query,null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(cursor.moveToFirst())
        {
            do
            {
                for(int i=0;i<cursor.getColumnCount();i++)
                {
                    row.add(cursor.getString(i));
                }
                table.add(row);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return table;
    }
}

