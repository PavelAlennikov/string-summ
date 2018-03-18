package com.github.pavelalennikov;

import static com.github.pavelalennikov.StringUtils.getStringDigitsInfo;
import static com.github.pavelalennikov.StringUtils.summ;

public class Demo {
    public static void main(String[] args) {
        String firstString = "197";
        String secondString = "94";
        getStringDigitsInfo();
        System.out.println(summ(firstString, secondString));
    }
}
