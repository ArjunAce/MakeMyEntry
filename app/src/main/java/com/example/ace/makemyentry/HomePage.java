package com.example.ace.makemyentry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    Button addEntryButton = null;
    Button viewRecordsButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        addEntryButton = (Button) findViewById(R.id.addEntryButton);
        viewRecordsButton = (Button) findViewById(R.id.viewRecordsButton);
    }

    public void addEntryViewButton(View v) {
        startActivity(new Intent(HomePage.this, AddEntry.class));
    }

    public void viewRecordsViewButton(View v) {
        startActivity(new Intent(HomePage.this, ViewRecords.class));
    }

    public void checkoutViewButton(View v) {
        startActivity(new Intent(HomePage.this, Checkout.class));
    }
}
