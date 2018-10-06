package com.example.ace.makemyentry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class Checkout extends AppCompatActivity {


    Button buttonFindBySSO = null;
    Button buttonAmountValue = null;
    Button buttonResetToZero = null;
    TextView editTextSSO = null;
    TextView textViewNameLabel = null;
    TextView textViewNameValue = null;
    TextView textViewUpdatedAtLabel = null;
    TextView textViewUpdatedAtValue = null;
    Employee e = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        buttonFindBySSO = (Button) findViewById(R.id.buttonFindBySSO);
        buttonAmountValue = (Button) findViewById(R.id.buttonAmountValue);
        buttonResetToZero = (Button) findViewById(R.id.buttonResetToZero);
        editTextSSO = (TextView) findViewById(R.id.editTextSSO);
        textViewNameLabel = (TextView) findViewById(R.id.textViewNameLabel);
        textViewNameValue = (TextView) findViewById(R.id.textViewNameValue);
        textViewUpdatedAtLabel = (TextView) findViewById(R.id.textViewUpdatedAtLabel);
        textViewUpdatedAtValue = (TextView) findViewById(R.id.textViewUpdatedAtValue);
    }

    public void buttonFindBySSOHandler(View v) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        int enteredSSO = Integer.parseInt(editTextSSO.getText().toString());
        e = dbHandler.findHandler(enteredSSO);
        if(e == null) {
            Toast toast = Toast.makeText(this, "Failure: Entry Not Present!", Toast.LENGTH_LONG);
            toast.show();
            textViewNameValue.setText("Entry Not Present");
            textViewUpdatedAtValue.setText("Entry Not Present");
            buttonAmountValue.setText("-");
        }
        else {
            textViewNameValue.setText(e.getEmployeeName());
            textViewUpdatedAtValue.setText(e.getDate());
            buttonAmountValue.setText("Rs." + String.valueOf(e.getAmount()));
        }
    }

    public void buttonResetToZeroHandler(View v) {
        if(e == null) {
            Toast toast = Toast.makeText(this, "Failure: Entry Not Present!", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
            String currentDateTime = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.SHORT).format(new Date());
            int enteredSSO = Integer.parseInt(editTextSSO.getText().toString());
            boolean updateHandlerSuccess = dbHandler.updateHandler(enteredSSO, 0, currentDateTime);
            if (updateHandlerSuccess) {
                Toast toast = Toast.makeText(this, "Success: Entry Updated! Bill till date: 0", Toast.LENGTH_LONG);
                toast.show();
                textViewNameValue.setText(e.getEmployeeName());
                textViewUpdatedAtValue.setText(e.getDate());
                buttonAmountValue.setText("Rs. 0");
            } else {
                Toast toast = Toast.makeText(this, "Failure: Entry Not Updated!", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}
