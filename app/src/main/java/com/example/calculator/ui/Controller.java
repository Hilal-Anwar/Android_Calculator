package com.example.calculator.ui;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.calculator.R;
import com.example.calculator.ui.calculator.Calculator;
import com.example.calculator.ui.calculator.Fraction;

import java.util.ArrayList;

public class Controller {
    int x=0;
    char angleMeasureSystem='D';
    ArrayList<String> arrayList=new ArrayList<>();
    String expression,number=" ";
    EditText box;
    TextView Answer;
    @SuppressLint({"SetTextI18n", "NewApi"})
    public Controller(View root){
        Answer = root.findViewById(R.id.answer);
        box = root.findViewById(R.id.expression);
        box.setShowSoftInputOnFocus(false);
        Answer.setShowSoftInputOnFocus(false);
        root.findViewById(R.id.zero).setOnClickListener(click -> numbers("0"));
        root.findViewById(R.id.one).setOnClickListener(click -> numbers("1"));
        root.findViewById(R.id.two).setOnClickListener(click -> numbers("2"));
        root.findViewById(R.id.three).setOnClickListener(click -> numbers("3"));
        root.findViewById(R.id.four).setOnClickListener(click ->numbers("4"));
        root.findViewById(R.id.five).setOnClickListener(click -> numbers("5"));
        root.findViewById(R.id.six).setOnClickListener(click -> numbers("6"));
        root.findViewById(R.id.seven).setOnClickListener(click ->numbers("7"));
        root.findViewById(R.id.eight).setOnClickListener(click -> numbers("8"));
        root.findViewById(R.id.nine).setOnClickListener(click -> numbers("9"));
        root.findViewById(R.id.point).setOnClickListener(click -> numbers("."));
        root.findViewById(R.id.fractional).setOnClickListener(click ->fraction());
        root.findViewById(R.id.pow).setOnClickListener(click ->power());
        root.findViewById(R.id.clear).setOnClickListener(click -> clear());
        root.findViewById(R.id.cut).setOnClickListener(click -> cut());
        root.findViewById(R.id.br1).setOnClickListener(click -> bracket("("));
        root.findViewById(R.id.br2).setOnClickListener(click ->bracket(")"));
        root.findViewById(R.id.root).setOnClickListener(click -> MathFunction("√("));
        root.findViewById(R.id.fac).setOnClickListener(click ->MathFunction("!"));
        root.findViewById(R.id.product).setOnClickListener(click ->Operator("×"));
        root.findViewById(R.id.subtraction).setOnClickListener(click -> Operator("−"));
        root.findViewById(R.id.add).setOnClickListener(click -> Operator("+"));
        root.findViewById(R.id.division).setOnClickListener(click -> Operator("÷"));
        root.findViewById(R.id.cube_root).setOnClickListener(click -> MathFunction("∛("));
        root.findViewById(R.id.equal).setOnClickListener(click -> equal());
    }

    @SuppressLint("SetTextI18n")
    public void Deg(Button viewById) {
        if (viewById.getText().equals("Deg")){
            viewById.setText("Rad");
            angleMeasureSystem='R';
        }
        else {
            viewById.setText("Deg");
            angleMeasureSystem='D';
        }
        getAnswer();
    }
    @SuppressLint("SetTextI18n")
    public void Operator(String button)
    {
        if (!box.getText().toString().equals(""))
        {
            if (!number.equals(" ")) {
                box.setText(expression + button);
                arrayList.add(button);
                x = arrayList.size() - 1;
            }
            else if (!box.getText().toString().equals("−"))
            {
                try {
                    box.setText(expression + button);
                    arrayList.set(x, button);
                } catch (Exception exception) {
                    arrayList.add(button);
                }
            }
            getAnswer();
        }
        else if (button.equals("−") && box.getText().toString().equals("")) {
            arrayList.add(button);
            box.setText(box.getText().toString() + button);
            expression = box.getText().toString();
            getAnswer();
        }
        number = " ";
        box.setSelection(box.getText().toString().length());
    }
    @SuppressLint("SetTextI18n")
    public void numbers(String button)  {
        if (Character.isDigit(button.charAt(0))||button.equals("e")||button.equals("π")||button.equals("%"))
        {
            number = number + button;
            box.setText(box.getText().toString() + button);
            arrayList.add(button);
        }
        if (button.equals(".") && !number.contains(".")) {
            number = number + button;
            box.setText(box.getText().toString() + button);
            arrayList.add(button);
        }
        expression = box.getText().toString();
        box.setSelection(box.getText().toString().length());
        getAnswer();
    }
    @SuppressLint("SetTextI18n")
    void getAnswer()
    {
        try {
            Answer.setText(new Calculator(box.getText().toString(),angleMeasureSystem).Evaluate().toString());
        } catch (Exception e) {
            System.out.println("Bad expression");
        }

    }
    @SuppressLint("SetTextI18n")
    public void power(){
        if (!number.equals(" "))
        {
            box.setText(box.getText()+"^(");
            arrayList.add("^(");
            expression=box.getText().toString();
            number=" ";
            getAnswer();
        }
    }
    public void clear(){
        expression = "";
        arrayList.clear();
        Answer.setText("");
        box.setText(expression);
        number = " ";
    }
    public void cut(){
        if (arrayList.size() != 0)
        {
            expression = "";
            if (arrayList.get(arrayList.size() - 1).equals(".")||arrayList.get(arrayList.size() - 1).equals("^("))
                number="0";
            arrayList.remove(arrayList.size() - 1);
            for (String s : arrayList) expression = String.format("%s%s", expression, s);
            box.setText(expression);
            getAnswer();
        }
    }
    @SuppressLint("SetTextI18n")
    public void bracket(String button){
        box.setText(box.getText()+ button);
        arrayList.add(button);
        expression = box.getText().toString();
        box.setSelection(box.getText().toString().length());
        getAnswer();
    }
    @SuppressLint("SetTextI18n")
    public void MathFunction(String text) {
            arrayList.add(text);
            box.setText(box.getText() + text);
            expression = box.getText().toString();
            number = " ";
    }
    @SuppressLint("SetTextI18n")
    public void  fraction(){
        try {
            Answer.setText(new Fraction(Answer.getText().toString()).getValues());
        } catch (Exception e) {
          Answer.setText("Bad Expression");
        }
    }
    @SuppressLint("SetTextI18n")
    public  void  equal(){
        try {
            box.setText(new Calculator(box.getText().toString(),angleMeasureSystem).Evaluate().toString());
            Answer.setText("");
            expression=box.getText().toString();
        } catch (Exception e) {
         box.setText("Bad expression");
         Answer.setText("");
        }
    }

    public char getAngleMeasureSystem() {
        return angleMeasureSystem;
    }

    public void setAngleMeasureSystem(char angleMeasureSystem) {
        this.angleMeasureSystem = angleMeasureSystem;
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
