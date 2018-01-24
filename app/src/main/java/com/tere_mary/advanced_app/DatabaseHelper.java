package com.tere_mary.advanced_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Theresia V A Mary G on 1/22/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Transaction.db";

    //table expenses
    public static final String TABLE_EXPENSES = "expenses";
    public static final String COLE_1 = "ID_Expenses";
    public static final String COLE_2 = "Description_Expenses";
    public static final String COLE_3 = "Amount_Expenses";

    //table income
    public static final String TABLE_INCOME = "income";
    public static final String COL_1 = "ID_Income";
    public static final String COL_2 = "Description_Income";
    public static final String COL_3 = "Amount_Income";

    //create table expenses
    public static final String TABLE_CREATE_EXPENSES = "CREATE TABLE " + TABLE_EXPENSES + " ( " +
            COLE_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLE_2 + " TEXT, " +
            COLE_3 + " INTEGER);";

    //create table income
    public static final String TABLE_CREATE_INCOME = "CREATE TABLE " + TABLE_INCOME + " ( " +
            COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_2 + " TEXT, " +
            COL_3 + " INTEGER);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_EXPENSES);
        db.execSQL(TABLE_CREATE_INCOME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOME);
        onCreate(db);
    }

    //save expenses
    public boolean save_expenses(String descEx, String AmountEx) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content_values = new ContentValues();
        content_values.put(COLE_2, descEx);
        content_values.put(COLE_3, AmountEx);
        long result = db.insert(TABLE_EXPENSES, null, content_values);

        return result != -1;
    }

    //list expenses
    public Cursor list_expenses() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor luar = db.rawQuery("SELECT * FROM " + TABLE_EXPENSES, null);
        return luar;
    }

    //update expenses
    public boolean update_expenses(String idex, String a, String b) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues content_values = new ContentValues();
        content_values.put(COLE_1, idex);
        content_values.put(COLE_2, a);
        content_values.put(COLE_3, b);
        db.update(TABLE_EXPENSES, content_values, "ID = ? ", new String[]{idex});

        return true;
    }

    //save income
    public boolean save_income(String descIn, String AmountIn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content_values = new ContentValues();
        content_values.put(COL_2, descIn);
        content_values.put(COL_3, AmountIn);
        long result = db.insert(TABLE_INCOME, null, content_values);

        return result != -1;
    }

    //list income
    public Cursor list_income() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor luar = db.rawQuery("SELECT * FROM " + TABLE_INCOME, null);
        return luar;
    }

    //update income
    public boolean update_income(String idin, String c, String d) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues content_values = new ContentValues();
        content_values.put(COL_1, idin);
        content_values.put(COL_2, c);
        content_values.put(COL_3, d);
        db.update(TABLE_INCOME, content_values, "ID = ? ", new String[]{idin});

        return true;
    }

}