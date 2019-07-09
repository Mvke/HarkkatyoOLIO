package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginScreen extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView Text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Name = findViewById(R.id.editText2);
        Password = findViewById(R.id.editText);
        Login = findViewById(R.id.button);
        Text = findViewById(R.id.textView2);
    }
    public void buttonLogin(View v){
        String username = Name.getText().toString();
        String password = Password.getText().toString();
        System.out.println(username);
        System.out.println(password);
        validate(username, password);

    }
    private void validate(String userName, String userPassword){
        if ((userName.matches("User")) && (userPassword.matches("1234"))){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Text.setText("");
            Name.setText("");
            Password.setText("");
        }
        else{
            Text.setText("Yritä uudelleen");

        }
    }
}
