package com.example.harkkatyo;

public class BankAccountSavings {
    private String accountname;
    private float ammount;
    private String accountnumber;

    public BankAccountSavings(String acnam, float am, String an){
            accountname = acnam;
            ammount = am;
        accountnumber = an;


    }

    public String toString(){
        String total = accountname + " " + ammount+ "€ Accountnumber: " + accountnumber;
        return total;
    }

    public String getAccountname() {
        return accountname;
    }

    public float getAmmount() {
        return ammount;
    }

    public String getAccountnumber() {
        return accountnumber;
    }
}


