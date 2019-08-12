package com.example.harkkatyo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.harkkatyo.Account;
import com.example.harkkatyo.DatabaseHelper;
import com.example.harkkatyo.R;
import com.example.harkkatyo.TransactionInfo;
import com.example.harkkatyo.XML;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class TransactionInfoActivity extends AppCompatActivity {
    private ListView listView;
    Context context = null;
    DatabaseHelper databaseHelper;
    Account account;
    ArrayList<TransactionInfo> arrayList;
    XML xml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_info);
        listView = findViewById(R.id.listView);
        Account acc = (Account) getIntent().getSerializableExtra("user");
        context = TransactionInfoActivity.this;
        account = new Account(acc.getBankid(), acc.getUsername(), acc.getPassword(), acc.getFirstname(), acc.getLastname(), acc.getAge());
        databaseHelper = new DatabaseHelper(this);
        arrayList = new ArrayList();
        addListview();
        createXml();
    }
    //This method just takes transaction information from the database and adds it to listviw.
    public void addListview(){
        Cursor cursor = databaseHelper.transactionlogdata(account.getUsername());
        while (cursor.moveToNext()){
            TransactionInfo transactionInfo = new TransactionInfo(cursor.getString(0),cursor.getString(1),cursor.getString(2));
            arrayList.add(transactionInfo);
        }
        ArrayAdapter<TransactionInfo> adapter1 =
                new ArrayAdapter(this,android.R.layout.simple_list_item_activated_1, arrayList);
        listView.setAdapter(adapter1);

    }
    //This creates the xml file
    public void createXml(){
        try {
            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput("Xmlfile.xml", Context.MODE_PRIVATE));
            writer.write(XML.CreateXMLString(arrayList));
            writer.close();
            System.out.println("XML file written.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("File is here" + context.getFilesDir());
    }
}
