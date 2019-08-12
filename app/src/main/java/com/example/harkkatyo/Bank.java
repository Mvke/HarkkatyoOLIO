package com.example.harkkatyo;

import android.content.Context;

public class Bank {
    String id;
    String name;
    public Bank(String idnum, String na){
        id = idnum;
        name = na;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
