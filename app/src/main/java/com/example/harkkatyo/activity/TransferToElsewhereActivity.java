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
import com.example.harkkatyo.XML;

import java.util.ArrayList;

public class TransferToElsewhereActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    private Spinner spinner;
    private EditText elseaccoountnumber;
    private EditText ammount;
    private int idnum;
    private XML xml;
    Context context = null;
    DatabaseHelper databaseHelper;
    ArrayAdapter<BankAccount> adapter;
    Account account;
    ArrayList<BankAccount> arrayList;
    ArrayList<BankAccount> arrayListCurrentTo;
    ArrayList<BankAccountSavings> arrayListSavingsTo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_elsewhere);
        spinner = findViewById(R.id.spinner11);
        elseaccoountnumber = findViewById(R.id.editText33);
        ammount = findViewById(R.id.editText34);
        Account acc = (Account) getIntent().getSerializableExtra("user");
        context = TransferToElsewhereActivity.this;
        spinner.setOnItemSelectedListener(this);
        account = new Account(acc.getBankid(), acc.getUsername(), acc.getPassword(), acc.getFirstname(), acc.getLastname(), acc.getAge());
        databaseHelper = new DatabaseHelper(this);
        arrayList = new ArrayList<>();
        arrayListCurrentTo = new ArrayList<>();
        arrayListSavingsTo = new ArrayList<>();
        xml = new XML();
        spinneri();


    }
    //This method gets information from the database and adds bankaccounts to spinner.
    public void spinneri() {
        Cursor cursor1 = databaseHelper.currentaccountData(account.getUsername());
        while (cursor1.moveToNext()) {
            BankAccount bankAccount = new BankAccount(cursor1.getString(2), cursor1.getFloat(3), cursor1.getInt(4), cursor1.getString(1));
            arrayList.add(bankAccount);
        }

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        idnum = position;


    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }

    public void buttonTransferMoney(View view){
        if(ammount.getText().toString().isEmpty() || elseaccoountnumber.getText().toString().isEmpty() ){
            if(ammount.getText().toString().isEmpty() ) {
                ammount.setError("Insert ammount");
            }
            else {
                elseaccoountnumber.setError("Insert accountnumber");

            }
        }
        else {
            BankAccount bankAccount = arrayList.get(idnum);
            String toAccount = elseaccoountnumber.getText().toString();
            Cursor cursor1 = databaseHelper.getAccountNumberInfo(toAccount);
            Cursor cursor2 = databaseHelper.getSavingsNumberInfo(toAccount);
            if(cursor1.getCount() == 1) {
                while(cursor1.moveToNext()){
                    BankAccount bA = new BankAccount(cursor1.getString(2), cursor1.getFloat(3), cursor1.getInt(4), cursor1.getString(1));
                    arrayListCurrentTo.add(bA);
                }
                BankAccount bankAccountTo = arrayListCurrentTo.get(0);
                String ammountstr = ammount.getText().toString();
                Float ammountfl = Float.parseFloat(ammountstr);
                if(bankAccount.getPaylimit() < ammountfl){
                    ammount.setError("Accounts paylimit isn't high enough");
                }
                else if(bankAccount.getAmmount() < ammountfl){
                    ammount.setError("You dont have enough money");
                }
                else{
                    float ammountplus =bankAccountTo.getAmmount() + ammountfl;
                    float ammountminus = bankAccount.getAmmount() - ammountfl;
                    databaseHelper.updateCurrentAccount(account.getUsername(), bankAccount.getAccountname(),ammountminus, bankAccount.getPaylimit(), bankAccount.getAccountnumber());
                    databaseHelper.updateCurrentAccount(account.getUsername(), bankAccountTo.getAccountname(),ammountplus, bankAccountTo.getPaylimit(), bankAccountTo.getAccountnumber());
                    databaseHelper.addDataToTransactionLog(bankAccount.getAccountnumber(), bankAccountTo.getAccountnumber(), ammountstr,account.getUsername());
                    super.onBackPressed();
                }

            }
            else if(cursor2.getCount() == 1) {
                while(cursor2.moveToNext()){
                    BankAccountSavings bA = new BankAccountSavings(cursor2.getString(2), cursor2.getFloat(3), cursor2.getString(1));
                    arrayListSavingsTo.add(bA);
                }
                BankAccountSavings bankAccountSavingsTo = arrayListSavingsTo.get(0);
                String ammountstr = ammount.getText().toString();
                Float ammountfl = Float.parseFloat(ammountstr);
                if(bankAccount.getPaylimit() < ammountfl){
                    ammount.setError("Accounts paylimit isn't high enough");
                }
                else if(bankAccount.getAmmount() < ammountfl){
                    ammount.setError("You dont have enough money");
                }
                else{
                    float ammountplus =bankAccountSavingsTo.getAmmount() + ammountfl;
                    float ammountminus = bankAccount.getAmmount() - ammountfl;
                    databaseHelper.updateCurrentAccount(account.getUsername(), bankAccount.getAccountname(),ammountminus, bankAccount.getPaylimit(), bankAccount.getAccountnumber());
                    databaseHelper.updateSavingsAccount(account.getUsername(), bankAccountSavingsTo.getAccountname(),ammountplus, bankAccountSavingsTo.getAccountnumber());
                    databaseHelper.addDataToTransactionLog(bankAccount.getAccountnumber(), bankAccountSavingsTo.getAccountnumber(), ammountstr, account.getUsername());
                    super.onBackPressed();
                }
            }
            else {
                elseaccoountnumber.setError("Account not found");
            }


        }


    }

}
