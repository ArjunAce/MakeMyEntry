package com.example.ace.makemyentry;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewRecords extends AppCompatActivity {

    TextView tableSSO, tableName, tableUpdatedOn, tableAmount;
    TextView tmpTextView;

    TableLayout viewAll_table;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);
        tableSSO = (TextView) findViewById(R.id.tableSSO);
        tableName = (TextView) findViewById(R.id.tableName);
        tableUpdatedOn = (TextView) findViewById(R.id.tableUpdatedOn);
        tableAmount = (TextView) findViewById(R.id.tableAmount);
        viewAll_table = (TableLayout) findViewById(R.id.viewAll_table);
        populateDataTable();
    }

    public void populateDataTable() {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        ArrayList<Employee> employeeArrayList = dbHandler.loadHandler();
        for(int k = 0; k <  1; k++) {
            for (int i = 0; i < employeeArrayList.size(); i++) {
                TableRow tableRow = new TableRow(this);
                tableRow.setGravity(Gravity.CENTER);
//                TableRow.LayoutParams params = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
//                tableRow.setLayoutParams(params);
                if( i%2 == 0)
                    tableRow.setBackgroundColor(Color.parseColor("#FF698CD7"));
                Employee e = employeeArrayList.get(i);

                for (int j = 0; j < 4; j++) {
                    tmpTextView = new TextView(this);
                    tmpTextView.setGravity(Gravity.CENTER);
//                    int p = dpToPx(2f);
                    tmpTextView.setPadding(10, 10, 10, 10);
                    tmpTextView.setTextSize(18);
                    String cellData = "";
                    if (j == 0) {
                        cellData = String.valueOf(e.getSSO());
                    } else if (j == 1) {
                        cellData = e.getEmployeeName();
                    } else if (j == 2) {
                        cellData = e.getDate();
//                        tmpTextView.setBackgroundColor(Color.parseColor("#5F59FC17"));
                    } else {
                        cellData = String.valueOf(e.getAmount());
                        tmpTextView.setPadding(10, 10, 10, 10);
                    }

                    tmpTextView.setText(cellData);
                    tableRow.addView(tmpTextView);
                }
                viewAll_table.addView(tableRow);

            }
        }
        Toast toast = Toast.makeText(this, "Number Of Records: " + employeeArrayList.size(), Toast.LENGTH_LONG);
        toast.show();

    }
}
