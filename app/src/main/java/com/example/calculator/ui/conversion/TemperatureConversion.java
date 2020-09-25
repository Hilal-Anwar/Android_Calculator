package com.example.calculator.ui.conversion;

import android.annotation.SuppressLint;
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
import android.widget.EditText;
import android.widget.Spinner;

import com.example.calculator.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class TemperatureConversion extends Fragment implements AdapterView.OnItemSelectedListener {
    Spinner unit_1,unit_2;
    String spinner_1_value,spinner_2_value;
    EditText editText_1,editText_2;
    String []values={"Celsius","Fahrenheit","Kelvin"};
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_temperature_conversion, container, false);
        unit_1 = root.findViewById(R.id.unit1);
        ArrayAdapter<String> adapter_1 = new ArrayAdapter<>(requireContext(),android.R.layout.simple_spinner_item,values);
        adapter_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit_1.setOnItemSelectedListener(this);
        unit_1.setAdapter(adapter_1);
        unit_2 = root.findViewById(R.id.unit2);
        ArrayAdapter<String> adapter_2 =new ArrayAdapter<>(requireContext(),android.R.layout.simple_spinner_item,values);
        adapter_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit_2.setOnItemSelectedListener(this);
        unit_2.setAdapter(adapter_2);
        editText_1=root.findViewById(R.id.unit1_data);
        editText_2=root.findViewById(R.id.unit2_data);
        editText_1.setShowSoftInputOnFocus(false);
        editText_2.setShowSoftInputOnFocus(false);
        root.findViewById(R.id.one).setOnClickListener(click->numberPad(root.findViewById(R.id.one)));
        root.findViewById(R.id.two).setOnClickListener(click->numberPad(root.findViewById(R.id.two)));
        root.findViewById(R.id.three).setOnClickListener(click->numberPad(root.findViewById(R.id.three)));
        root.findViewById(R.id.four).setOnClickListener(click->numberPad(root.findViewById(R.id.four)));
        root.findViewById(R.id.five).setOnClickListener(click->numberPad(root.findViewById(R.id.five)));
        root.findViewById(R.id.six).setOnClickListener(click->numberPad(root.findViewById(R.id.six)));
        root.findViewById(R.id.seven).setOnClickListener(click->numberPad(root.findViewById(R.id.seven)));
        root.findViewById(R.id.eight).setOnClickListener(click->numberPad(root.findViewById(R.id.eight)));
        root.findViewById(R.id.nine).setOnClickListener(click->numberPad(root.findViewById(R.id.nine)));
        root.findViewById(R.id.zero).setOnClickListener(click->numberPad(root.findViewById(R.id.zero)));
        root.findViewById(R.id.point).setOnClickListener(click->{
            if (!editText_1.getText().toString().contains("."))
                editText_1.setText(editText_1.getText().toString()+".");
        });
        root.findViewById(R.id.cut).setOnClickListener(click->{
            String val=editText_1.getText().toString();
            if (val.length()>0)
                editText_1.setText(val.substring(0,val.length()-1));
            applyConversion();
        });
        root.findViewById(R.id.clear).setOnClickListener(click->{
            editText_1.setText("");
            editText_2.setText("");
        });
        root.findViewById(R.id.button4).setOnClickListener(click->{
            numberPad(root.findViewById(R.id.button4));
        });
        return root;
    }
    @SuppressLint("SetTextI18n")
    private void numberPad(Button button) {
        if (button.getText().equals("±") && !editText_1.getText().toString().isEmpty())
            editText_1.setText("" + (-1 * Double.parseDouble(editText_1.getText().toString())));
        if (!button.getText().equals("±") )editText_1.setText(editText_1.getText().toString() + button.getText());
        applyConversion();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId()==unit_1.getId()){
            spinner_1_value=parent.getItemAtPosition(position).toString();
        }
        else{
            spinner_2_value=parent.getItemAtPosition(position).toString();
        }
        applyConversion();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    @SuppressLint("SetTextI18n")
    void applyConversion() {
        if (!editText_1.getText().toString().isEmpty())
        {
            System.out.println("hh");
            if (spinner_1_value.equals("Celsius") &&spinner_2_value.equals("Kelvin")) {
                editText_2.setText("" + (Double.parseDouble(editText_1.getText().toString()) + 273));
            } else if ((spinner_1_value.equals("Celsius") && spinner_2_value.equals("Fahrenheit"))) {
                editText_2.setText("" + ((Double.parseDouble(editText_1.getText().toString())* 9 / 5) - 32));
            } else if ((spinner_1_value.equals("Fahrenheit") && spinner_2_value.equals("Celsius")) ){
                editText_2.setText("" + ((Double.parseDouble(editText_1.getText().toString()) - 32) * 5 / 9));
            } else if ((spinner_1_value.equals("Fahrenheit") && spinner_2_value.equals("Kelvin")) ){
                editText_2.setText("" + (((Double.parseDouble(editText_1.getText().toString()) - 32) * 5 / 9) + 273));
            } else if ((spinner_1_value.equals("Kelvin") && spinner_2_value.equals("Celsius"))) {
                editText_2.setText("" + (Double.parseDouble(editText_1.getText().toString()) - 273));
            } else if ((spinner_1_value.equals("Kelvin") && spinner_2_value.equals("Fahrenheit"))) {
                double v = (Double.parseDouble(editText_1.getText().toString()) - 273);
                editText_2.setText("" +( (v* 9 / 5) - 32));
            }
            else
            editText_2.setText(editText_1.getText());
        }
        else editText_2.setText(editText_1.getText());

    }
}