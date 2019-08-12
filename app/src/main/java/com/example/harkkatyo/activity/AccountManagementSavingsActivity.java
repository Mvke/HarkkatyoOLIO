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
import com.example.harkkatyo.BankAccount;
import com.example.harkkatyo.BankAccountSavings;
import com.example.harkkatyo.DatabaseHelper;
import com.example.harkkatyo.R;

import java.util.ArrayList;

public class AccountManagementSavingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<BankAccountSavings> arrayList;
    private Spinner spinner;
    private EditText accountname;
    private EditText ammount;
    private int idnum;
    Context context = null;
    Account account;
    DatabaseHelper databaseHelper;
    ArrayAdapter<BankAccountSavings> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management_savings);
        arrayList = new ArrayList<>();
        accountname = findViewById(R.id.editText24);
        ammount = findViewById(R.id.editText17);
        spinner = findViewById(R.id.spinner4);
        context = AccountManagementSavingsActivity.this;
        spinner.setOnItemSelectedListener(this);
        Account acc = (Account) getIntent().getSerializableExtra("user");
        account = new Account(acc.getBankid(), acc.getUsername(), acc.getPassword(), acc.getFirstname(), acc.getLastname(), acc.getAge());
        databaseHelper = new DatabaseHelper(this);
        spinneri();
        texts();
    }
    //This method gets information from the database and adds bankaccounts to spinner.
    public void spinneri(){
        Cursor cursor2 = databaseHelper.savingaccountdata(account.getUsername());
        while(cursor2.moveToNext()){
            BankAccountSavings bankAccount = new BankAccountSavings(cursor2.getString(2), cursor2.getFloat(3), cursor2.getString(1));
            arrayList.add(bankAccount);
            System.out.println(bankAccount.getAccountnumber());
        }

        adapter = new ArrayAdapter<>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, arrayList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    //This method sets texts for the texts boxes
    public void texts() {
        BankAccountSavings bA = arrayList.get(idnum);
        accountname.setText(bA.getAccountname());
        String ammountString = String.valueOf(bA.getAmmount());
        ammount.setText(ammountString);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        idnum = position;
        texts();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {


    }
    //This method works when buttons is pressed. It updates the information to database and to the spinner.
    public void buttonUpdate(View v) {
        if (accountname.getText().toString().isEmpty()) {
            accountname.setError("Insert name");
        } else if (ammount.getText().toString().isEmpty()) {
            ammount.setError("Insert ammount (Insert 0 if you dont want add money))");

        } else {
            BankAccountSavings bA = arrayList.get(idnum);
            String ammountstring = ammount.getText().toString();
            float ammountotal = bA.getAmmount() + Float.parseFloat(ammountstring);
            BankAccountSavings bankAccount = new BankAccountSavings(accountname.getText().toString(), ammountotal, bA.getAccountnumber());
            databaseHelper.updateSavingsAccount(account.getUsername(), accountname.getText().toString(), ammountotal, bankAccount.getAccountnumber());
            arrayList.set(idnum, bankAccount);
            adapter.notifyDataSetChanged();
            texts();
        }
    }
}
