package com.example.calculator.ui.scientific;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.calculator.R;
import com.example.calculator.ui.Controller;

import java.util.ArrayList;

public class ScientificFragment extends Fragment {

    View root;
    static boolean condition1 = false, condition2 = false, condition3 = false;
    ArrayList<Button> arrayList = new ArrayList<>();
    static String buttValue="Deg";
    Button button;
    @SuppressLint("StaticFieldLeak")
    static Controller c;
    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_scientific, container, false);
        button=root.findViewById(R.id.Deg);
        button.setText(buttValue);
        arrayList.add(root.findViewById(R.id.sin));
        arrayList.add(root.findViewById(R.id.cos));
        arrayList.add(root.findViewById(R.id.tan));
        arrayList.add(root.findViewById(R.id.cosec));
        arrayList.add(root.findViewById(R.id.sec));
        arrayList.add(root.findViewById(R.id.cot));
        if (savedInstanceState!=null)
        {
            if (condition1){
                ((Button)root.findViewById(R.id.InvT)).setTextColor(Color.parseColor("#03DAC5"));
                inverseT(condition1);
            }
            if (condition2){
                ((Button)root.findViewById(R.id.InvL)).setTextColor(Color.parseColor("#03DAC5"));
                inverseL(condition2);
            }
            if (condition3){
                ((Button)root.findViewById(R.id.hyp)).setTextColor(Color.parseColor("#03DAC5"));
                hyperbolic(condition3);
            }
            Controller controller1=new Controller(root);
            controller1.setAngleMeasureSystem(c.getAngleMeasureSystem());
            controller1.setArrayList(c.getArrayList());
            controller1.setExpression(c.getExpression());
            controller1.setNumber(c.getNumber());
            c=controller1;
        }
        else   c=new Controller(root);
        root.findViewById(R.id.InvT).setOnClickListener(click -> {
            condition1 = changeColor(root.findViewById(R.id.InvT), condition1);
            inverseT(condition1);
        });
        root.findViewById(R.id.InvL).setOnClickListener(click -> {
            condition2 = changeColor(root.findViewById(R.id.InvL), condition2);
            inverseL(condition2);
        });

        root.findViewById(R.id.hyp).setOnClickListener(click -> {
            condition3 = changeColor(root.findViewById(R.id.hyp), condition3);
            hyperbolic(condition3);
        });
        root.findViewById(R.id.sin).setOnClickListener(click ->c.MathFunction(((Button)root.findViewById(R.id.sin)).getText().toString()+"("));
        root.findViewById(R.id.cosec).setOnClickListener(click ->c.MathFunction(((Button)root.findViewById(R.id.cosec)).getText().toString()+"("));
        root.findViewById(R.id.cos).setOnClickListener(click ->c.MathFunction(((Button)root.findViewById(R.id.cos)).getText().toString()+"("));
        root.findViewById(R.id.sec).setOnClickListener(click ->c.MathFunction(((Button)root.findViewById(R.id.sec)).getText().toString()+"("));
        root.findViewById(R.id.tan).setOnClickListener(click ->c.MathFunction(((Button)root.findViewById(R.id.tan)).getText().toString()+"("));
        root.findViewById(R.id.cot).setOnClickListener(click ->c.MathFunction(((Button)root.findViewById(R.id.cot)).getText().toString()+"("));
        root.findViewById(R.id.log).setOnClickListener(click ->c.MathFunction(((Button)root.findViewById(R.id.log)).getText().toString()+"("));
        root.findViewById(R.id.ln).setOnClickListener(click ->c.MathFunction(((Button)root.findViewById(R.id.ln)).getText().toString()+"("));
        root.findViewById(R.id.pi).setOnClickListener(click -> c.numbers("π"));
        root.findViewById(R.id.exp).setOnClickListener(click -> c.numbers("e"));
        root.findViewById(R.id.mod).setOnClickListener(click -> c.MathFunction("mod("));
        root.findViewById(R.id.percentage).setOnClickListener(click-> c.numbers("%"));
       button.setOnClickListener(click-> {
            c.Deg( root.findViewById(R.id.Deg));
            buttValue=button.getText().toString();
        });
        return root;
    }
    @SuppressLint("SetTextI18n")
    public  void inverseT(boolean condition1){
        if (condition1) {
            for (int x = 0; x < arrayList.size(); x++) {
                arrayList.get(x).setText("a" + arrayList.get(x).getText());
            }
        } else {
            for (int x = 0; x < arrayList.size(); x++) {
                String text = arrayList.get(x).getText().toString().substring(1, arrayList.get(x).getText().length());
                arrayList.get(x).setText(text);
            }
        }
    }
    @SuppressLint("SetTextI18n")
    public  void inverseL(boolean condition2){
        if (condition2) {
            ((Button) root.findViewById(R.id.ln)).setText("e^");
            ((Button) root.findViewById(R.id.log)).setText("10^");
        } else {
            ((Button) root.findViewById(R.id.ln)).setText("ln");
            ((Button) root.findViewById(R.id.log)).setText("log");
        }
    }
    @SuppressLint("SetTextI18n")
    public void hyperbolic(boolean condition3){
        if (condition3) {
            for (int x = 0; x < arrayList.size(); x++) {
                arrayList.get(x).setText(arrayList.get(x).getText() + "h");
            }
        } else {
            for (int x = 0; x < arrayList.size(); x++) {
                String text = arrayList.get(x).getText().toString().substring(0, arrayList.get(x).getText().length() - 1);
                arrayList.get(x).setText(text);
            }
        }
    }
    boolean changeColor(Button button, boolean condition) {
        if (!condition) {
            condition = true;
            button.setTextColor(Color.parseColor("#03DAC5"));
        } else {
            condition = false;
            button.setTextColor(Color.BLACK);
        }
        return condition;
    }
}