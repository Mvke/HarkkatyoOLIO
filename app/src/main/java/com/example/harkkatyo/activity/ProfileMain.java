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

public class ProfileMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText firstname;
    EditText lastname;
    EditText age;
    EditText password;
    EditText passwordcheck;
    Account acc;
    DatabaseHelper databaseHelper;





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
        setText();
    }

    public void setText(){
        firstname.setText(acc.getFirstname());
        lastname.setText(acc.getLastname());
        age.setText(acc.getAge());
        password.setText(acc.getPassword());
    }

    public void buttonConfrim(View v){
        String fname = firstname.getText().toString();
        String lname = lastname.getText().toString();
        String ag = age.getText().toString();
        String pword = password.getText().toString();
        String pwordcheck = passwordcheck.getText().toString();

        if(!pword.matches(pwordcheck)){

        }
        else if(fname.isEmpty()){

        }
        else if(lname.isEmpty()){

        }
        else if(ag.isEmpty()){

        }
        else if(pword.isEmpty()){

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
