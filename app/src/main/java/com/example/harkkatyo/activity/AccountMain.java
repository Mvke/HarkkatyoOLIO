package com.example.harkkatyo.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.harkkatyo.Account;
import com.example.harkkatyo.DatabaseHelper;
import com.example.harkkatyo.R;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class AccountMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Account account;
    DatabaseHelper databaseHelper;
    private TextView accounttext;
    private TextView cardtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        Account acc = (Account) getIntent().getSerializableExtra("user");
        account = new Account(acc.getBankid(), acc.getUsername(), acc.getPassword(), acc.getFirstname(), acc.getLastname(), acc.getAge());
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        databaseHelper = new DatabaseHelper(this);
        accounttext = findViewById(R.id.textView4);
        cardtext = findViewById(R.id.textView8);
    }
    //Commands if you press back button when navigation bar is open
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //This explains what happens when u press the text int the navigation bar
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("user", account);
            startActivity(intent);
        } else if (id == R.id.information) {
            Intent intent = new Intent(this, ProfileMain.class);
            intent.putExtra("user", account);
            startActivity(intent);

        } else if (id == R.id.account) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //All the methods below are button commands. It takes you to next activity if everything is ok.
    public void buttonAddAccount(View v){
        Intent intent = new Intent(this, AccountAddActivity.class);
        intent.putExtra("user", account);
        startActivity(intent);
        //These two commands set empties text boxes if user have done any errors
        accounttext.setText("");
        cardtext.setText("");

    }



    public void buttonEditCurrentAccount(View v){
        Cursor cursor1 = databaseHelper.currentaccountData(account.getUsername());
        if(cursor1.getCount() == 0){
            accounttext.setText("You don't have current account");
        }
        else{
            accounttext.setText("");
            cardtext.setText("");
            Intent intent = new Intent(this, AccountManagementActivity.class);
            intent.putExtra("user", account);
            startActivity(intent);
        }

    }

    public void buttonEditSavingsAccount(View v){
        Cursor cursor1 = databaseHelper.savingaccountdata(account.getUsername());
        if(cursor1.getCount() == 0){
            accounttext.setText("You don't have savings account");
        }
        else{
            accounttext.setText("");
            cardtext.setText("");
            Intent intent = new Intent(this, AccountManagementSavingsActivity.class);
            intent.putExtra("user", account);
            startActivity(intent);
        }


    }

    public void buttonAddCard(View v){
        Cursor cursor1 = databaseHelper.currentaccountData(account.getUsername());
        if(cursor1.getCount() == 0){
            cardtext.setText("You don't have current account");
        }
        else{
            accounttext.setText("");
            cardtext.setText("");
            Intent intent = new Intent(this, CardAddActivity.class);
            intent.putExtra("user", account);
            startActivity(intent);
        }

    }
    public void buttonEditCreditCard(View v){
        Cursor cursor1 = databaseHelper.getAllCreditCards(account.getUsername());
        if(cursor1.getCount() == 0){
            cardtext.setText("You don't have credit card");
        }
        else{
            accounttext.setText("");
            cardtext.setText("");
            Intent intent = new Intent(this, CardManagementActivity.class);
            intent.putExtra("user", account);
            startActivity(intent);
        }

    }
    public void buttonEditDebitCard(View v){
        Cursor cursor1 = databaseHelper.getAllDebitCards(account.getUsername());
        if(cursor1.getCount() == 0){
            cardtext.setText("You don't have debit card");
        }
        else{
            accounttext.setText("");
            cardtext.setText("");
            Intent intent = new Intent(this, CardManagementDebitActivity.class);
            intent.putExtra("user", account);
            startActivity(intent);
        }


    }
}
