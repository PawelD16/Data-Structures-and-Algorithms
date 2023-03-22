package com.company;

import java.util.LinkedList;

public class RightLeftTest { //DZIALA !!

    public static boolean isPowerOfTwo(int x) {
        return (x != 0) && ((x & (x - 1)) == 0);
    }

    public static void main(String[] args) {
        int n = 2, x = n;
        int level = (int) (Math.ceil(Math.log(n + 1) / Math.log(2)) - 1);

        LinkedList<String> linkedList = new LinkedList<>();
        for (int i = 0; n - (int) Math.pow(2, i) > 0; i++) {
            n = n - (int) Math.pow(2, i);
        }
        while (level > 0) {
            if (n % Math.pow(2, level) < Math.pow(2, level - 1)) linkedList.add("left");
            else linkedList.add("right");
            level--;
        }

        if (isPowerOfTwo(x + 1)) linkedList.add("left");

        for (String string : linkedList) {
            System.out.println(string);
        }
    }


}
