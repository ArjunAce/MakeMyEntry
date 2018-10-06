package com.example.ace.makemyentry;

/**
 * Created by Ace on 30-Sep-18.
 */


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MyDBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "employeeDB.db";
    public static final String TABLE_NAME = "Employee";
    public static final String COLUMN_SSO = "SSO";
    public static final String COLUMN_NAME = "EmployeeName";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_AMOUNT = "Amount";
    //initialize the database
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_SSO +
                " INTEGER PRIMARY KEY, " + COLUMN_NAME + " TEXT, " + COLUMN_DATE + " TEXT, " + COLUMN_AMOUNT + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
     /*   db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);*/
    }

    public ArrayList<Employee> loadHandler() {
        String result = "";
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Employee> employeeArrayList =  new ArrayList<Employee>(5);
        while (cursor.moveToNext()) {
            int sso = cursor.getInt(0);
            String name = cursor.getString(1);
            String date = cursor.getString(2);
            int amount = cursor.getInt(3);
            employeeArrayList.add(new Employee(sso, name, date, amount));
            result += String.valueOf(sso) + " " + name + " " + date + " " + String.valueOf(amount) +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
//        return result;
        return employeeArrayList;
    }

    public Boolean addHandler(Employee employee) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_SSO, employee.getSSO());
        values.put(COLUMN_NAME, employee.getEmployeeName());
        values.put(COLUMN_DATE, employee.getDate());
        values.put(COLUMN_AMOUNT, employee.getAmount());
        SQLiteDatabase db = this.getWritableDatabase();
        long success = db.insert(TABLE_NAME, null, values);
        db.close();
        return success > 0? true: false;
    }

    public Employee findHandler(int sso) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_SSO + " = " + "'" + sso + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Employee employee = new Employee();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            employee.setSSO(Integer.parseInt(cursor.getString(0)));
            employee.setEmployeeName(cursor.getString(1));
            employee.setDate(cursor.getString(2));
            employee.setAmount(Integer.parseInt(cursor.getString(3)));
            cursor.close();
        } else {
            employee = null;
        }
        db.close();
        return employee;
    }

//    public boolean deleteHandler(int ID) {}
//
    public boolean updateHandler(int sso, int newBillAmount, String currentDateTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_AMOUNT, newBillAmount);
        args.put(COLUMN_DATE, currentDateTime);
        int numberOfRowsUpdated = db.update(TABLE_NAME, args, COLUMN_SSO + "=" + sso, null) ;
        return numberOfRowsUpdated == 1;
    }

    public void deleteHandler() {
        String DELETE_TABLE = "delete from " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DELETE_TABLE);
    }

}