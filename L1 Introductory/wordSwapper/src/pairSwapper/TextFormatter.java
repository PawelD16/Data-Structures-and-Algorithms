package pairSwapper;

import java.util.ArrayList;

public class TextFormatter {

    private final char[] canStartWith = {'A', 'Ą', 'B', 'C', 'Ć', 'D', 'E', 'Ę', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'Ł', 'M', 'N', 'Ń', 'O', 'Ó', 'P', 'Q', 'R', 'S', 'Ś', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Ź', 'Ż',
            'a', 'ą', 'b', 'c', 'ć', 'd', 'e', 'ę', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'ł', 'm', 'n', 'ń', 'o', 'ó', 'p', 'q', 'r', 's', 'ś', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'ź', 'ż'};
    private final char[] cantStartWith = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private final char[] punctiation = {' ', '[', ',', '?', '.', '@', '!', ':', '(', ')', ';', ']', '/', '\\','\'','\"','\n'};

    ArrayList<String> foundWords;
    String[] strings;

    public TextFormatter() {
        foundWords = new ArrayList<>();
    }

    public void pairSwap(String text) {

        if(text == null){ //sprawdzamy czy text jest pusty
            System.out.println("Text is null");
            return;
        }

        strings = equalSignSplitter(text);
        String[] resultString = strings;

            if(strings.length>1) {
                for (int i = 0; i < strings.length; i++) { //zamiana d2->d1
                    if (identifierRecognizer(foundWords.get(2 * i)) && identifierRecognizer(foundWords.get(2 * i + 1).substring(1, foundWords.get(2 * i + 1).length())) && foundWords.get(2 * i + 1).contains("=")) {
                        resultString[i] = strings[i].substring(0, strings[i].length() - foundWords.get(2 * i).length()) + foundWords.get(2 * i + 1).substring(1, foundWords.get(2 * i + 1).length());
                    }
                }

                for (int i = strings.length; i > 0; i--) { //zamiana d1->d2
                    if (identifierRecognizer(foundWords.get(2 * i - 2)) && identifierRecognizer(foundWords.get(2 * i - 1).substring(1, foundWords.get(2 * i - 1).length())) && foundWords.get(2 * i - 1).contains("=")) {
                        resultString[i] = '=' + foundWords.get(2 * i - 2) + resultString[i].substring(foundWords.get(2 * i - 1).length(), resultString[i].length());
                    }
                }

                for (int i = 0; i < resultString.length; i++) {
                    System.out.print(resultString[i]);
                }
            } else System.out.print(text);
            foundWords.clear();
    }

    private String[] equalSignSplitter(String text) {
        text = text.replaceAll("\n","");
        String[] equalSignSplitText = text.split("(?==)"), tempStringArray; //dzielimy tekst tak, aby znaki rownosci byly na poczatku
        String d1 = null, d2;

        for (int i = 0; i < equalSignSplitText.length; i++) {
            tempStringArray = equalSignSplitText[i].split("(?<= )"); //dzielimy kawalki tektu tak, aby spacje byly za slowem
            if (tempStringArray[0].contains("=") && d1!=null) { //dzieki temu nie zaczniemy od d2 (na przykład gdy przed pierwszym slowem są znaki rownosci)
                d2 = tempStringArray[0];
                //System.out.println("d2: " + d2);

                while (true) { //usuwana jest interpunkcja za "d2", nie mozemy odrzucac tych slow przez znaki interpunkcyjne za nimi, poniewaz pozwalaja na to zasady pisowni
                    int breakCondition = 0;
                    for (int j = 0; j < punctiation.length; j++) {
                        if (d2.charAt(d2.length() - 1) == punctiation[j]) d2 = d2.substring(0, d2.length() - 1);
                        else breakCondition++;
                    }
                    if (breakCondition == punctiation.length) break;
                }
            } else d2 = null;

            d1 = tempStringArray[tempStringArray.length - 1]; // nie sprawdzamy interpunkcji za "d1", poniewaz za d1 i przed d2 (d1=d2) nie moze byc niczego
            //System.out.println("d1: " + d1);


            if (d2 != null) {
                foundWords.add(d2);
            }
            if (d1 != null) {
                foundWords.add(d1);
            }
        }

        //System.out.println(foundWords);
        return equalSignSplitText;
    }

    private boolean identifierRecognizer(String word) {
        boolean ifIdentifier = true;
        int counter = 0;

        if (word != null && word.length() > 0) { //sprawdzanie pierwszego znaku
            for (int i = 0; i < canStartWith.length; i++) {
                if (word.charAt(0) == canStartWith[i] || word.charAt(0) == '_') {
                    counter++;
                    break;
                }
            }

            for (int i = 1; i < word.length(); i++) { //sprwadzaie reszty znakow (inne kryteria niz znak pierwszy)
                for (int j = 0; j < canStartWith.length; j++) {
                    if (word.charAt(i) == canStartWith[j]) counter++;
                }
                for (int k = 0; k < cantStartWith.length; k++) {
                    if (word.charAt(i) == cantStartWith[k]) counter++;
                }
            }

            if (counter != word.length()) ifIdentifier = false;
            //System.out.println("'Text: '" + word + "'Counter: '" + counter + "'text.lenght: '" + word.length() + "ifIdentifier: '" + ifIdentifier);
        } else ifIdentifier = false;
        return ifIdentifier;
    }
}
