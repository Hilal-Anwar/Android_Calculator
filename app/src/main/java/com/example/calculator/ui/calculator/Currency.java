package com.example.calculator.ui.calculator;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class Currency
{
    private TreeMap<String,String[]> cur=new TreeMap<>();
    private String rateDate="2020-09-21";
    private String currency_code1,currency_code2;
    Double x=1.0,y=1.0;
    private TreeMap<String,CurrencyData> temporaryData=new TreeMap<>();
    private double amount;
    public Currency(){
        String[] code = {"HRK", "CHF", "MXN", "ZAR", "INR", "THB", "CNY",
                "AUD", "ILS", "KRW", "JPY", "PLN", "GBP", "IDR", "HUF", "PHP", "TRY", "RUB", "HKD",
                "ISK", "DKK", "CAD", "USD", "MYR", "BGN", "NOK", "RON", "SGD", "CZK", "SEK", "NZD", "BRL"};
        for (int i = 0; i < code.length ; i++) {
            String[] currencySymbol = {"kn", "CHF", "Mex$", "R", "₹", "฿", "¥", "A$",
                    "₪", "₩", "円", "zł", "£", "Rp", "Ft", "₱",
                    "₺", "RUB", "HK$", "Íkr", "Kr", "Can$", "$", "RM",
                    "Лв", "kr", "lei", "S$", "Kč", "kr", "NZ$", "R$"};
            String[] currencyNames = {"Croatian Kunac", "Swiss Franc", "Mexican Peso", "South African Rand", "Indian rupee", "Baht", "Chinese Yuan", "Australian Dollar",
                    "Israeli New Shekel", "Korean Republic won", "Japanese yen", "Polish zloty", "Pound sterling", "Indonesian rupiah", "Hungarian Forint", "Philippine peso", "Turkish lira",
                    "Russian Rouble", "Hong Kong Dollar", "Icelandic króna", "Danish krone", "Canadian dollar", "United States Dollar", "Malaysian Ringgit", "Bulgarian Lev", "Norwegian krone",
                    "Romanian Leu", "Singapore Dollar", "Czech koruna", "Swedish krona", "New Zealand Dollar", "Brazilian real"};
            cur.put(code[i],new String[]{currencyNames[i], currencySymbol[i]});
        }
    }
    public void reload() throws IOException {
        String url_str = "https://api.exchangeratesapi.io/latest";
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        JsonElement root = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));
        StringTokenizer stringTokenizer=new StringTokenizer(root.toString(),"{}");
        String data = "";
        while (stringTokenizer.countTokens()>=2){
            data=stringTokenizer.nextToken();
        }
        String x=stringTokenizer.nextToken();
        rateDate=x.substring(x.lastIndexOf(':')+2,x.length()-1);
        StringTokenizer stringTokenizer1=new StringTokenizer(data,",");
        String name;
        temporaryData.clear();
        while (stringTokenizer1.hasMoreTokens()){
            name=stringTokenizer1.nextToken();
            temporaryData.put(Objects.requireNonNull(cur.get(name.substring(1, name.lastIndexOf('"'))))[0],
                    new CurrencyData(Objects.requireNonNull(cur.get(name.substring(1, name.lastIndexOf('"'))))[1],Double.parseDouble(name.substring(name.indexOf(':')+1))));
        }
    }
    public void setCurrency_code1(String currency_code1) {
        this.currency_code1 = currency_code1;
    }

    public void setCurrency_code2(String currency_code2) {
        this.currency_code2 = currency_code2;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double convertedRate() {
        if (!temporaryData.isEmpty()){
        x= Objects.requireNonNull(temporaryData.get(currency_code1)).getRates();
        y= Objects.requireNonNull(temporaryData.get(currency_code2)).getRates();
        }
        System.out.println(y+"   "+x);
        return y/x*amount;
    }
    public TreeMap<String, CurrencyData> getTemporaryData()
    {
        return temporaryData;
    }
    public String getRateDate() {
        return rateDate;
    }

}
