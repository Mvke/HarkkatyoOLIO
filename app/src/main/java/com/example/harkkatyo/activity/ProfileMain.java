package com.example.harkkatyo.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.harkkatyo.Account;
import com.example.harkkatyo.DatabaseHelper;
import com.example.harkkatyo.R;
import com.example.harkkatyo.activity.AccountMain;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.example.harkkatyo.activity.MainActivity;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.EditText;
import android.widget.Switch;

public class ProfileMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private EditText firstname;
    private EditText lastname;
    private EditText age;
    private EditText password;
    private EditText passwordcheck;
    private Account acc;
    private DatabaseHelper databaseHelper;
    private Switch aSwitch;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        acc = (Account) getIntent().getSerializableExtra("user");
        firstname = findViewById(R.id.editText9);
        lastname = findViewById(R.id.editText10);
        age = findViewById(R.id.editText11);
        password = findViewById(R.id.editText12);
        passwordcheck = findViewById(R.id.editText13);
        databaseHelper = new DatabaseHelper(this);
        aSwitch = findViewById(R.id.switch6);
        setText();
    }
    //This method sets texts for the texts boxes
    public void setText(){
        firstname.setText(acc.getFirstname());
        lastname.setText(acc.getLastname());
        age.setText(acc.getAge());
        password.setText(acc.getPassword());
    }
    //This method deletes account and all the information linked to that account.
    public void buttonDelete(View v) {
        if(aSwitch.isChecked()){
            databaseHelper.deleteAccount(acc.getUsername());
            Intent intent = new Intent(this, LoginScreen.class);
            startActivity(intent);
        }
        else{

        }

    }
    //This button changes the user information
    public void buttonConfrim(View v){
        String fname = firstname.getText().toString();
        String lname = lastname.getText().toString();
        String ag = age.getText().toString();
        String pword = password.getText().toString();
        String pwordcheck = passwordcheck.getText().toString();

        if(pword.isEmpty()){
            password.setError("Enter password");
        }
        else if(!pword.matches(pwordcheck)){
            passwordcheck.setError("Passwords doesn't match");
        }
        else if(fname.isEmpty()){
            firstname.setError("Enter name");

        }
        else if(lname.isEmpty()){
            lastname.setError("Enter lastname");

        }
        else if(ag.isEmpty()){
            age.setError("Enter age");

        }
        else if(Integer.parseInt(ag)<18){
            age.setError("Your age cannot be under 18");

        }

        else{
            databaseHelper.updateUser("1", acc.getUsername() ,pwordcheck, fname, lname, ag);
            acc= new Account(1, acc.getUsername(),pwordcheck, fname, lname, ag);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("user", acc);
            startActivity(intent);
        }

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
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("user", acc);
            startActivity(intent);
        } else if (id == R.id.information) {


        } else if (id == R.id.account) {
            Intent intent = new Intent(this, AccountMain.class);
            intent.putExtra("user", acc);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
