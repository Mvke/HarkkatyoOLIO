package com.example.harkkatyo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.harkkatyo.DatabaseHelper;
import com.example.harkkatyo.R;


public class Register extends AppCompatActivity {

    private EditText Firstname;
    private EditText Lastname;
    private EditText Age;
    private EditText Username;
    private EditText Password1;
    private EditText Password2;
    DatabaseHelper databaseHelper;




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

    }

    public void buttonCheckAndAdd(View v){
        String firstname = Firstname.getText().toString();
        String lastname = Lastname.getText().toString();
        String age = Age.getText().toString();
        String username = Username.getText().toString();
        String passwordcheck1 = Password1.getText().toString();
        String passwordcheck2 = Password2.getText().toString();
        if(!passwordcheck1.equals(passwordcheck2)){

        }
        else if(firstname.isEmpty()){

        }
        else if(lastname.isEmpty()){

        }
        else if(age.isEmpty()){

        }
        else if(username.isEmpty()){

        }
        else{
            databaseHelper.addDataToUser("1", username, passwordcheck2, firstname, lastname, age);
            super.onBackPressed();


        }
    }

}
