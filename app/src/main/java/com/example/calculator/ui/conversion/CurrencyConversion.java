package com.example.calculator.ui.conversion;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.calculator.R;
import com.example.calculator.ui.ExampleDialog;
import com.example.calculator.ui.calculator.Currency;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class CurrencyConversion extends Fragment implements AdapterView.OnItemSelectedListener {
    View root;
    Currency currency=new Currency();
    ProgressBar progressBar;
    Spinner currency_1,currency_2;
    private String []values={"Croatian Kunac", "Swiss Franc", "Mexican Peso", "South African Rand", "Indian rupee", "Baht", "Chinese Yuan", "Australian Dollar",
            "Israeli New Shekel", "Korean Republic won", "Japanese yen", "Polish zloty", "Pound sterling", "Indonesian rupiah", "Hungarian Forint", "Philippine peso", "Turkish lira",
            "Russian Rouble", "Hong Kong Dollar", "Icelandic króna", "Danish krone", "Canadian dollar", "United States Dollar", "Malaysian Ringgit", "Bulgarian Lev", "Norwegian krone",
            "Romanian Leu", "Singapore Dollar", "Czech koruna", "Swedish krona", "New Zealand Dollar", "Brazilian real"};
    String spinner_1_value="Australian Dollar",spinner_2_value="Australian Dollar";
    TextView editText_1,editText_2,message,symbol_1,symbol_2;
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Arrays.sort(values);
        root=inflater.inflate(R.layout.fragment_currency_conversion, container, false);
        reload_Currency_data();
        setup();
        root.findViewById(R.id.update_rates).setOnClickListener(click-> {
            reload_Currency_data();
            message.setText("Updating ...");
            progressBar.setVisibility(View.VISIBLE);
        });
        return root;
    }
    @SuppressLint("SetTextI18n")
    void reload_Currency_data(){
        new Thread(() -> {
            try {
                currency.reload();
                message.setText("Updated on "+ currency.getRateDate()+","+" by European central bank ."+"Updated after 24 hours");
                progressBar.setVisibility(View.INVISIBLE);
            } catch (IOException e) {
                message.setText("Connection error");
                progressBar.setVisibility(View.INVISIBLE);
                DialogFragment newFragment = new ExampleDialog();
                newFragment.show(getChildFragmentManager(),"Currency rates");
            }
        }).start();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (!currency.getTemporaryData().isEmpty())
        {
        if (parent.getId()==currency_1.getId()){
            spinner_1_value=parent.getItemAtPosition(position).toString();
            symbol_1.setText(Objects.requireNonNull(currency.getTemporaryData().get(spinner_1_value)).getSymbol());
        }
        else{
            spinner_2_value=parent.getItemAtPosition(position).toString();
            symbol_2.setText(Objects.requireNonNull(currency.getTemporaryData().get(spinner_2_value)).getSymbol());
        }
        convert();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    void setup(){
        currency_1=root.findViewById(R.id.currency_1);
        currency_2=root.findViewById(R.id.currency_2);
        editText_1=root.findViewById(R.id.value_1);
        editText_2=root.findViewById(R.id.value_2);
        editText_1.setShowSoftInputOnFocus(false);
        editText_2.setShowSoftInputOnFocus(false);
        message=root.findViewById(R.id.message);
        symbol_1=root.findViewById(R.id.symbol_1);
        symbol_2=root.findViewById(R.id.symbol_2);
        progressBar=root.findViewById(R.id.progressBar);
        message.setText("Updating ...");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item,values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currency_1.setOnItemSelectedListener(this);
        currency_1.setAdapter(adapter);
        currency_2.setOnItemSelectedListener(this);
        currency_2.setAdapter(adapter);
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
    }
    @SuppressLint("SetTextI18n")
    private void numberPad(Button button) {
        editText_1.setText(editText_1.getText().toString()+button.getText());
        convert();
    }
    @SuppressLint("SetTextI18n")
    void convert(){
        if (!currency.getTemporaryData().isEmpty()){
        currency.setCurrency_code1(spinner_1_value);
        currency.setCurrency_code2(spinner_2_value);
        symbol_2.setText(Objects.requireNonNull(currency.getTemporaryData().get(spinner_2_value)).getSymbol());
        currency.setAmount(editText_1.getText().toString().isEmpty() ? 0 : Double.parseDouble(editText_1.getText().toString()));
        editText_2.setText(""+currency.convertedRate());
        }
    }
}