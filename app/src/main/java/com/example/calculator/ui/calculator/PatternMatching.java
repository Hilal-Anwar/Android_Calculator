package com.example.calculator.ui.calculator;

import java.util.ArrayList;
import java.util.TreeMap;

public class PatternMatching {
    TreeMap<Integer, String[]> patterns = new TreeMap<>();
    public String [] getPatterns() {
        String[] a={};
        ArrayList<Integer> arrayList= new ArrayList<>(this.patterns.keySet());
        if(!arrayList.isEmpty())
        return patterns.get(arrayList.get(arrayList.size()-1));
        else return a;
    }
    PatternMatching(String number)
    {
        int count = 0;
        int pos = 0;
        String value = "";
        for (int i = 1; i <= number.length() / 2; i++) {
            String subScript = number.substring(number.length() - i);
            for (int j = number.length() - subScript.length(); j >= 0; j = j - subScript.length())
            {
                if ((j - subScript.length()) >= 0) {
                    String substring = number.substring(j - subScript.length(), j);
                    if (substring.equals(subScript)) {
                        value = substring;
                        count++;
                        pos = j - subScript.length();
                    } else {
                        if (count >= 1) {
                            String[] a = {value,pos+""};
                            this.patterns.put(count + 1, a);
                            count = 0;
                        }
                        break;
                    }
                }
            }
            if (count >= 1) {
                String[] a = {value,pos+""};
                this.patterns.put(count + 1, a);
            }
            count = 0;
        }
    }
}
