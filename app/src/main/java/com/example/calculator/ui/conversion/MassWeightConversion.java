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
import java.util.LinkedHashMap;
import java.util.Objects;

public class MassWeightConversion extends Fragment implements AdapterView.OnItemSelectedListener {
    Double []weightConversionData={0.001,1.0,14.59,1.661E-27,2.835E-2,0.4536,907.2,1000.0};
    Spinner unit_1,unit_2;
    String spinner_1_value,spinner_2_value;
    EditText editText_1,editText_2;
    LinkedHashMap<String,Double> hashMap=new LinkedHashMap<>();
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_mass_weight_conversion, container, false);
        unit_1 = root.findViewById(R.id.unit1);
        ArrayAdapter<CharSequence> adapter_1 = ArrayAdapter.createFromResource(requireContext(), R.array.mass_array, android.R.layout.simple_spinner_item);
        adapter_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit_1.setOnItemSelectedListener(this);
        unit_1.setAdapter(adapter_1);
        unit_2 = root.findViewById(R.id.unit2);
        ArrayAdapter<CharSequence> adapter_2 = ArrayAdapter.createFromResource(requireContext(), R.array.mass_array, android.R.layout.simple_spinner_item);
        adapter_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit_2.setOnItemSelectedListener(this);
        unit_2.setAdapter(adapter_2);
        editText_1=root.findViewById(R.id.unit1_data);
        editText_2=root.findViewById(R.id.unit2_data);
        editText_1.setShowSoftInputOnFocus(false);
        editText_2.setShowSoftInputOnFocus(false);
        ArrayList<CharSequence> arrayList=new ArrayList<>(Arrays.asList(Objects.requireNonNull(adapter_1.getAutofillOptions())));
        for (int i=0;i<weightConversionData.length;i++){
            hashMap.put(arrayList.get(i).toString(),weightConversionData[i]);
        }
        spinner_1_value=arrayList.get(0).toString();
        spinner_2_value=arrayList.get(0).toString();
        System.out.println(spinner_1_value+"  "+spinner_2_value);
        System.out.println(hashMap.get(spinner_1_value));
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
            convert();
        });
        root.findViewById(R.id.clear).setOnClickListener(click->{
            editText_1.setText("");
            editText_2.setText("");
        });
        return root;
    }

    @SuppressLint("SetTextI18n")
    private void numberPad(Button button) {
        editText_1.setText(editText_1.getText().toString()+button.getText());
        convert();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId()==unit_1.getId()){
            spinner_1_value=parent.getItemAtPosition(position).toString();
        }
        else{
            spinner_2_value=parent.getItemAtPosition(position).toString();
        }
        convert();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    void convert(){
        Double a=hashMap.get(spinner_1_value);
        Double b=hashMap.get(spinner_2_value);
        float x=0;
        if (!editText_1.getText().toString().equals(""))
            x=Float.parseFloat(editText_1.getText().toString());
        editText_2.setText(String.valueOf(a/b*x));
    }
}