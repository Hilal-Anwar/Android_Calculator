package com.example.calculator.ui.calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

class CalculatorOperation {
    int sign, finalSign;
    Double FinalValue = 0.0;
    ArrayList<Double> Memory = new ArrayList<>();
    ArrayList<Integer> divisionOperator = new ArrayList<>();
    ArrayList<Integer> multiplicationOperator = new ArrayList<>();
    ArrayList<Integer> powerOperator = new ArrayList<>();
    String tem = "";
    ArrayList<String> list = new ArrayList<>(Arrays.asList("√", "∛", "asinh", "acosh", "atanh", "acsch", "asech", "acoth",
            "csch", "sech", "coth", "sinh", "cosh", "tanh", "asin", "acsc",
            "acos", "asec", "atan", "acot", "sin", "csc", "cos", "sec", "tan", "cot", "log", "ln","mod"));
    ArrayList<Character> constants = new ArrayList<>(Arrays.asList('r', 'ŕ', 'ŝ', 'ć', 'ŧ', 'ç', 'ş', 'č', 'Ç', 'Ş', 'Č', 'Ŝ',
            'Ć', 'Ŧ', 's', 'ċ', 'c', 'ś', 't', 'ĉ', 'S', 'Ċ', 'C', 'Ś', 'T', 'Ĉ', 'L', 'l','M'));
    char system;
    void Operation(String ConsoleValue) {
        Memory.clear();
        divisionOperator.clear();
        multiplicationOperator.clear();
        powerOperator.clear();
        tem = "";
        sign = 1;
        finalSign = 1;
        FinalValue = 0.0;
        ConsoleValue = ConsoleValue + "+";
        for (int i = 0; i < ConsoleValue.length(); i++) {
            if ((Character.isDigit(ConsoleValue.charAt(i)) || ConsoleValue.charAt(i) == '.') || ConsoleValue.charAt(i) == 'E') {
                tem = String.format("%s%s", tem, ConsoleValue.charAt(i));
                if (ConsoleValue.charAt(i) == 'E' && ConsoleValue.charAt(i + 1) == '-') {
                    tem = String.format("%s%s", tem, '-');
                    tem = String.format("%s%s", tem, ConsoleValue.charAt(i + 2));
                    i = i + 2;
                }
                if (tem.length() == 1)
                    finalSign = sign;
            }
            if (ConsoleValue.charAt(i) == '!') {
                tem = String.valueOf(factorial(Long.parseLong(tem)));
            }
            if (ConsoleValue.charAt(i) == 'e')
                tem = String.valueOf(Math.E);
            if (ConsoleValue.charAt(i) == 'π')
                tem = String.valueOf(Math.PI);
            for (char constant : constants) {
                if (ConsoleValue.charAt(i) == constant)
                    i = function(i, constant, ConsoleValue);
            }
            if (ConsoleValue.charAt(i) == '-') {
                sign = (-1);
            }
            if (ConsoleValue.charAt(i) == '+')
                sign = 1;
            if (ConsoleValue.charAt(i) == '-' || ConsoleValue.charAt(i) == '+') {
                if (!tem.equals("")) {
                    Memory.add(Double.parseDouble(tem) * finalSign);
                    tem = "";
                }
            }
            if (ConsoleValue.charAt(i) == '/') {
                sign = 1;
                if (!tem.equals("")) {
                    Memory.add(Double.parseDouble(tem) * finalSign);
                    tem = "";
                }
                divisionOperator.add(Memory.size() - 1);
            }
            if (ConsoleValue.charAt(i) == '*') {
                sign = 1;
                if (!tem.equals("")) {
                    Memory.add(Double.parseDouble(tem) * finalSign);
                    tem = "";
                }
                multiplicationOperator.add(Memory.size() - 1);
            }
            if (ConsoleValue.charAt(i) == '^') {
                sign = 1;
                if (!tem.equals("")) {
                    Memory.add(Double.parseDouble(tem) * finalSign);
                    tem = "";
                }
                powerOperator.add(Memory.size() - 1);
            }
        }
    }

