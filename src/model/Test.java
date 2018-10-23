package model;

import java.math.BigInteger;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        String string = "15426398785";
        long a = Long.valueOf(string);
        List<Long> longList = new LinkedList<Long>();

        for (long i=string.length()-1; i>=0 ; i--) {
            long b = a%10;
            a = a/10;
            longList.add(b);
        }
        Collections.reverse(longList);
        System.out.println(string);
        System.out.println(longList);

    }

}
