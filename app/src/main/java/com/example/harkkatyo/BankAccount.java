package com.example.harkkatyo;

public class BankAccount {
    private String accountname;
    private float ammount;
    private int paylimit;
    private String accountnumber;

    public BankAccount(String acnam, float am, int limit, String an){
        accountname = acnam;
        ammount = am;
        paylimit = limit;
        accountnumber = an;


    }

    public String toString(){
        String total = accountname + " " + ammount+ "€ Accountnumber: " + accountnumber;
        return total;
    }

    public float getAmmount() {
        return ammount;
    }

    public String getAccountname() {
        return accountname;
    }

    public int getPaylimit() {
        return paylimit;
    }

    public String getAccountnumber() {
        return accountnumber;
    }
}
