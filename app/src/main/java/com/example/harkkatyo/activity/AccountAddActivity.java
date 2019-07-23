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

public class AccountAddActivity extends AppCompatActivity {
    private EditText accountname;
    private EditText ammount;
    private EditText limit;
    private Switch aSwitch;
    Account account;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_add);
        Account acc = (Account) getIntent().getSerializableExtra("user");
        account = new Account(acc.getBankid(), acc.getUsername(), acc.getPassword(), acc.getFirstname(), acc.getLastname(), acc.getAge());
        aSwitch = findViewById(R.id.switch1);
        accountname = findViewById(R.id.editText16);
        ammount = findViewById(R.id.editText18);
        limit = findViewById(R.id.editText19);
        databaseHelper = new DatabaseHelper(this);
    }

    public void buttonCreate(View v){
        String name = accountname.getText().toString();
        String moneytext = ammount.getText().toString();
        float money = Float.parseFloat(moneytext);
        String paylimit = limit.getText().toString();
        Random random = new Random();
        int accountnum = random.nextInt(100000);
        if(aSwitch.isChecked()) {
            int paylimit1 = Integer.parseInt(paylimit);
            databaseHelper.addDataToCurrentAccount("qw", name, money, paylimit1, accountnum);
        }
        else {
            databaseHelper.addDataToSavingsAccount(account.getUsername(), name, money, accountnum);

        }
    }
}