    void power() {
        for (int j = 0; j < powerOperator.size(); j++) {
            Memory.set(powerOperator.get(j), Math.pow(Memory.get(powerOperator.get(j)), (Memory.get(powerOperator.get(j) + 1))));
            Memory.remove(powerOperator.get(j) + 1);
            powerOperator = sizeReducer(powerOperator, powerOperator, j);
            divisionOperator = sizeReducer(divisionOperator, powerOperator, j);
            multiplicationOperator = sizeReducer(multiplicationOperator, powerOperator, j);
        }
    }

    //for division
    void division() {
        for (int i = 0; i < divisionOperator.size(); i++) {
            Memory.set(divisionOperator.get(i), Memory.get(divisionOperator.get(i)) / Memory.get(divisionOperator.get(i) + 1));
            Memory.remove(divisionOperator.get(i) + 1);
            divisionOperator = sizeReducer(divisionOperator, divisionOperator, i);
            multiplicationOperator = sizeReducer(multiplicationOperator, divisionOperator, i);
        }
    }

    //for multiplication
    void multiplication() {
        for (int j = 0; j < multiplicationOperator.size(); j++) {
            Memory.set(multiplicationOperator.get(j), Memory.get(multiplicationOperator.get(j)) * Memory.get(multiplicationOperator.get(j) + 1));
            Memory.remove(multiplicationOperator.get(j) + 1);
            multiplicationOperator = sizeReducer(multiplicationOperator, multiplicationOperator, j);
        }
    }

    //for addition and Subtraction
    void AdditionAndSubtraction() {
        for (Double aDouble : Memory) FinalValue = FinalValue + aDouble;
    }

    ArrayList<Integer> sizeReducer(ArrayList<Integer> memoryList1, ArrayList<Integer> memoryList2, int a) {
        for (int b = 0; b < memoryList1.size(); b++) {
            if ((memoryList2.get(a)) < memoryList1.get(b))
                memoryList1.set(b, (memoryList1.get(b) - 1));
        }
        return memoryList1;
    }

