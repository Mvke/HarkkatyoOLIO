package com.example.harkkatyo.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.harkkatyo.Account;
import com.example.harkkatyo.DatabaseHelper;
import com.example.harkkatyo.R;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Account account;
    DatabaseHelper databaseHelper;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Account acc = (Account) getIntent().getSerializableExtra("user");
        account = new Account(acc.getBankid(), acc.getUsername(), acc.getPassword(), acc.getFirstname(), acc.getLastname(), acc.getAge());
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        databaseHelper = new DatabaseHelper(this);
        textView = findViewById(R.id.textView3);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {

        } else if (id == R.id.information) {
            Intent intent = new Intent(this, ProfileMain.class);
            intent.putExtra("user", account);
            startActivity(intent);

        } else if (id == R.id.account) {
            Intent intent = new Intent(this, AccountMain.class);
            intent.putExtra("user", account);
            startActivity(intent);
        } 

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //Below is methods for buttons. They have a check if you have any accounts etc. and if everything is okay it takes you to next activity
    public void buttonTransfer(View view){
        Cursor cursor1 = databaseHelper.currentaccountData(account.getUsername());
        Cursor cursor2 = databaseHelper.savingaccountdata(account.getUsername());
        if(cursor1.getCount() + cursor2.getCount() < 2){
            textView.setText("You have less that two accounts");

        }
        else{
            Intent intent = new Intent(this, TransferActivity.class);
            intent.putExtra("user", account);
            startActivity(intent);
        }

    }

    public void buttonPayCard(View view){
        Cursor cursor1 = databaseHelper.getAllCreditCards(account.getUsername());
        Cursor cursor2 = databaseHelper.getAllDebitCards(account.getUsername());
        System.out.println(cursor1.getCount()+ cursor2.getCount());
        if(cursor1.getCount() + cursor2.getCount() < 1){
            textView.setText("You don't have any cards");

        }
        else{
            Intent intent = new Intent(this, Cardpayactivity.class);
            intent.putExtra("user", account);
            startActivity(intent);
        }
    }


    public void buttonTransferTo(View view){
        Cursor cursor1 = databaseHelper.currentaccountData(account.getUsername());
        if(cursor1.getCount() < 1){
            textView.setText("You have less that one current account!");

        }
        else{
            Intent intent = new Intent(this, TransferToElsewhereActivity.class);
            intent.putExtra("user", account);
            startActivity(intent);
        }

    }
    public void buttonInfo(View view){
        Cursor cursor1 = databaseHelper.transactionlogdata(account.getUsername());
        if(cursor1.getCount() < 1){
            textView.setText("You don't have any transactions");

        }
        else{
            Intent intent = new Intent(this, TransactionInfoActivity.class);
            intent.putExtra("user", account);
            startActivity(intent);
        }
    }

}
