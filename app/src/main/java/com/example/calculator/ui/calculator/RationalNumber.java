package com.example.calculator.ui.calculator;

class RationalNumber
{
    static StringBuilder num = new StringBuilder();
    protected static long a, b,pos=0,l=0;
    static void findValue(String input)
    {
        pos=0;l=0;
        num=new StringBuilder();
        if (!input.contains("."))
            input = input + ".0";
        boolean condition = true;
        l = input.length();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '.' && condition) {
                pos = input.indexOf(".") + 1;
                condition = false;
            } else {
                num.append(input.charAt(i));
            }
        }
        a = Long.parseLong(num.toString());
        b = (long) Math.pow(10, l - pos);
    }
    static void findValueForInfinity(int bar){
        long x,y;
        long k;
            x=Long.parseLong(num.substring(0,num.length()-bar));
            y=Long.parseLong(num.substring(num.length()-bar,num.length()));
            k=(long)Math.pow(10,bar)-1;
            a=x*k+y;
            b=k*((long)Math.pow(10,(l-pos-bar)));
    }
}
