package com.example.calculator.ui.date;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.calculator.R;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Objects;

public class Add_and_Subtract_dates extends Fragment  implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener  {

    View root;
    Spinner years,months,days;
    String []values=new String[1000];
    static int d, m, y,newYearValue,newMonthValue,newDayValue;
    String text;
    Button bt;
    DatePickerDialog datePicker;
    RadioButton add,subtract;
    LocalDate localDate;
    TextView finalDate;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root=inflater.inflate(R.layout.fragment_add_and__subtract_dates, container, false);
        years=root.findViewById(R.id.year);
        months=root.findViewById(R.id.month);
        days=root.findViewById(R.id.days);
        add=root.findViewById(R.id.radioButton_add);
        subtract=root.findViewById(R.id.radioButton_subtract);
        finalDate=root.findViewById(R.id.final_date);
        bt=root.findViewById(R.id.date_3);
        if (savedInstanceState==null){
        d = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        m = Calendar.getInstance().get(Calendar.MONTH);
        y = Calendar.getInstance().get(Calendar.YEAR);
        }
        localDate=LocalDate.of(y,m+1,d);
        text = d + " " + Month(m + 1) + " " + y;
        bt.setText(text);
        for (int i=0;i<values.length;i++){
            values[i]= String.valueOf(i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item,values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        years.setAdapter(adapter);
        months.setAdapter(adapter);
        days.setAdapter(adapter);
        years.setOnItemSelectedListener(this);
        months.setOnItemSelectedListener(this);
        days.setOnItemSelectedListener(this);
        add.setOnClickListener(click->{
            subtract.setChecked(false);
            add.setChecked(true);
            DateOperation();

        });
        subtract.setOnClickListener(click->{
            subtract.setChecked(true);
            add.setChecked(false);
            DateOperation();
        });
        bt.setOnClickListener(click -> showDatePickerDialog());
        return root;
    }
    private void showDatePickerDialog() {
        datePicker = new DatePickerDialog(this.getContext(), this, y, m, d);
        datePicker.show();
    }
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        int l= Integer.parseInt((parent.getItemAtPosition(position).toString()));
              if (parent.getId()==years.getId()){
                 newYearValue=l;
              }
              if (parent.getId()==months.getId()){
                  newMonthValue=l;
              }
              if (parent.getId()==days.getId()){
                  newDayValue=l;
              }
          DateOperation();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
          y=year;
          m=month;
          d=dayOfMonth;
          bt.setText(d+" "+Month(m+1)+" "+y);
          localDate=LocalDate.of(y,m+1,d);
          DateOperation();
    }
    public String Month(int number) {
        switch (number) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "Not a month";
        }
    }
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    void DateOperation(){
        LocalDate localDate1=localDate;
        if (subtract.isChecked()){
           localDate1=localDate1.minusYears(newYearValue);
           localDate1=localDate1.minusMonths(newMonthValue);
            localDate1=localDate1.minusDays(newDayValue);
        }
        else {
            localDate1=localDate1.plusYears(newYearValue);
            localDate1=localDate1.plusMonths(newMonthValue);
            localDate1=localDate1.plusDays(newDayValue);
        }
        finalDate.setText(localDate1.getDayOfMonth()+" "+Month(localDate1.getMonthValue())+" "+localDate1.getYear());
    }
}