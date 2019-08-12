package com.example.harkkatyo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    //When you press the button this happens. First it checks if any input boxes were empty.
    public void buttonCreate(View v){
        if(accountname.getText().toString().isEmpty()){
            accountname.setError("Please write account name here");
        }
        else if(ammount.getText().toString().isEmpty()){
            ammount.setError("Add ammount");
        }
        else if(limit.getText().toString().isEmpty()&& aSwitch.isChecked()){
            limit.setError("Set buy limit to your account");
        }
        else{
            String name = accountname.getText().toString();
            String moneytext = ammount.getText().toString();
            float money = Float.parseFloat(moneytext);
            String paylimit = limit.getText().toString();
            //Here I generate random number for accountnumber
            Random random = new Random();
            int accountnum = random.nextInt(100000);
            String accnumstring = ""+ accountnum;
            //This checks if the switch is checked
            if(aSwitch.isChecked()) {
                int paylimit1 = Integer.parseInt(paylimit);
                //Adds information to currentaccount
                databaseHelper.addDataToCurrentAccount(account.getUsername(), name, money, paylimit1, accnumstring);
            }
            else {
                databaseHelper.addDataToSavingsAccount(account.getUsername(), name, money, accnumstring);

            }
            //Takes user back to previous activity.
            super.onBackPressed();
        }

    }
}