    //For maths function
    int function(int i, char type, String Data) {
        i = i + 1;
        tem = "";
        finalSign = sign;
        while (true) {
            if (Character.isDigit(Data.charAt(i)) || Data.charAt(i) == '.' || Data.charAt(i) == 'E' || tem.equals("")) {
                if (Data.charAt(i) == 'E' && Data.charAt(i + 1) == '-') {
                    tem = tem + "E" + "-";
                    i = i + 2;
                } else {
                    tem = tem + Data.charAt(i);
                    i = i + 1;
                }
            } else {
                if (system == 'R') {
                    //Trigonometrical and Inverse Trigonometrical functions
                    if (type == 'S')
                        tem = String.valueOf(Math.sin(Double.parseDouble(tem)));
                    if (type == 'C')
                        tem = String.valueOf(Math.cos(Double.parseDouble(tem)));
                    if (type == 'T')
                        tem = String.valueOf(Math.tan(Double.parseDouble(tem)));
                    if (type == 'Ċ')
                        tem = String.valueOf(1 / Math.sin(Double.parseDouble(tem)));
                    if (type == 'Ś')
                        tem = String.valueOf(1 / Math.cos(Double.parseDouble(tem)));
                    if (type == 'Ĉ')
                        tem = String.valueOf(1 / Math.tan(Double.parseDouble(tem)));
                    if (type == 's')
                        tem = String.valueOf(Math.asin(Double.parseDouble(tem)));
                    if (type == 'c')
                        tem = String.valueOf(Math.acos(Double.parseDouble(tem)));
                    if (type == 't')
                        tem = String.valueOf(Math.atan(Double.parseDouble(tem)));
                    if (type == 'ċ')
                        tem = String.valueOf(Math.asin(1 / Double.parseDouble(tem)));
                    if (type == 'ś')
                        tem = String.valueOf(Math.acos(1 / Double.parseDouble(tem)));
                    if (type == 'ĉ')
                        tem = String.valueOf(Math.atan(1 / Double.parseDouble(tem)));
                }
                //hyperbolic function
                if (type == 'Ŝ')
                    tem = String.valueOf(Math.sinh(Double.parseDouble(tem)));
                if (type == 'Ć')
                    tem = String.valueOf(Math.cosh(Double.parseDouble(tem)));
                if (type == 'Ŧ')
                    tem = String.valueOf(Math.tanh(Double.parseDouble(tem)));
                if (type == 'Ç')
                    tem = String.valueOf(1 / Math.sinh(Double.parseDouble(tem)));
                if (type == 'Ş')
                    tem = String.valueOf(1 / Math.cosh(Double.parseDouble(tem)));
                if (type == 'Č')
                    tem = String.valueOf(1 / Math.tanh(Double.parseDouble(tem)));
                if (type == 'ŝ')
                    tem = String.valueOf(Math.log(Double.parseDouble(tem) + Math.sqrt(Math.pow(Double.parseDouble(tem), 2) + 1)));
                if (type == 'ć')
                    tem = String.valueOf(Math.log(Double.parseDouble(tem) + Math.sqrt(Math.pow(Double.parseDouble(tem), 2) - 1)));
                if (type == 'ŧ')
                    tem = String.valueOf(0.5 * Math.log((1 + Double.parseDouble(tem)) / (1 - Double.parseDouble(tem))));
                if (type == 'ç')
                    tem = String.valueOf(Math.log(1 / Double.parseDouble(tem) + Math.sqrt(Math.pow(1 / Double.parseDouble(tem), 2) + 1)));
                if (type == 'ş')
                    tem = String.valueOf(Math.log((1 + Math.sqrt(1 - Math.pow(Double.parseDouble(tem), 2))) / Double.parseDouble(tem)));
                if (type == 'č')
                    tem = String.valueOf(0.5 * Math.log((Double.parseDouble(tem) + 1) / (Double.parseDouble(tem) - 1)));
                if (system == 'D') {
                    if (type == 'S')
                        tem = String.valueOf(Math.sin(Math.toRadians(Double.parseDouble(tem))));
                    if (type == 'C')
                        tem = String.valueOf(Math.cos(Math.toRadians(Double.parseDouble(tem))));
                    if (type == 'T') {
                        if (!tem.equals("90.0"))
                            tem = String.valueOf(Math.tan(Math.toRadians(Double.parseDouble(tem))));
                        else tem = "Infinity";
                    }
                    if (type == 'Ċ')
                        tem = String.valueOf(1 / Math.sin((Math.toRadians(Double.parseDouble(tem)))));
                    if (type == 'Ś')
                        tem = String.valueOf(1 / Math.cos(Math.toRadians(Double.parseDouble(tem))));
                    if (type == 'Ĉ')
                        tem = String.valueOf(1 / Math.tan(Math.toRadians(Double.parseDouble(tem))));
                    if (type == 's')
                        tem = String.valueOf(Math.toDegrees(Math.asin(Double.parseDouble(tem))));
                    if (type == 'c')
                        tem = String.valueOf(Math.toDegrees(Math.acos(Double.parseDouble(tem))));
                    if (type == 't') {
                        if (!tem.equals("90.0"))
                            tem = String.valueOf(Math.toDegrees(Math.atan(Double.parseDouble(tem))));
                        else tem = "Infinity";
                    }
                    if (type == 'ċ')
                        tem = String.valueOf(Math.toDegrees(Math.asin(1 / Double.parseDouble(tem))));
                    if (type == 'ś')
                        tem = String.valueOf(Math.toDegrees(Math.acos(1 / Double.parseDouble(tem))));
                    if (type == 'ĉ')
                        tem = String.valueOf(Math.toDegrees(Math.atan(1 / Double.parseDouble(tem))));
                }
                //log and inverse log
                if (type == 'l')
                    tem = String.valueOf(Math.log(Double.parseDouble(tem)));
                if (type == 'L')
                    tem = String.valueOf(Math.log10(Double.parseDouble(tem)));
                //for root function
                if (type == 'r')
                    tem = String.valueOf(Math.sqrt(Double.parseDouble(tem)));
                if (type == 'ŕ') {
                    tem = String.valueOf(Math.pow(Double.parseDouble(tem), 0.3333333333333333));
                }
                if (type == 'M') {
                    tem = String.valueOf(Math.abs(Double.parseDouble(tem)));
                }
                break;
            }
        }
        return i;
    }

    // for  finding factorial
    BigDecimal factorial(double n) {
        BigDecimal f = new BigDecimal("1");
        for (int i = 1; i <= n; i++)
            f = f .multiply(BigDecimal.valueOf(i));
        return f;
    }
}
