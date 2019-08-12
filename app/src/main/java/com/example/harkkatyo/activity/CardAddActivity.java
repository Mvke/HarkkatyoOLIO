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
import android.widget.Switch;

import com.example.harkkatyo.Account;
import com.example.harkkatyo.BankAccount;
import com.example.harkkatyo.DatabaseHelper;
import com.example.harkkatyo.R;

import java.util.ArrayList;
import java.util.Random;

public class CardAddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private EditText cardname;
    private EditText buylimit;
    private Switch aSwitch;
    Account account;
    DatabaseHelper databaseHelper;
    ArrayAdapter<BankAccount> adapter;
    ArrayList<BankAccount> arrayList;
    private Spinner spinner;
    private int idnum;
    Context context = null;
    private EditText credit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_add);
        credit = findViewById(R.id.editText25);
        spinner = findViewById(R.id.spinner);
        cardname = findViewById(R.id.editText21);
        buylimit = findViewById(R.id.editText32);
        Account acc = (Account) getIntent().getSerializableExtra("user");
        context = CardAddActivity.this;
        spinner.setOnItemSelectedListener(this);
        account = new Account(acc.getBankid(), acc.getUsername(), acc.getPassword(), acc.getFirstname(), acc.getLastname(), acc.getAge());
        aSwitch = findViewById(R.id.switch3);
        databaseHelper = new DatabaseHelper(this);
        arrayList = new ArrayList<>();
        spinneri();
    }
    //This method gets information from the database and adds bankaccounts to spinner.
    public void spinneri(){
        Cursor cursor1 = databaseHelper.currentaccountData(account.getUsername());
        while(cursor1.moveToNext()){
            System.out.println(cursor1.getInt(4));
            BankAccount bankAccount = new BankAccount(cursor1.getString(2), cursor1.getFloat(3), cursor1.getInt(4), cursor1.getString(1));
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
    //This method works when button is pressed. It takes the card information to database. There is also a switch check.
    public void buttonCreate(View v){
        BankAccount bankAccount = arrayList.get(idnum);
        String card_name = cardname.getText().toString();
        String limit = buylimit.getText().toString();
        int limit1 = Integer.parseInt(limit);
        String creditstr = credit.getText().toString();
        int creditint = Integer.parseInt(creditstr);
        Random random = new Random();
        int cardnum = random.nextInt(100000);
        String cardnumString = "" + cardnum;
        if(aSwitch.isChecked()) {
            System.out.println(cardnumString+ "  " +bankAccount.getAccountnumber()+ "  " +limit1+ "  " +creditint+ "  " +card_name);
            databaseHelper.addDataToCreditCard(cardnumString, bankAccount.getAccountnumber(),limit1,creditint,card_name);
            super.onBackPressed();
        }
        else {
            databaseHelper.addDataToDebitCard(cardnumString, bankAccount.getAccountnumber(),limit1,card_name);
            super.onBackPressed();

        }

    }
}
