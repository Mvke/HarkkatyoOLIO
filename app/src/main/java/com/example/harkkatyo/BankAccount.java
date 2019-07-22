package com.example.harkkatyo;

public class BankAccount {
    private String accountname;
    private String ammount;

    public BankAccount(String acnam, String am){
        accountname = acnam;
        ammount = am;


    }

    public String toString(){
        String total = accountname + " " + ammount+ "€";
        return total;
    }
}
