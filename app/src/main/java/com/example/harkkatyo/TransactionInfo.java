package com.example.harkkatyo;

import java.io.Serializable;
import java.util.Date;

public class TransactionInfo implements Serializable {
    String accountnumberfrom;
    String accountnumberto;
    String moneytxt;
    public TransactionInfo(String accnumfrom, String accnumto, String money){
        accountnumberfrom = accnumfrom;
        accountnumberto = accnumto;
        moneytxt = money;
    }

    public String toString(){
        String total = "From: " + accountnumberfrom + " To: " + accountnumberto + " Ammount: "+ moneytxt + "€";
        return total;
    }

    public String getMoneytxt() {
        return moneytxt;
    }

    public String getAccountnumberto() {
        return accountnumberto;
    }

    public String getAccountnumberfrom() {
        return accountnumberfrom;
    }
}
