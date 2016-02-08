package com.ubitrixbd.yearmonthpicker.app.utility;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import com.ubitrixbd.yearmonthpicker.app.R;

import java.util.Calendar;

/**
 * Created by Yousuf on 2/8/2016.
 */
public class MonthYearPickerDialog {


    private  int MIN_YEAR = 1900;
    private  int MAX_YEAR = 2099;
    private static final String[] PICKER_DISPLAY_MONTHS_NAMES = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
            "Nov", "Dec" };
    private static final String[] MONTHS = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December" };

    private int selectedMonth, selectedYear;

    private NumberPicker monthPicker;
    private NumberPicker yearPicker;
    private Button btnSet;

    private Context mContext;
    private String title="Select Month and Year";
    private AlertDialog.Builder mBuilder;
    private Dialog mDialog;

    private OnSet mOnSet;


    public interface  OnSet{
        public void onSet(int month, int year);
    }


    public MonthYearPickerDialog(Context mContext, String title) {
        this.mContext = mContext;
        this.title = title;

        mDialog = new Dialog(mContext);

        mDialog.setContentView(R.layout.layout_month_year_picker);
        mDialog.setTitle(title);

        Calendar calendar = Calendar.getInstance();
        selectedMonth = calendar.get(Calendar.MONTH);
        selectedYear = calendar.get(Calendar.YEAR);

    }

    public MonthYearPickerDialog(Context mContext, String title, int selectedMonth, int selectedYear) {
        this.mContext = mContext;
        this.title = title;
        this.selectedMonth = selectedMonth;

        mDialog = new Dialog(mContext);

        mDialog.setContentView(R.layout.layout_month_year_picker);
        mDialog.setTitle(title);

    }

    public void init(int month, int year){
        selectedMonth = month;
        selectedYear = year;
    }

    public void setOnSetListener(OnSet mListener){
        mOnSet = mListener;
    }



    public void show(){

        if( mDialog != null ){

            monthPicker = (NumberPicker) mDialog.findViewById(R.id.monthPicker);
            yearPicker = (NumberPicker) mDialog.findViewById(R.id.yearPicker);
            btnSet = (Button) mDialog.findViewById(R.id.btnSet);

            monthPicker.setDisplayedValues(PICKER_DISPLAY_MONTHS_NAMES);
            monthPicker.setMinValue(0);
            monthPicker.setMaxValue(MONTHS.length - 1);
            // disable input from keyboard
            // already disable in xml
            // monthPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

            yearPicker.setMinValue(MIN_YEAR);
            yearPicker.setMaxValue(MAX_YEAR);
            // disable input from keyboard
            // already disable in xml
            //  yearPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);


            monthPicker.setValue(selectedMonth);
            yearPicker.setValue(selectedYear);




            btnSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnSet != null) {

                        int month = monthPicker.getValue();
                        int year = yearPicker.getValue();

                        mOnSet.onSet(month, year);
                    }

                    mDialog.dismiss();
                }
            });




            mDialog.show();
        }

    }

    public  void setMinYear(int minYear) {
        MIN_YEAR = minYear;
    }

    public void setMaxYear(int maxYear) {
        MAX_YEAR = maxYear;
    }

    public int getMinYear() {
        return MIN_YEAR;
    }

    public int getMaxYear() {
        return MAX_YEAR;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
