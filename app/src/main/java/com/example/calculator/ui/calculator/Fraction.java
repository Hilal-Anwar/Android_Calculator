package com.example.calculator.ui.calculator;


public class Fraction {
    private final String number;
    private int bar = 0;

    public Fraction(String input) {
        String num = input.substring(input.indexOf('.') + 1);
        String[] a = new PatternMatching(num).getPatterns();
        if ((a.length > 0)&&!a[0].equals("9")) {
            this.bar = a[0].length();
            String scr = "";
            int index = Integer.parseInt(a[1]);
            if (index != 0)
                scr = num.substring(0, index);
            this.number = input.substring(0, input.indexOf('.') + 1) + scr + a[0];
        } else {
            this.number = input;
        }
    }

    Fraction(String NumberWith_a_bar, int No_of_Digit_that_has_bar) {
        this.bar = No_of_Digit_that_has_bar;
        this.number = NumberWith_a_bar;
    }

    public String getValues() {
        if (this.bar != 0) {
            return Rational.P_by_QFormTillInfinity(this.number, bar);
        } else {
            return Rational.P_by_QForm(number);
        }
    }
}
