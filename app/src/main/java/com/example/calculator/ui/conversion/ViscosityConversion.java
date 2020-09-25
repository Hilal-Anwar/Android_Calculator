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

public class ViscosityConversion extends Fragment implements AdapterView.OnItemSelectedListener {
    Double []dynamicConversionData={9806.6501248*9.807,9806.6501248,1488.164435,100.0,1.0,0.4133789,1488.1639328,47880.2595148,6894757.0,47880.25898};
    Double []KinematicConversionData={1.0,0.001,100.0,1000000.0,92903.04,645.16};
    Spinner unit_1,unit_2,unit_3,unit_4;
    String spinner_1_value,spinner_2_value,spinner_3_value,spinner_4_value;
    EditText editText_1,editText_2,editText_3,editText_4;
    LinkedHashMap<String,Double> hashMap=new LinkedHashMap<>();
    LinkedHashMap<String,Double> hashMap1=new LinkedHashMap<>();
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_viscosity_conversion, container, false);
        //Energy
        unit_1 = root.findViewById(R.id.energy_unit1);
        ArrayAdapter<CharSequence> adapter_1 = ArrayAdapter.createFromResource(requireContext(), R.array.viscosity_Kinematic_array, android.R.layout.simple_spinner_item);
        adapter_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit_1.setOnItemSelectedListener(this);
        unit_1.setAdapter(adapter_1);
        unit_2 = root.findViewById(R.id.energy_unit2);
        ArrayAdapter<CharSequence> adapter_2 = ArrayAdapter.createFromResource(requireContext(), R.array.viscosity_Kinematic_array, android.R.layout.simple_spinner_item);
        adapter_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit_2.setOnItemSelectedListener(this);
        unit_2.setAdapter(adapter_2);
        editText_1=root.findViewById(R.id.energy1_data);
        editText_2=root.findViewById(R.id.energy2_data);
        editText_1.setShowSoftInputOnFocus(false);
        editText_2.setShowSoftInputOnFocus(false);
        ArrayList<CharSequence> arrayList=new ArrayList<>(Arrays.asList(Objects.requireNonNull(adapter_1.getAutofillOptions())));
        for (int i=0;i<KinematicConversionData.length;i++){
            hashMap.put(arrayList.get(i).toString(),KinematicConversionData[i]);
        }
        spinner_1_value=arrayList.get(0).toString();
        spinner_2_value=arrayList.get(0).toString();
        System.out.println(spinner_1_value+"  "+spinner_2_value);
        System.out.println(hashMap.get(spinner_1_value));

        //Power

        unit_3 = root.findViewById(R.id.power_unit1);
        ArrayAdapter<CharSequence> adapter_3 = ArrayAdapter.createFromResource(requireContext(), R.array.viscosity_Dynamic_array, android.R.layout.simple_spinner_item);
        adapter_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit_3.setOnItemSelectedListener(this);
        unit_3.setAdapter(adapter_3);
        unit_4 = root.findViewById(R.id.power_unit2);
        ArrayAdapter<CharSequence> adapter_4 = ArrayAdapter.createFromResource(requireContext(), R.array.viscosity_Dynamic_array, android.R.layout.simple_spinner_item);
        adapter_4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit_4.setOnItemSelectedListener(this);
        unit_4.setAdapter(adapter_4);
        editText_3=root.findViewById(R.id.power1_data);
        editText_4=root.findViewById(R.id.power2_data);
        editText_3.setShowSoftInputOnFocus(false);
        editText_4.setShowSoftInputOnFocus(false);
        ArrayList<CharSequence> arrayList1=new ArrayList<>(Arrays.asList(Objects.requireNonNull(adapter_3.getAutofillOptions())));
        System.out.println(dynamicConversionData.length);
        for (int i=0;i<arrayList1.size();i++){
            hashMap1.put(arrayList1.get(i).toString(),dynamicConversionData[i]);
        }
        spinner_3_value=arrayList1.get(0).toString();
        spinner_4_value=arrayList1.get(0).toString();
        System.out.println(spinner_3_value+"  "+spinner_4_value);
        System.out.println(hashMap1.get(spinner_3_value));


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
            String val1=editText_1.getText().toString();
            String val2=editText_3.getText().toString();
            if (val1.length()>0&&val2.length()>0){
                editText_1.setText(val1.substring(0,val1.length()-1));
                editText_3.setText(val2.substring(0,val2.length()-1));
            }
            convert();
        });
        root.findViewById(R.id.clear).setOnClickListener(click->{
            editText_1.setText("");
            editText_2.setText("");
            editText_3.setText("");
            editText_4.setText("");
        });
        return root;
    }

    @SuppressLint("SetTextI18n")
    private void numberPad(Button button) {
        editText_1.setText(editText_1.getText().toString()+button.getText());
        editText_3.setText(editText_3.getText().toString()+button.getText());
        convert();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId()==unit_1.getId()){
            spinner_1_value=parent.getItemAtPosition(position).toString();
        }
        if (parent.getId()==unit_2.getId()){
            spinner_2_value=parent.getItemAtPosition(position).toString();
        }
        if (parent.getId()==unit_3.getId()){
            spinner_3_value=parent.getItemAtPosition(position).toString();
        }
        if(parent.getId()==unit_4.getId()){
            spinner_4_value=parent.getItemAtPosition(position).toString();
        }
        convert();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    void convert(){
        Double a=hashMap.get(spinner_1_value);
        Double b=hashMap.get(spinner_2_value);
        Double c=hashMap1.get(spinner_3_value);
        Double d=hashMap1.get(spinner_4_value);
        float x=0,y=0;
        if (!editText_1.getText().toString().equals(""))
        {
            x=Float.parseFloat(editText_1.getText().toString());
            editText_2.setText(String.valueOf(a/b*x));
        }
        if (!editText_3.getText().toString().equals(""))
        {
            y=Float.parseFloat(editText_3.getText().toString());
            editText_4.setText(String.valueOf(c/d*y));
        }
        else {
            editText_2.setText("");
            editText_4.setText("");
        }
    }
}