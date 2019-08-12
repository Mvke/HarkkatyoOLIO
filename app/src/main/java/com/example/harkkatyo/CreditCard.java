package com.example.harkkatyo;

public class CreditCard {
    String cardname;
    String cardnumber;
    String accountnumber;
    int buylimit;
    int credit;
    public CreditCard(String cardna,String cardnu, String accnum, int limit, int cre){
        cardname = cardna;
        cardnumber = cardnu;
        accountnumber = accnum;
        buylimit = limit;
        credit = cre;
    }

    public String toString(){
        String total = cardname + " buylimit: "+ buylimit + "€ Credit: " + credit + "€";
        return total;
    }

    public int getBuylimit() {
        return buylimit;
    }

    public int getCredit() {
        return credit;
    }

    public String getCardname() {
        return cardname;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public String getAccountnumber() {
        return accountnumber;
    }
}
