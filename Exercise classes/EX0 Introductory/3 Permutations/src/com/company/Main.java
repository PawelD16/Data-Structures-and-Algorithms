package com.company;

import java.util.Arrays;

public class Main {

    public static int[] reverse(int[] intTable, int l, int r) {
        int temp;
        while (l < r) {
            temp = intTable[l];
            intTable[l++] = intTable[r];
            intTable[r--] = temp;
        }
        return intTable;
    }

    public static boolean nextPermutation(int[] intTable) {
        boolean ifNextPermutationExtsts = false;
        int temp;
        if (intTable.length < 2) return false; //sprawdzamy czy znajdowanie kolejnej permutacji ma w ogole sens

        for (int i = intTable.length - 1; i > 0; i--) { //szukanie najdluzszego nierosnącego ciagu liczb od konca
            if (intTable[i - 1] < intTable[i]) {
                ifNextPermutationExtsts = true;

                for (int j = intTable.length-1; j > i-1; j--) {
                    if (intTable[j] > intTable[i - 1]) { //znajdywanie sukcesora najbardziej z prawej strony i zamiana z liczba ktora przewala ciag liczb niemalejacnych
                        temp = intTable[i - 1];
                        intTable[i - 1] = intTable[j];
                        intTable[j] = temp;
                        reverse(intTable, i , intTable.length-1); //obrót takiej ilosci liczb jaka byla w nierosnocym ciagu
                        break;
                    }
                }
                break;
            }

        }
        if (ifNextPermutationExtsts) System.out.println(Arrays.toString(intTable));
        return ifNextPermutationExtsts;
    }

    public static void main(String[] args) {
        int[] intTable = {1,2,6,2};
        System.out.println(nextPermutation(intTable));
    }

}
