package com.example.calculator.ui.calculator;
public class Calculator extends CalculatorOperation {

    public String getMem() {
        return mem;
    }

    private String mem;
    public Calculator(String ConsoleValue, char measureSystem)
    {
        system=measureSystem;
        ConsoleValue=Reformat(ConsoleValue);
        mem=ConsoleValue;
        ConsoleValue=ConsoleValue.replace("×","*");
        ConsoleValue=ConsoleValue.replace("÷","/");
        ConsoleValue=ConsoleValue.replace("−","-");
        ConsoleValue=ConsoleValue.replace("%","/100*");
        for (int i = 0; i < list.size(); i++) {
            ConsoleValue=ConsoleValue.replace(list.get(i),constants.get(i).toString());
        }
        ConsoleValue=SolveBrackets(Reorganize(ConsoleValue));
        System.out.println(ConsoleValue);
        Operation(ConsoleValue);
    }
    String SolveBrackets(String ConsoleValue){
        int start = 0, end = 0;
        while (ConsoleValue.contains("(") || ConsoleValue.contains(")"))
        {
            for (int i = 0; i < ConsoleValue.length(); i++)
            {
                if (ConsoleValue.charAt(i) == '(')
                    start = i;
                if (ConsoleValue.charAt(i) == ')') {
                    end = i;
                    break;
                }
            }
            String x = ConsoleValue.substring(start, end + 1);
            Operation(ConsoleValue.substring(start + 1, end));
            if (start != 0 && Character.isDigit(ConsoleValue.charAt(start - 1)))
                ConsoleValue = ConsoleValue.replace(x, "*" + Evaluate());
            else if (start != 0 && ConsoleValue.charAt(start - 1) == '-') {
                String y = ConsoleValue.substring(start - 1, end + 1);
                ConsoleValue = ConsoleValue.replace(y, -1 + "*" + Evaluate());
            } else if (start != 0 && ConsoleValue.charAt(start - 1) == '*')
                ConsoleValue = ConsoleValue.replace(x, String.valueOf(Evaluate()));
            else

                ConsoleValue = ConsoleValue.replace(x, String.valueOf(Evaluate()));
            System.out.println(ConsoleValue);
        }
        return  ConsoleValue;
    }
    String Reformat(String co)
    {
        int c1=0,c2=0;
        for (int i=0;i<co.length();i++){
            if (co.charAt(i)=='(')
                c1++;
            if (co.charAt(i)==')')
                c2++;
        }
        StringBuilder coBuilder = new StringBuilder(co);
        for(int i = 0; i<(c1-c2); i++){
            coBuilder.append(")");
        }
        co = coBuilder.toString();
        if ((c1-c2)<0){
            co=co.substring(0,(co.length()-(c2-c1)));
        }
        return co;
    }
    String Reorganize(String str){
        StringBuilder tem = new StringBuilder();
        int bound = str.length();
        for (int i = 0; i < bound; i++) {
            if ((i != 0) && (constants.contains(str.charAt(i))||str.charAt(i)=='e'||str.charAt(i)=='π')
                    && (Character.isDigit(str.charAt(i - 1)) || str.charAt(i - 1) == ')'|| str.charAt(i - 1) == 'e'|| str.charAt(i - 1) == 'π')) {
                tem.append("*").append(str.charAt(i));
            } else if ((i != 0 && Character.isDigit(str.charAt(i)) && str.charAt(i - 1) == ')') ||
                    (i != 0 && Character.isDigit(str.charAt(i)) &&
                            ((str.charAt(i - 1) == 'π') || (str.charAt(i - 1) == 'e') || (str.charAt(i - 1) == '!')))) {
                tem.append("*").append(str.charAt(i));
            }
            else if ((i!=0&&str.charAt(i)=='e'&&str.charAt(i-1)=='π')||((i!=0&&str.charAt(i)=='π'&&str.charAt(i-1)=='e')))
                tem.append("*").append(str.charAt(i));
            else if ((i!=0&&str.charAt(i)=='π'&&str.charAt(i-1)=='π')||((i!=0&&str.charAt(i)=='e'&&str.charAt(i-1)=='e')))
                tem.append("*").append(str.charAt(i));
            else tem.append(str.charAt(i));
        }
        return tem.toString();
    }
    public Double Evaluate() {
        power();
        division();
        multiplication();
        AdditionAndSubtraction();
        return FinalValue;
    }
}
