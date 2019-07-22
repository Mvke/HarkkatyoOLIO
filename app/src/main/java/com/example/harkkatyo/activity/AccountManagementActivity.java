package com.example.harkkatyo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.harkkatyo.Account;
import com.example.harkkatyo.BankAccount;
import com.example.harkkatyo.DatabaseHelper;
import com.example.harkkatyo.R;

import java.util.ArrayList;

public class AccountManagementActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayList <BankAccount> arrayList = new ArrayList<>();
    private Spinner spinner;
    private EditText accountname;
    private EditText ammount;
    private EditText limit;
    private int idnum;
    Context context = null;
    private Switch aSwitch;
    Account account;
    DatabaseHelper databaseHelper;
    ArrayAdapter<BankAccount> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);
        accountname = findViewById(R.id.editText14);
        ammount = findViewById(R.id.editText16);
        limit = findViewById(R.id.editText17);
        spinner = findViewById(R.id.spinner2);
        context = AccountManagementActivity.this;
        spinner.setOnItemSelectedListener(this);
        aSwitch = findViewById(R.id.switch1);
        Account acc = (Account) getIntent().getSerializableExtra("user");
        account = new Account(acc.getBankid(), acc.getUsername(), acc.getPassword(), acc.getFirstname(), acc.getLastname(), acc.getAge());
        databaseHelper = new DatabaseHelper(this);
        spinner();

    }






    public void spinner(){
        adapter = new ArrayAdapter<>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, arrayList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        idnum = position;

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {


    }

    public void buttonCreate(View v){

        String name = accountname.getText().toString();
        String moneytext = ammount.getText().toString();
        float money = Float.parseFloat(moneytext);
        String paylimit = limit.getText().toString();
        int paylimit1 = Integer.parseInt(paylimit);
        if(aSwitch.isChecked()) {
            databaseHelper.addDataToCurrentAccount(account.getUsername(), name, money, paylimit1);
            BankAccount bank = new BankAccount(name, moneytext);
            arrayList.add(bank);
            adapter.notifyDataSetChanged();

        }
        else {

        }
    }

    public void buttonUpdate(View v){

    }


}







