package com.example.harkkatyo;

public class BankAccount {
    private String accountname;
    private float ammount;

    public BankAccount(String acnam, float am){
        accountname = acnam;
        ammount = am;


    }

    public String toString(){
        String total = accountname + " " + ammount+ "€";
        return total;
    }
}
