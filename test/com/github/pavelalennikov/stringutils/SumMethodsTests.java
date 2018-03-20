package com.github.pavelalennikov.stringutils;

import com.github.pavelalennikov.StringUtilsNew;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SumMethodsTests {
    @Test
    public void sumNumbersMethodTest() {
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                String result = StringUtilsNew.sumNumbers(String.valueOf(i), String.valueOf(j));
                assertEquals("Wrong result for input = {" + i + ", " + j + "} result = " + result,
                        String.valueOf(i + j), result);
            }
        }
    }

    //runs test only for i, j ∈ [0, 99] because of low performance
    @Test
    public void sumNumbersByOneMethodTest() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                String result = StringUtilsNew.sumNumbersByOne(String.valueOf(i), String.valueOf(j));
                assertEquals("Wrong result for input = {" + i + ", " + j + "} result = " + result,
                        String.valueOf(i + j), result);
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void sumMethodValidationTest() {
        StringUtilsNew.sumNumbers("abc", "");

    }

    @Test(expected = IllegalArgumentException.class)
    public void sumByOneMethodValidationTest() {
        StringUtilsNew.sumNumbers("abc", "");
    }

}
