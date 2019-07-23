package com.example.harkkatyo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.harkkatyo.Account;
import com.example.harkkatyo.Bank;
import com.example.harkkatyo.BankAccount;
import com.example.harkkatyo.DatabaseHelper;
import com.example.harkkatyo.R;

import java.util.ArrayList;

public class AccountManagementActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayList <BankAccount> arrayList = new ArrayList<>();
    private Spinner spinner;
    private EditText accountname;
    private EditText limit;
    private int idnum;
    Context context = null;
    Account account;
    DatabaseHelper databaseHelper;
    ArrayAdapter<BankAccount> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);
        accountname = findViewById(R.id.editText14);
        limit = findViewById(R.id.editText17);
        spinner = findViewById(R.id.spinner2);
        context = AccountManagementActivity.this;
        spinner.setOnItemSelectedListener(this);
        Account acc = (Account) getIntent().getSerializableExtra("user");
        account = new Account(acc.getBankid(), acc.getUsername(), acc.getPassword(), acc.getFirstname(), acc.getLastname(), acc.getAge());
        databaseHelper = new DatabaseHelper(this);
        spinner();

    }






    public void spinner(){
        Cursor cursor1 = databaseHelper.currentaccountData(account.getUsername());
        Cursor cursor2 = databaseHelper.savingaccountdata(account.getUsername());
        while(cursor1.moveToNext()){
            System.out.println(cursor1.getString(2) +  cursor1.getFloat(3));
            BankAccount bankAccount = new BankAccount(cursor1.getString(2), cursor1.getFloat(3));
            arrayList.add(bankAccount);
        }
        while(cursor2.moveToNext()){
            BankAccount bankAccount = new BankAccount(cursor2.getString(2), cursor2.getFloat(3));
            arrayList.add(bankAccount);
        }

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


    public void buttonUpdate(View v){

        adapter.notifyDataSetChanged();
    }


}







