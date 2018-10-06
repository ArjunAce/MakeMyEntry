package com.example.ace.makemyentry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.text.DateFormat;

public class AddEntry extends AppCompatActivity {

    Button buttonAddEntry = null;
    Button buttonReset = null;

    Button buttonAdd5Rows = null;
    Button buttonDelete = null;

    TextView editTextSSO = null;
    TextView editTextName = null;
    TextView editTextAmount = null;
    TextView textViewDate = null;

//    TextView editTextAllData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        buttonAddEntry = (Button) findViewById(R.id.buttonAddEntry);
        buttonReset = (Button) findViewById(R.id.buttonReset);

        buttonAdd5Rows = (Button) findViewById(R.id.buttonAdd5Rows);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        editTextSSO = (TextView) findViewById(R.id.editTextSSO);
        editTextName = (TextView) findViewById(R.id.editTextName);
        editTextAmount = (TextView) findViewById(R.id.editTextAmount);
        textViewDate = (TextView) findViewById(R.id.textViewDate);
//        editTextAllData = (TextView) findViewById(R.id.editTextAllData);

        String currentDateTime = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.SHORT).format(new Date());
        currentDateTime = "Date: " + currentDateTime;
        textViewDate.setText(currentDateTime);
    }

    public void buttonAddEntryHandler(View v) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        int enteredSSO = Integer.parseInt(editTextSSO.getText().toString());
        String enteredName = editTextName.getText().toString();
        int enteredAmount = Integer.parseInt(editTextAmount.getText().toString());
        String currentDateTime = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.SHORT).format(new Date());

        Employee e = dbHandler.findHandler(enteredSSO);
        if(e == null) {
            Employee employee = new Employee(enteredSSO, enteredName, currentDateTime, enteredAmount);
            boolean addHandlerSuccess = dbHandler.addHandler(employee);

            if (addHandlerSuccess) {
                Toast toast = Toast.makeText(this, "Success: Entry Registered! Bill till date: " + enteredAmount, Toast.LENGTH_LONG);
                toast.show();
                buttonResetHandler(v);
            } else {
                Toast toast = Toast.makeText(this, "Failure: Entry Not Registered!", Toast.LENGTH_LONG);
                toast.show();
            }
        }
        else{
            int newBillAmount = e.getAmount() + enteredAmount;
            boolean updateHandlerSuccess = dbHandler.updateHandler(enteredSSO, newBillAmount, currentDateTime);
            if (updateHandlerSuccess) {
                Toast toast = Toast.makeText(this, "Success: Entry Updated! Bill till date: " + newBillAmount, Toast.LENGTH_LONG);
                toast.show();
                buttonResetHandler(v);
            } else {
                Toast toast = Toast.makeText(this, "Failure: Entry Not Updated!", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }


    public void buttonResetHandler(View v) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        editTextSSO.setText("");
        editTextName.setText("");
        editTextAmount.setText("");
    }

    public void buttonAdd5RowsHandler(View v){
        for(int i = 0; i < 5; i++) {
            int enteredSSO = (int) Math.floor((Math.random() * 10000)) % 200;
            enteredSSO += 10;
            int nameLength = (enteredSSO % 12);
            nameLength++;
            char enteredNameInCharArray[] = new char[nameLength];
            for (int j = 0; j < nameLength; j++) {
                int randomCharInt = (int) Math.floor((Math.random() * 26));
                char randomChar = (char) (65 + randomCharInt);
                enteredNameInCharArray[j] = randomChar;
            }
            String enteredName = String.valueOf(enteredNameInCharArray);
            int enteredAmount = (int) Math.floor((Math.random() * 10000)) % 50;
            String currentDateTime = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(new Date());

            editTextSSO.setText(String.valueOf(enteredSSO));
            editTextName.setText(enteredName);
            editTextAmount.setText(String.valueOf(enteredAmount));
            buttonAddEntryHandler(v);
        }
    }


    public void buttonDeleteHandler(View v){
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        dbHandler.deleteHandler();
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
