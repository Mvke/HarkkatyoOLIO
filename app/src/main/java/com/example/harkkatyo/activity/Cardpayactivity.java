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
import com.example.harkkatyo.CreditCard;
import com.example.harkkatyo.DatabaseHelper;
import com.example.harkkatyo.DebitCard;
import com.example.harkkatyo.R;

import java.util.ArrayList;

public class Cardpayactivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private EditText editText;
    private Switch aSwitch;
    private int idnum;
    Context context = null;
    DatabaseHelper databaseHelper;
    ArrayList<DebitCard> arrayListDebit;
    ArrayList<CreditCard> arrayListCredit;
    ArrayList<BankAccount> arrayListCurrrent;
    ArrayAdapter<DebitCard> adapterDebit;
    ArrayAdapter<CreditCard> adapterCredit;
    Account account;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardpayactivity);
        spinner = findViewById(R.id.spinner10);
        editText = findViewById(R.id.editText31);
        aSwitch = findViewById(R.id.switch5);
        arrayListCredit = new ArrayList();
        arrayListDebit = new ArrayList();
        arrayListCurrrent = new ArrayList();
        Account acc = (Account) getIntent().getSerializableExtra("user");
        context = Cardpayactivity.this;
        spinner.setOnItemSelectedListener(this);
        account = new Account(acc.getBankid(), acc.getUsername(), acc.getPassword(), acc.getFirstname(), acc.getLastname(), acc.getAge());
        databaseHelper = new DatabaseHelper(this);
        textView = findViewById(R.id.textView);
        spinnerFirst();
        check();
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        idnum = position;


    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }
    //This method gets information from the database and adds debit cards to spinner.
    public void spinnerFirst(){
        arrayListDebit.clear();
        Cursor cursor1 = databaseHelper.getAllDebitCards(account.getUsername());
        while (cursor1.moveToNext()) {
            DebitCard debitCard = new DebitCard(cursor1.getString(1), cursor1.getString(0), cursor1.getString(2), cursor1.getInt(3));
            arrayListDebit.add(debitCard);
        }

        adapterDebit = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayListDebit);
        adapterDebit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterDebit);

    }
    //This method checks if the switch is checked and changes the information to the spinner.
    public void check() {
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    arrayListCredit.clear();
                    Cursor cursor2 = databaseHelper.getAllCreditCards(account.getUsername());
                    while (cursor2.moveToNext()) {
                        CreditCard creditCard = new CreditCard(cursor2.getString(1), cursor2.getString(0), cursor2.getString(2), cursor2.getInt(3), cursor2.getInt(4));
                        arrayListCredit.add(creditCard);
                    }
                    adapterCredit = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayListCredit);
                    adapterCredit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapterCredit);

                } else {
                    arrayListDebit.clear();
                    Cursor cursor1 = databaseHelper.getAllDebitCards(account.getUsername());
                    while (cursor1.moveToNext()) {
                        DebitCard debitCard = new DebitCard(cursor1.getString(1), cursor1.getString(0), cursor1.getString(2), cursor1.getInt(3));
                        arrayListDebit.add(debitCard);
                    }

                    adapterDebit = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayListDebit);
                    adapterDebit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapterDebit);

                }
            }

        });
    }
    //This method activates when button is pressed. It checks which card you are using and does the ammount meet the requirements
    public void buttonUpdate(View view){
        if(aSwitch.isChecked()){
            if(editText.getText().toString().isEmpty()){
                editText.setError("You didn't give an ammount!");
            }
            else if(arrayListCredit.isEmpty()){

            }
            else {
                CreditCard creditCard = arrayListCredit.get(idnum);
                Cursor cursor = databaseHelper.getAccountNumberInfo(creditCard.getAccountnumber());
                while(cursor.moveToNext()){
                    System.out.println(cursor.getString(2));
                    BankAccount bankAccount = new BankAccount(cursor.getString(2), cursor.getFloat(3), cursor.getInt(4), cursor.getString(1));
                    arrayListCurrrent.add(bankAccount);
                }
                BankAccount ba = arrayListCurrrent.get(0);
                String ammountstr = editText.getText().toString();
                int ammount = Integer.parseInt(ammountstr);

                if(creditCard.getBuylimit() < ammount){

                }
                else if(ba.getAmmount() < ammount){
                    if(creditCard.getCredit() < ammount){
                        textView.setText("You don't have enough money and credit on your account");
                    }
                    else {
                        int totalcredit = creditCard.getCredit() - ammount;
                        databaseHelper.updateCreditCard(creditCard.getCardnumber(),creditCard.getAccountnumber(),creditCard.getBuylimit(),totalcredit,creditCard.getCardname());
                        CreditCard crca = new CreditCard(creditCard.getCardname(), creditCard.getCardnumber(), creditCard.getAccountnumber(), creditCard.getBuylimit(),totalcredit);
                        arrayListCredit.set(idnum, crca);
                        adapterCredit.notifyDataSetChanged();

                    }
                }
                else{
                    float totalammount = ba.getAmmount() - ammount;
                    databaseHelper.updateCurrentAccount(account.getUsername(), ba.getAccountname(),totalammount, ba.getPaylimit(), ba.getAccountnumber());
                    textView.setText("Payment was successful");

                }

            }


        }
        else{
            if(editText.getText().toString().isEmpty()){
                editText.setError("You didn't give an ammount!");
            }
            else if(arrayListDebit.isEmpty()){

            }
            else {
                DebitCard debitCard = arrayListDebit.get(idnum);
                Cursor cursor = databaseHelper.getAccountNumberInfo(debitCard.getAccountnumber());
                while(cursor.moveToNext()){
                    BankAccount bankAccount = new BankAccount(cursor.getString(2), cursor.getFloat(3), cursor.getInt(4), cursor.getString(1));
                    arrayListCurrrent.add(bankAccount);
                }
                BankAccount ba = arrayListCurrrent.get(0);
                String ammountstr = editText.getText().toString();
                float ammount = Float.parseFloat(ammountstr);


                if(debitCard.getBuylimit() < ammount){
                    textView.setText("Limit is lower than the ammount");
                }
                else if(ba.getAmmount() <= ammount){
                    textView.setText("You dont have enough money in your account");
                }
                else{
                    float totalammount = ba.getAmmount() - ammount;
                    databaseHelper.updateCurrentAccount(account.getUsername(), ba.getAccountname(),totalammount, ba.getPaylimit(), ba.getAccountnumber());
                    textView.setText("Payment was successful");

                }

            }
        }
    }
}
