package com.example.harkkatyo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.example.harkkatyo.Account;
import com.example.harkkatyo.DatabaseHelper;
import com.example.harkkatyo.R;

import java.util.Random;

public class CardAddActivity extends AppCompatActivity {
    private EditText cardname;
    private EditText buylimit;
    private Switch aSwitch;
    Account account;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_add);
        cardname = findViewById(R.id.editText21);
        buylimit = findViewById(R.id.editText22);
        Account acc = (Account) getIntent().getSerializableExtra("user");
        account = new Account(acc.getBankid(), acc.getUsername(), acc.getPassword(), acc.getFirstname(), acc.getLastname(), acc.getAge());
        aSwitch = findViewById(R.id.switch1);
        databaseHelper = new DatabaseHelper(this);
    }

    public void buttonCreate(View v){
        String card_name = cardname.getText().toString();
        String limit = buylimit.getText().toString();
        int limit1 = Integer.getInteger(limit);
        Random random = new Random();
        int cardnum = random.nextInt(100000);

    }
}
