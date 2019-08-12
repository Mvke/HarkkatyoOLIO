package com.example.harkkatyo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.harkkatyo.Account;
import com.example.harkkatyo.Bank;
import com.example.harkkatyo.DatabaseHelper;
import com.example.harkkatyo.R;

import java.util.ArrayList;


public class Register extends AppCompatActivity {

    private EditText Firstname;
    private EditText Lastname;
    private EditText Age;
    private EditText Username;
    private EditText Password1;
    private EditText Password2;
    DatabaseHelper databaseHelper;
    ArrayList<Bank> arrayList;
    ArrayList<Account> arrayList1;
    private int check;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Firstname = findViewById(R.id.editText3);
        Lastname = findViewById(R.id.editText4);
        Age = findViewById(R.id.editText5);
        Username = findViewById(R.id.editText6);
        Password1 = findViewById(R.id.editText7);
        Password2 = findViewById(R.id.editText8);
        databaseHelper = new DatabaseHelper(this);
        arrayList = new ArrayList();
        arrayList1 = new ArrayList();
    }

    public void buttonCheckAndAdd(View v){
        String firstname = Firstname.getText().toString();
        String lastname = Lastname.getText().toString();
        String age = Age.getText().toString();
        String username = Username.getText().toString();
        String passwordcheck1 = Password1.getText().toString();
        String passwordcheck2 = Password2.getText().toString();
        Cursor cursor = databaseHelper.bankdata();
        Cursor cursor1 = databaseHelper.userdata();
        //Here I take bank information and I add it later to profile
        while(cursor.moveToNext()){
            Bank bank = new Bank(cursor.getString(0), cursor.getString(1));
            arrayList.add(bank);
        }
        Bank ba = arrayList.get(0);
        //This checks if the username is already taken.
        while(cursor1.moveToNext()){
            Account account = new Account(cursor1.getInt(0), cursor1.getString(1),cursor1.getString(2),cursor1.getString(3),cursor1.getString(4),cursor1.getString(5));
            if(account.getUsername().matches(username)){
                check = 1;
                break;
            }
            else{
                check = 0;
            }
        }
        if(check == 1){
            Username.setError("This username is already taken");
        }
        else if(passwordcheck1.isEmpty()){
            Password1.setError("Enter password");
        }

        else if(!passwordcheck1.equals(passwordcheck2)){
            Password2.setError("Your password isn't same as the first");
        }
        else if(firstname.isEmpty()){
            Firstname.setError("Enter name");

        }
        else if(lastname.isEmpty()){
            Lastname.setError("Enter lastname");

        }
        else if(age.isEmpty()){
            Age.setError("Enter age");

        }
        else if(Integer.parseInt(age)< 18){
            Age.setError("Your age cannot be under 18");
        }
        else if(username.isEmpty()){
            Username.setError("Enter username");
        }
        else{
            databaseHelper.addDataToUser(ba.getId(), username, passwordcheck2, firstname, lastname, age);
            super.onBackPressed();


        }
    }

}
