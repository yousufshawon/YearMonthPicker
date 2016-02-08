package com.ubitrixbd.yearmonthpicker.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.ubitrixbd.yearmonthpicker.app.utility.MonthYearPickerDialog;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    Button btnDialog;
    TextView textView;
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniUi();

    }


    private void iniUi(){


        btnDialog = (Button) findViewById(R.id.btnDialog);
        textView = (TextView) findViewById(R.id.tvValue);

        setProperty();
    }



    private void setProperty(){

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mShowDialog();
            }
        });



    }




    private void mShowDialog(){

        MonthYearPickerDialog monthYearPickerDialog = new MonthYearPickerDialog(this, "Select Month and Year");
        monthYearPickerDialog.setMaxYear(Calendar.getInstance().get(Calendar.YEAR));
        monthYearPickerDialog.setMinYear(2014);
        monthYearPickerDialog.setOnSetListener(new MonthYearPickerDialog.OnSet() {
            @Override
            public void onSet(int month, int year) {

                Log.d(TAG, "month: " + month + " year: " + year);
                textView.setText("month: " + month + " year: " + year);

            }
        });
        monthYearPickerDialog.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
