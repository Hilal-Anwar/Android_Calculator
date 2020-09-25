package com.example.calculator.ui.calculator;

class Rational extends RationalNumber {
    static String P_by_QForm(String rational) {
        findValue(rational);
        return a / Hcf(a, b) + "/" + b / Hcf(a, b);
    }
    static String P_by_QFormTillInfinity(String rational,int barOnDigits) {
        findValue(rational);
        findValueForInfinity(barOnDigits);
        /*
        if (!String.valueOf((a / Hcf(a, b))/(b / Hcf(a, b))).startsWith(rational)) {
            findValue(rational);
        }*/
        return a / Hcf(a, b) + "/" + b / Hcf(a, b);
    }
    static private long Hcf(long a, long b) {
        long gdc = 0, count = 0;
        for (long i = 1; i <= Math.max(a, b); i++) {
            if (a % i == 0 && b % i == 0) {
                gdc = Math.max(i, gdc);
            } else {
                count++;
                if (count == 500)
                    break;
            }
        }
        return gdc;
    }
}
