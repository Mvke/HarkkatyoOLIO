package com.example.harkkatyo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.harkkatyo.Account;
import com.example.harkkatyo.BankAccount;
import com.example.harkkatyo.BankAccountSavings;
import com.example.harkkatyo.DatabaseHelper;
import com.example.harkkatyo.R;
import com.example.harkkatyo.XML;

import java.util.ArrayList;

public class TransferActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private EditText ammount;
    private int idnumFrom;
    private int idnumTo;
    Context context = null;
    DatabaseHelper databaseHelper;
    ArrayAdapter<BankAccount> adapterFromCurrent;
    ArrayAdapter<BankAccount> adapterToCurrent;
    ArrayAdapter<BankAccountSavings> adapterFromSavings;
    ArrayAdapter<BankAccountSavings> adapterToSavings;
    Account account;
    ArrayList<BankAccount> arrayListCurrent;
    ArrayList<BankAccountSavings> arrayListSavings;
    private Switch aSwitch1;
    private Switch aSwitch2;
    private XML xml;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        fromSpinner = findViewById(R.id.spinner8);
        toSpinner = findViewById(R.id.spinner9);
        ammount = findViewById(R.id.editText30);
        Account acc = (Account) getIntent().getSerializableExtra("user");
        context = TransferActivity.this;
        fromSpinner.setOnItemSelectedListener(this);
        toSpinner.setOnItemSelectedListener(this);
        account = new Account(acc.getBankid(), acc.getUsername(), acc.getPassword(), acc.getFirstname(), acc.getLastname(), acc.getAge());
        databaseHelper = new DatabaseHelper(this);
        arrayListCurrent = new ArrayList<>();
        arrayListSavings = new ArrayList<>();
        aSwitch1 = findViewById(R.id.switch2);
        aSwitch2 = findViewById(R.id.switch4);
        textView = findViewById(R.id.textView29);
        xml = new XML();
        spinnerFirst();
        check();

    }
    //This method checks if the switch is checked and changes the information to the spinner.
    public void check() {
        aSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    arrayListSavings.clear();
                    Cursor cursor2 = databaseHelper.savingaccountdata(account.getUsername());
                    while (cursor2.moveToNext()) {
                        BankAccountSavings bankAccountSavings = new BankAccountSavings(cursor2.getString(2), cursor2.getFloat(3), cursor2.getString(1));
                        arrayListSavings.add(bankAccountSavings);
                    }
                    adapterFromSavings = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayListSavings);
                    adapterFromSavings.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    fromSpinner.setAdapter(adapterFromSavings);

                } else {
                    arrayListCurrent.clear();
                    Cursor cursor1 = databaseHelper.currentaccountData(account.getUsername());
                    while (cursor1.moveToNext()) {
                        BankAccount bankAccount = new BankAccount(cursor1.getString(2), cursor1.getFloat(3), cursor1.getInt(4), cursor1.getString(1));
                        arrayListCurrent.add(bankAccount);
                    }

                    adapterFromCurrent = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayListCurrent);
                    adapterFromCurrent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    fromSpinner.setAdapter(adapterFromCurrent);

                }
            }

        });
        aSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    arrayListSavings.clear();
                    Cursor cursor2 = databaseHelper.savingaccountdata(account.getUsername());
                    while (cursor2.moveToNext()) {
                        BankAccountSavings bankAccountSavings = new BankAccountSavings(cursor2.getString(2), cursor2.getFloat(3), cursor2.getString(1));
                        arrayListSavings.add(bankAccountSavings);
                    }
                    adapterToSavings = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayListSavings);
                    adapterToSavings.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    toSpinner.setAdapter(adapterToSavings);

                } else {
                    arrayListCurrent.clear();
                    Cursor cursor1 = databaseHelper.currentaccountData(account.getUsername());
                    while (cursor1.moveToNext()) {
                        BankAccount bankAccount = new BankAccount(cursor1.getString(2), cursor1.getFloat(3), cursor1.getInt(4), cursor1.getString(1));
                        arrayListCurrent.add(bankAccount);
                    }

                    adapterToCurrent = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayListCurrent);
                    adapterToCurrent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    toSpinner.setAdapter(adapterToCurrent);

                }
            }

        });

    }

    //This method gets information from the database and adds bankaccounts to spinner.
    public void spinnerFirst() {
        Cursor cursor1 = databaseHelper.currentaccountData(account.getUsername());
        while (cursor1.moveToNext()) {
            BankAccount bankAccount = new BankAccount(cursor1.getString(2), cursor1.getFloat(3), cursor1.getInt(4), cursor1.getString(1));
            arrayListCurrent.add(bankAccount);
        }

        adapterFromCurrent = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayListCurrent);
        adapterFromCurrent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapterFromCurrent);

        adapterToCurrent = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayListCurrent);
        adapterToCurrent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(adapterToCurrent);


    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        //This switch case checks position in each spinner.
        switch (arg0.getId()) {
            case R.id.spinner8:
                idnumFrom = position;

                break;
            case R.id.spinner9:
                idnumTo = position;
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }
    //First this method checks which switches are checked and transfers money between two accounts.
    public void buttonDone(View v) {

        if(aSwitch1.isChecked() && aSwitch2.isChecked()){
            if(arrayListSavings.isEmpty()){
                textView.setText("You are missing account");
            }
            else if(ammount.getText().toString().isEmpty()){
                textView.setText("Set ammount");
            }
            else{
                String ammounntstr = ammount.getText().toString();
                int ammountint = Integer.parseInt(ammounntstr);
                BankAccountSavings bankAccountSavingsFrom = arrayListSavings.get(idnumFrom);
                BankAccountSavings bankAccountSavingsTo = arrayListSavings.get(idnumTo);
                if (bankAccountSavingsFrom.getAmmount()< ammountint){
                    ammount.setError("You don't have this much money on your account");
                }
                else if(bankAccountSavingsFrom.getAccountnumber().matches(bankAccountSavingsTo.getAccountnumber())){
                    textView.setText("You can't transfer money from account to same account");

                }
                else{
                    float ammounttotalfor = bankAccountSavingsFrom.getAmmount() - ammountint;
                    float ammounttotalto = bankAccountSavingsTo.getAmmount() + ammountint;
                    databaseHelper.updateSavingsAccount(account.getUsername(), bankAccountSavingsFrom.getAccountname(),ammounttotalfor,bankAccountSavingsFrom.getAccountnumber());
                    databaseHelper.updateSavingsAccount(account.getUsername(), bankAccountSavingsTo.getAccountname(),ammounttotalto,bankAccountSavingsTo.getAccountnumber());
                    BankAccountSavings bankAccountf = new BankAccountSavings(bankAccountSavingsFrom.getAccountname(),ammounttotalfor,bankAccountSavingsFrom.getAccountnumber());
                    BankAccountSavings  bankAccountt = new BankAccountSavings(bankAccountSavingsTo.getAccountname(),ammounttotalto,bankAccountSavingsTo.getAccountnumber());
                    arrayListSavings.set(idnumTo,bankAccountt);
                    arrayListSavings.set(idnumFrom, bankAccountf);
                    adapterFromSavings.notifyDataSetChanged();
                    adapterToSavings.notifyDataSetChanged();
                    databaseHelper.addDataToTransactionLog(bankAccountf.getAccountnumber(), bankAccountt.getAccountnumber(), ammounntstr,account.getUsername());
                    textView.setText(" ");

                }
            }


        }
        else if(!aSwitch1.isChecked() && aSwitch2.isChecked()){
            if(arrayListSavings.isEmpty() || arrayListCurrent.isEmpty()){
                textView.setText("You are missing account");
            }
            else if(ammount.getText().toString().isEmpty()){
                textView.setText("Set ammount");
            }
            else{
                BankAccount bankAccountFrom = arrayListCurrent.get(idnumFrom);
                BankAccountSavings bankAccountSavingsTo = arrayListSavings.get(idnumTo);
                String ammounntstr = ammount.getText().toString();
                int ammountint = Integer.parseInt(ammounntstr);
                if (bankAccountFrom.getAmmount()< ammountint){
                    ammount.setError("You don't have this much money on your account");
                }
                else if(bankAccountFrom.getAccountnumber().matches(bankAccountSavingsTo.getAccountnumber())){
                    textView.setText("You can't transfer money from account to same account");

                }
                else{
                    float ammounttotalfor = bankAccountFrom.getAmmount() - ammountint;
                    float ammounttotalto = bankAccountSavingsTo.getAmmount() + ammountint;
                    databaseHelper.updateCurrentAccount(account.getUsername(), bankAccountFrom.getAccountname(),ammounttotalfor, bankAccountFrom.getPaylimit(), bankAccountFrom.getAccountnumber());
                    databaseHelper.updateSavingsAccount(account.getUsername(), bankAccountSavingsTo.getAccountname(),ammounttotalto,bankAccountSavingsTo.getAccountnumber());
                    BankAccount bankAccountf = new BankAccount(bankAccountFrom.getAccountname(),ammounttotalfor,bankAccountFrom.getPaylimit(),bankAccountFrom.getAccountnumber());
                    BankAccountSavings  bankAccountt = new BankAccountSavings(bankAccountSavingsTo.getAccountname(),ammounttotalto,bankAccountSavingsTo.getAccountnumber());
                    arrayListCurrent.set(idnumFrom, bankAccountf);
                    arrayListSavings.set(idnumTo,bankAccountt);
                    adapterFromCurrent.notifyDataSetChanged();
                    adapterToSavings.notifyDataSetChanged();
                    databaseHelper.addDataToTransactionLog(bankAccountf.getAccountnumber(), bankAccountt.getAccountnumber(), ammounntstr,account.getUsername());
                    textView.setText(" ");
                }
            }


        }

        else if(aSwitch1.isChecked() && !aSwitch2.isChecked()){
            if(arrayListSavings.isEmpty() || arrayListCurrent.isEmpty()){
                textView.setText("You are missing account");
            }
            else if(ammount.getText().toString().isEmpty()){
                textView.setText("Set ammount");
            }
            else{
                BankAccountSavings bankAccountSavingsFrom = arrayListSavings.get(idnumFrom);
                BankAccount bankAccountTo = arrayListCurrent.get(idnumTo);
                String ammounntstr = ammount.getText().toString();
                int ammountint = Integer.parseInt(ammounntstr);
                if (bankAccountSavingsFrom.getAmmount()< ammountint){
                    ammount.setError("You don't have this much money on your account");
                }
                else if(bankAccountSavingsFrom.getAccountnumber().matches(bankAccountTo.getAccountnumber())){
                    textView.setText("You can't transfer money from account to same account");

                }
                else{
                    float ammounttotalfor = bankAccountSavingsFrom.getAmmount() - ammountint;
                    float ammounttotalto = bankAccountTo.getAmmount() + ammountint;
                    databaseHelper.updateSavingsAccount(account.getUsername(), bankAccountSavingsFrom.getAccountname(),ammounttotalfor,bankAccountSavingsFrom.getAccountnumber());
                    databaseHelper.updateCurrentAccount(account.getUsername(), bankAccountTo.getAccountname(),ammounttotalto,bankAccountTo.getPaylimit(), bankAccountTo.getAccountnumber());
                    BankAccountSavings bankAccountf = new BankAccountSavings(bankAccountSavingsFrom.getAccountname(),ammounttotalfor,bankAccountSavingsFrom.getAccountnumber());
                    BankAccount bankAccountt = new BankAccount(bankAccountTo.getAccountname(),ammounttotalto,bankAccountTo.getPaylimit(),bankAccountTo.getAccountnumber());
                    arrayListSavings.set(idnumFrom, bankAccountf);
                    arrayListCurrent.set(idnumTo,bankAccountt);
                    adapterFromSavings.notifyDataSetChanged();
                    adapterToCurrent.notifyDataSetChanged();
                    databaseHelper.addDataToTransactionLog(bankAccountf.getAccountnumber(), bankAccountt.getAccountnumber(), ammounntstr,account.getUsername());
                    textView.setText(" ");
                }
            }



        }
        else{
            if(arrayListCurrent.isEmpty()){
                textView.setText("You are missing account");
            }
            else if(ammount.getText().toString().isEmpty()){
                textView.setText("Set ammount");
            }
            else{
                BankAccount bankAccountFrom = arrayListCurrent.get(idnumFrom);
                BankAccount bankAccountTo = arrayListCurrent.get(idnumTo);
                String ammounntstr = ammount.getText().toString();
                int ammountint = Integer.parseInt(ammounntstr);
                if (bankAccountFrom.getAmmount()< ammountint){
                    ammount.setError("You don't have this much money on your account");
                }
                else if(bankAccountFrom.getAccountnumber().matches(bankAccountTo.getAccountnumber())){
                    textView.setText("You can't transfer money from account to same account");

                }
                else{
                    float ammounttotalfor = bankAccountFrom.getAmmount() - ammountint;
                    float ammounttotalto = bankAccountTo.getAmmount() + ammountint;
                    databaseHelper.updateCurrentAccount(account.getUsername(), bankAccountFrom.getAccountname(),ammounttotalfor, bankAccountFrom.getPaylimit(), bankAccountFrom.getAccountnumber());
                    databaseHelper.updateCurrentAccount(account.getUsername(), bankAccountTo.getAccountname(),ammounttotalto,bankAccountTo.getPaylimit(), bankAccountTo.getAccountnumber());
                    BankAccount bankAccountf = new BankAccount(bankAccountFrom.getAccountname(),ammounttotalfor,bankAccountFrom.getPaylimit(),bankAccountFrom.getAccountnumber());
                    BankAccount bankAccountt = new BankAccount(bankAccountTo.getAccountname(),ammounttotalto,bankAccountTo.getPaylimit(),bankAccountTo.getAccountnumber());
                    arrayListCurrent.set(idnumFrom, bankAccountf);
                    arrayListCurrent.set(idnumTo,bankAccountt);
                    adapterFromCurrent.notifyDataSetChanged();
                    adapterToCurrent.notifyDataSetChanged();
                    databaseHelper.addDataToTransactionLog(bankAccountf.getAccountnumber(), bankAccountt.getAccountnumber(), ammounntstr,account.getUsername());
                    textView.setText(" ");

                }
            }

        }

    }
}
