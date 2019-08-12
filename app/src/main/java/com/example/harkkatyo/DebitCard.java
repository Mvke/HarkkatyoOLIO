package com.example.harkkatyo;

public class DebitCard {
    String cardname;
    String cardnumber;
    String accountnumber;
    int buylimit;
    public DebitCard(String cardna,String cardnu, String accnum, int limit){
        cardname = cardna;
        cardnumber = cardnu;
        accountnumber = accnum;
        buylimit = limit;
    }

    public String toString(){
        String total = cardname + " buylimit: "+ buylimit;
        return total;
    }

    public int getBuylimit() {
        return buylimit;
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
