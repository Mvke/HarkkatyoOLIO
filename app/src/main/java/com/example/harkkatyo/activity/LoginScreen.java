package com.example.harkkatyo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.harkkatyo.Account;
import com.example.harkkatyo.DatabaseHelper;
import com.example.harkkatyo.R;

public class LoginScreen extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Text;
    DatabaseHelper DBHelper;
    Account account;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Name = findViewById(R.id.editText2);
        Password = findViewById(R.id.editText);
        Text = findViewById(R.id.textView2);

        DBHelper = new DatabaseHelper(this);
        DBHelper.getReadableDatabase();



    }
    public void buttonLogin(View v){
        String username = Name.getText().toString();
        String password = Password.getText().toString();
        validate(username, password);

    }
    private void validate(String userName, String userPassword){
            Cursor cursor = DBHelper.userdata();
            while(cursor.moveToNext()){
                if(cursor.getString(1).matches(userName) && cursor.getString(2).matches(userPassword)){
                    account = new Account(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("user", account);
                    startActivity(intent);
                    Text.setText("");
                    Name.setText("");
                    Password.setText("");


                }
            }


    }
    public void buttonRegister(View v){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public Account getAccount(){
        return account;
    }

}
