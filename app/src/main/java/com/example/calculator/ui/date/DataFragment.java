package com.example.calculator.ui.date;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.calculator.R;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class DataFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    Button bt1, bt2;
    static int d, m, y,d1,m1,y1,d2,m2,y2;
    String text;
    int  value;
    TextView textView,Total;
    DatePickerDialog datePicker;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_date, container, false);
        Total=root.findViewById(R.id.total_no_days);
        bt1 = root.findViewById(R.id.date_1);
        textView=root.findViewById(R.id.differece_between_dates);
        bt2 = root.findViewById(R.id.date_2);
        if (savedInstanceState==null){
            d = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            m = Calendar.getInstance().get(Calendar.MONTH);
            y = Calendar.getInstance().get(Calendar.YEAR);
            d1=d2=d;
            m1=m2=m;
            y1=y2=y;
            text = d + " " + Month(m + 1) + " " + y;
        bt1.setText(text);
        bt2.setText(text);
        }
        else {
            bt1.setText(d1+" "+Month(m1+1)+" "+y1);
            bt2.setText(d2+" "+Month(m2+1)+" "+y2);
            Total.setText(getTotalDays());
            textView.setText(findDifference(LocalDate.of(y1,m1+1,d1),LocalDate.of(y2,m2+1,d2)));
        }
        bt1.setOnClickListener(click -> {
            value=1;
            m=m1;
            d=d1;
            y=y1;
            showDatePickerDialog();
        });
        bt2.setOnClickListener(click -> {
            value=2;
            y=y2;
            m=m2;
            d=d2;
            showDatePickerDialog();
        });
        return root;
    }

    private void showDatePickerDialog() {
        datePicker = new DatePickerDialog(this.getContext(), this, y, m, d);
        datePicker.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
              if (value==1){
                  bt1.setText(dayOfMonth+" "+Month(month+1)+" "+year);
                  y1=year;
                  m1=month;
                  d1=dayOfMonth;
              }
              else {
                  y2=year;
                  m2=month;
                  d2=dayOfMonth;
                  bt2.setText(dayOfMonth+" "+Month(month+1)+" "+year);
              }
        Total.setText(getTotalDays());
        textView.setText(findDifference(LocalDate.of(y1,m1+1,d1),LocalDate.of(y2,m2+1,d2)));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getTotalDays() {
        LocalDate start=LocalDate.of(y1,m1+1,d1);
        LocalDate end=LocalDate.of(y2,m2+1,d2);
        LocalDate localDate;
        if (start.isAfter(end)){
            localDate=start;
            start=end;
            end=localDate;
        }
        int i=0;
        while ((start.getYear()!=end.getYear())||
                ((start.getMonth()!=end.getMonth()))||
                ((start.getDayOfMonth()!=end.getDayOfMonth())))
        {
            start=start.plusDays(1);
            i++;
        }
        return i + " days";
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    String findDifference(LocalDate start_date,
                   LocalDate end_date)
    {
        String year,months,days;
        Period diff
                = Period.between(start_date,end_date);
        year= diff.getYears() == 0 ? "" : Math.abs(diff.getYears())+ " years ";
        months= diff.getMonths() == 0 ? "" : Math.abs(diff.getMonths() )+ " months ";
        days= diff.getDays() == 0 ? "" : Math.abs(diff.getDays()) + " days ";
        if (months.equals("")&&year.equals("")&&days.equals(""))
            return "Same date";
        else
            return year+months+days;
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
}