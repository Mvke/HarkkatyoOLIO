package com.example.harkkatyo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 8;


    private static  final  String DBNAME = "database.db";


    private static final String TABLE_NAME1 = "bank";
    private static final String BANK_ID = "bankid";

    private static final String TABLE_NAME2 = "admin";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private static final String TABLE_NAME3 = "user";
    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String AGE = "age";

    private static final String TABLE_NAME4 = "currentaccount";
    private static final String ACCOUNT_NAME = "accountname";
    private static final String ACCOUNT_NUMBER = "accountnumber";
    private static final String AMMOUNT = "ammount";
    private static final String PAYLIMIT = "paylimit";

    private static final String TABLE_NAME5 = "savingsaccount";

    private static final String TABLE_NAME6 = "creditcard";
    private static final String CARD_NUMBER = "cardnumber";
    private static final String CARD_NAME = "cardname";
    private static final String BUYLIMIT = "buylimit";
    private static final String CREDIT = "credit";

    private static final String TABLE_NAME7 = "debitcard";

    private static final String TABLE_NAME8 = "transactionlog";
    private static final String ACCOUNT_NUMBER1= "accountnumber1";
    private static final String ACCOUNT_NUMBER2 = "accountnumber2";
    private static final String MONEY_GO = "moneygo";






    public DatabaseHelper(Context context){
        super(context, DBNAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME1 + "(\n" + BANK_ID +  " INTEGER PRIMARY KEY\n)");

        db.execSQL("CREATE TABLE " + TABLE_NAME2 + "(\n" +
                 BANK_ID + " INTEGER,\n" +
                 USERNAME + " VARCHAR(10) PRIMARY KEY,\n" +
                 PASSWORD+ " VARCHAR(10),\n" +
                "CONSTRAINT Bank\n" +
                "FOREIGN KEY (bankid) REFERENCES bank(bankid)\n" +
                "ON DELETE CASCADE\n" +
                ")");
        db.execSQL("CREATE TABLE " + TABLE_NAME3 + "(\n" +
                BANK_ID + " INTEGER,\n" +
                USERNAME + " VARCHAR(10) PRIMARY KEY,\n" +
                PASSWORD+ " VARCHAR(10),\n" +
                FIRST_NAME+ " VARCHAR(10),\n" +
                LAST_NAME+ " VARCHAR(20),\n" +
                AGE+ " INTEGER,\n" +
                "CONSTRAINT Bank\n" +
                "CHECK  (age>=18)," +
                "FOREIGN KEY (bankid) REFERENCES bank(bankid)\n" +
                "ON DELETE CASCADE\n" +
                ")");
        db.execSQL("CREATE TABLE " + TABLE_NAME4 + "(\n" +
                USERNAME + " VARCHAR(10),\n" +
                ACCOUNT_NUMBER + " INTEGER PRIMARY KEY,\n" +
                ACCOUNT_NAME + " VARCHAR(20),\n" +
                AMMOUNT + " FLOAT ,\n" +
                PAYLIMIT+ " INTEGER,\n" +
                "CONSTRAINT userid\n" +
                "FOREIGN KEY (username) REFERENCES user(username)\n" +
                "ON DELETE CASCADE\n" +
                ")");
        db.execSQL("CREATE TABLE " + TABLE_NAME5 + "(\n" +
                USERNAME + " VARCHAR(10),\n" +
                ACCOUNT_NUMBER + " INTEGER PRIMARY KEY,\n" +
                ACCOUNT_NAME + " VARCHAR(20),\n" +
                AMMOUNT + " FLOAT ,\n" +
                "CONSTRAINT userid \n" +
                "FOREIGN KEY (username) REFERENCES user(username)\n" +
                "ON DELETE CASCADE\n" +
                ")");
        db.execSQL("CREATE TABLE " + TABLE_NAME6 + "(\n" +
                CARD_NUMBER + " INTEGER PRIMARY KEY,\n" +
                CARD_NAME + " VARCHAR(20),\n" +
                ACCOUNT_NUMBER + " INTEGER,\n" +
                BUYLIMIT + " INTEGER,\n" +
                CREDIT + " INTEGER ,\n" +
                "CONSTRAINT accountid\n" +
                "FOREIGN KEY (accountnumber) REFERENCES currentaccount(accountnumber)\n" +
                "ON DELETE CASCADE\n" +
                ")");

        db.execSQL("CREATE TABLE " + TABLE_NAME7 + "(\n" +
                CARD_NUMBER + " INTEGER PRIMARY KEY,\n" +
                CARD_NAME + " VARCHAR(20),\n" +
                ACCOUNT_NUMBER + " INTEGER,\n" +
                BUYLIMIT + " INTEGER,\n" +
                "CONSTRAINT  accountid\n" +
                "FOREIGN KEY (accountnumber) REFERENCES currentaccount(accountnumber)\n" +
                "ON DELETE CASCADE\n" +
                ")");

        db.execSQL("CREATE TABLE " + TABLE_NAME8 + "(\n" +
                ACCOUNT_NUMBER1 + " INTEGER,\n" +
                ACCOUNT_NUMBER2 + " INTEGER,\n" +
                MONEY_GO + " VARCHAR(10),\n" +
                "CONSTRAINT  accountid1\n" +
                "FOREIGN KEY (accountnumber1) REFERENCES currentaccount(accountnumber),\n" +
                "CONSTRAINT  accountid2\n" +
                "FOREIGN KEY (accountnumber2) REFERENCES savingsaccount(accountnumber)\n" +
                "ON DELETE CASCADE\n" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME1);
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME2);
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME3);
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME4);
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME5);
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME6);
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME7);
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME8);
            onCreate(db);

    }
    public void addDataToUser(String item1, String item2,String item3, String item4,String item5, String item6){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BANK_ID, item1);
        contentValues.put(USERNAME, item2);
        contentValues.put(PASSWORD, item3);
        contentValues.put(FIRST_NAME, item4);
        contentValues.put(LAST_NAME, item5);
        contentValues.put(AGE, item6);
        db.insert(TABLE_NAME3, null, contentValues);

    }
    public void updateUser(String item1, String item2,String item3, String item4,String item5, String item6){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BANK_ID, item1);
        contentValues.put(USERNAME, item2);
        contentValues.put(PASSWORD, item3);
        contentValues.put(FIRST_NAME, item4);
        contentValues.put(LAST_NAME, item5);
        contentValues.put(AGE, item6);
        db.update(TABLE_NAME3, contentValues, "USERNAME=?", new String[]{item2});

    }

    public void addDataToCurrentAccount(String item1, String item2, float item3, int item4, int item5){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, item1);
        contentValues.put(ACCOUNT_NUMBER, item5);
        contentValues.put(ACCOUNT_NAME, item2);
        contentValues.put(AMMOUNT, item3);
        contentValues.put(PAYLIMIT, item4);
        db.insert(TABLE_NAME4, null, contentValues);

    }

    public void addDataToSavingsAccount(String item1, String item2, float item3, int item4){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, item1);
        contentValues.put(ACCOUNT_NUMBER, item4);
        contentValues.put(ACCOUNT_NAME, item2);
        contentValues.put(AMMOUNT, item3);
        db.insert(TABLE_NAME5, null, contentValues);

    }

    public void addDataToDebitCard(int item1, int item2, int item3, String item4){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CARD_NUMBER, item1);
        contentValues.put(CARD_NAME, item4);
        contentValues.put(ACCOUNT_NUMBER,item2);
        contentValues.put(BUYLIMIT, item3);
        db.insert(TABLE_NAME7, null, contentValues);
    }

    public void addDataToCreditCard(int item1, int item2, int item3, int item4, String item5){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CARD_NUMBER, item1);
        contentValues.put(CARD_NAME, item5);
        contentValues.put(ACCOUNT_NUMBER,item2);
        contentValues.put(BUYLIMIT, item3);
        contentValues.put(CREDIT, item4);
        db.insert(TABLE_NAME6, null, contentValues);
    }

    public Cursor userdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user", null);
        return cursor;
    }

    public Cursor currentaccountData(String item1){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM currentaccount WHERE username = '" + item1 +"'", null);
        return cursor;

    }

    public Cursor savingaccountdata(String item1){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM savingsaccount WHERE username = '" + item1 +"'" , null);
        return cursor;
    }

    public void deleteAccount(String item1){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM user WHERE username = '"+item1 +"'");
    }


}