package com.example.harkkatyo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION =15;


    private static  final  String DBNAME = "database.db";


    private static final String TABLE_NAME1 = "bank";
    private static final String BANK_ID = "bankid";
    private static final String BANK_NAME = "bankname";

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
    //Here I create SQL tables using SQL commands mixed with variables.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME1 + "(\n" + BANK_ID +  " INTEGER PRIMARY KEY,\n" +
                BANK_NAME+ " VARCHAR(20)\n" +
                ")");

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
                ACCOUNT_NUMBER + " VARCHAR(20) PRIMARY KEY,\n" +
                ACCOUNT_NAME + " VARCHAR(20),\n" +
                AMMOUNT + " FLOAT ,\n" +
                PAYLIMIT+ " INTEGER,\n" +
                "CONSTRAINT userid\n" +
                "FOREIGN KEY (username) REFERENCES user(username)\n" +
                "ON DELETE CASCADE\n" +
                ")");
        db.execSQL("CREATE TABLE " + TABLE_NAME5 + "(\n" +
                USERNAME + " VARCHAR(10),\n" +
                ACCOUNT_NUMBER + " VARCHAR(20) PRIMARY KEY,\n" +
                ACCOUNT_NAME + " VARCHAR(20),\n" +
                AMMOUNT + " FLOAT ,\n" +
                "CONSTRAINT userid \n" +
                "FOREIGN KEY (username) REFERENCES user(username)\n" +
                "ON DELETE CASCADE\n" +
                ")");
        db.execSQL("CREATE TABLE " + TABLE_NAME6 + "(\n" +
                CARD_NUMBER + " VARCHAR(10) PRIMARY KEY,\n" +
                CARD_NAME + " VARCHAR(20),\n" +
                ACCOUNT_NUMBER + " VARCHAR(20),\n" +
                BUYLIMIT + " INTEGER,\n" +
                CREDIT + " INTEGER ,\n" +
                "CONSTRAINT accountid\n" +
                "FOREIGN KEY (accountnumber) REFERENCES currentaccount(accountnumber)\n" +
                "ON DELETE CASCADE\n" +
                ")");

        db.execSQL("CREATE TABLE " + TABLE_NAME7 + "(\n" +
                CARD_NUMBER + " VARCHAR(10) PRIMARY KEY,\n" +
                CARD_NAME + " VARCHAR(20),\n" +
                ACCOUNT_NUMBER + " VARCHAR(20),\n" +
                BUYLIMIT + " INTEGER,\n" +
                "CONSTRAINT  accountid\n" +
                "FOREIGN KEY (accountnumber) REFERENCES currentaccount(accountnumber)\n" +
                "ON DELETE CASCADE\n" +
                ")");

        db.execSQL("CREATE TABLE " + TABLE_NAME8 + "(\n" +
                ACCOUNT_NUMBER1 + " VARCHAR(20),\n" +
                ACCOUNT_NUMBER2 + " VARCHAR(20),\n" +
                MONEY_GO + " VARCHAR(20),\n" +
                USERNAME + " VARCHAR(10)\n" +
                ")");

        db.execSQL("PRAGMA foreign_keys=on");
        db.execSQL("INSERT INTO bank (bankid, bankname) VALUES ('1', 'Osuuspankki')");

    }
    //This checks if table named x exist it deletes it before creating it.
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
    /*So this part on i have lots of add data to x and update x.
    'Add data to' adds information to table and 'update' updates already existing information*/
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

    public void addDataToCurrentAccount(String item1, String item2, float item3, int item4, String item5){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, item1);
        contentValues.put(ACCOUNT_NUMBER, item5);
        contentValues.put(ACCOUNT_NAME, item2);
        contentValues.put(AMMOUNT, item3);
        contentValues.put(PAYLIMIT, item4);
        db.insert(TABLE_NAME4, null, contentValues);

    }

    public void updateCurrentAccount(String item1, String item2, float item3, int item4, String item5){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, item1);
        contentValues.put(ACCOUNT_NUMBER, item5);
        contentValues.put(ACCOUNT_NAME, item2);
        contentValues.put(AMMOUNT, item3);
        contentValues.put(PAYLIMIT, item4);
        db.update(TABLE_NAME4, contentValues, "accountnumber=?", new String[]{item5});
    }

    public void addDataToSavingsAccount(String item1, String item2, float item3, String  item4){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, item1);
        contentValues.put(ACCOUNT_NUMBER, item4);
        contentValues.put(ACCOUNT_NAME, item2);
        contentValues.put(AMMOUNT, item3);
        db.insert(TABLE_NAME5, null, contentValues);

    }

    public void updateSavingsAccount(String item1, String item2, float item3, String  item4){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, item1);
        contentValues.put(ACCOUNT_NUMBER, item4);
        contentValues.put(ACCOUNT_NAME, item2);
        contentValues.put(AMMOUNT, item3);
        db.update(TABLE_NAME5, contentValues, "accountnumber=?", new String[]{item4});
    }

    public void addDataToDebitCard(String item1, String item2, int item3, String item4){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CARD_NUMBER, item1);
        contentValues.put(CARD_NAME, item4);
        contentValues.put(ACCOUNT_NUMBER,item2);
        contentValues.put(BUYLIMIT, item3);
        db.insert(TABLE_NAME7, null, contentValues);
    }
    public void updateDebitCard(String item1, String item2, int item3,String item5){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CARD_NUMBER, item1);
        contentValues.put(CARD_NAME, item5);
        contentValues.put(ACCOUNT_NUMBER,item2);
        contentValues.put(BUYLIMIT, item3);
        db.update(TABLE_NAME6, contentValues, "cardnumber=?", new String[]{item1});
    }

    public void addDataToCreditCard(String item1, String item2, int item3, int item4, String item5){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CARD_NUMBER, item1);
        contentValues.put(CARD_NAME, item5);
        contentValues.put(ACCOUNT_NUMBER,item2);
        contentValues.put(BUYLIMIT, item3);
        contentValues.put(CREDIT, item4);
        db.insert(TABLE_NAME6, null, contentValues);
    }

    public void updateCreditCard(String item1, String item2, int item3, int item4, String item5){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CARD_NUMBER, item1);
        contentValues.put(CARD_NAME, item5);
        contentValues.put(ACCOUNT_NUMBER,item2);
        contentValues.put(BUYLIMIT, item3);
        contentValues.put(CREDIT, item4);
        db.update(TABLE_NAME6, contentValues, "cardnumber=?", new String[]{item1});
    }

    public void addDataToTransactionLog(String item1, String item2, String item3, String item4){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ACCOUNT_NUMBER1, item1);
        contentValues.put(ACCOUNT_NUMBER2,item2);
        contentValues.put(MONEY_GO, item3);
        contentValues.put(USERNAME, item4);
        db.insert(TABLE_NAME8, null, contentValues);
    }
    //These cursors get information out from the database.
    public Cursor userdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user", null);
        return cursor;
    }
    public Cursor bankdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM bank WHERE bankid = 1", null);
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

    public Cursor debitcarddata(String item1){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM debitcard WHERE accountnumber = '" + item1 +"'" , null);
        return cursor;
    }

    public Cursor creditcarddata(String item1){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM creditcard WHERE accountnumber = '" + item1 +"'" , null);
        return cursor;
    }
    public Cursor transactionlogdata(String item1){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM transactionlog WHERE username = '" + item1 +"'", null);
        return cursor;

    }

    public Cursor getAllCreditCards(String item1){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT cardnumber, cardname, creditcard.accountnumber, buylimit, credit FROM creditcard INNER JOIN user ON user.username = currentaccount.username INNER JOIN currentaccount ON currentaccount.accountnumber = creditcard.accountnumber WHERE user.username = '" + item1 +"'" , null);
        return cursor;
    }

    public Cursor getAllDebitCards(String item1){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT cardnumber, cardname, debitcard.accountnumber, buylimit FROM debitcard INNER JOIN user ON user.username = currentaccount.username INNER JOIN currentaccount ON currentaccount.accountnumber = debitcard.accountnumber WHERE user.username = '" + item1 +"'" , null);
        return cursor;
    }
    public Cursor getAccountNumberInfo(String item1){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM currentaccount WHERE accountnumber = '" + item1 +"'" , null);
        return cursor;
    }
    public Cursor getSavingsNumberInfo(String item1){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM savingsaccount WHERE accountnumber = '" + item1 +"'" , null);
        return cursor;
    }
    //Last one deletes account and all the informtion that belongs to that account
    public void deleteAccount(String item1){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=on");
        db.execSQL("DELETE FROM user WHERE username = '"+item1 +"'");
    }


}