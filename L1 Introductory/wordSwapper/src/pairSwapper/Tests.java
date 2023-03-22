package pairSwapper;

public class Tests {

    public Tests(TextFormatter textFormatter){

        System.out.print("\n\nSome \"=\" before, after and inside the sentence: '=== Litw0===_0jczyzno moja, Ty jestes jak zdr0w13, ile C13====c3n1c, t3n ty1k0 si3 d0wie=_kt0 C13 stracil===.'\nText: ");
        textFormatter.pairSwap("=== Litw0===_0jczyzno moja, Ty jestes jak zdr0w13, ile C13====c3n1c, t3n ty1k0 si3 d0wie=_kt0 C13 stracil===.");

        System.out.print("\n\nEmpty String\nText: ");
        textFormatter.pairSwap("");

        System.out.print("\n\nNull String\nText: ");
        textFormatter.pairSwap(null);

        System.out.print("\n\nSingular words of all types (identifiers, 'D1' and 'D2'): '=Litwo', 'Litwo', '=0jczyzno','0jczyzno':\nText: ");
        textFormatter.pairSwap("=Litwo");
        System.out.print("\nText: ");
        textFormatter.pairSwap("Litwo");
        System.out.print("\nText: ");
        textFormatter.pairSwap("=0jczyzno");
        System.out.print("\nText: ");
        textFormatter.pairSwap("0jczyzno");

        System.out.print("\n\nComas etc. in places that should make it problematic: Litw0 =_0jczyzno moja, Ty jestes jak zdr0w13, ile C13=c3n1c/, t3n ty1k0 si3 d0wie.=_kt0!   C13 stracil.\nText: ");
        textFormatter.pairSwap("Litw0 =_0jczyzno moja, Ty jestes jak zdr0w13, ile C13=c3n1c/, t3n ty1k0 si3 d0wie.=_kt0!   C13 stracil.");

        System.out.print("\n\nOne quick swap: Litwo=_0jczyzno\nText: ");
        textFormatter.pairSwap("Litwo=_0jczyzno");

        System.out.print("\n\nOne quick swap (incorrect identifiers): Litwo=0jczyzno\nText: ");
        textFormatter.pairSwap("Litwo=0jczyzno");

        System.out.print("\n\nFew spaws in a row: 'Litwo=_0jczyzno moja=Ty jestes=jak zdr0w13, ile C13=c3n1c'\nText: ");
        textFormatter.pairSwap("Litwo=_0jczyzno moja=Ty jestes=jak zdr0w13, ile C13=c3n1c");

        System.out.print("\n\nNewlines: 'Litw0 =_0jczyzno moja, Ty jestes jak zdr0w13, ile\n C13=c3n1c/, \nt3n ty1k0 si3 d0wie\n=_kt0 C13 stracil.'\nText: ");
        textFormatter.pairSwap("Litw0 =_0jczyzno moja, Ty jestes jak zdr0w13, ile\n C13=c3n1c/, \nt3n ty1k0 si3 d0wie\n=_kt0 C13 stracil.");

        System.out.print("\n\nTesting multiple identifiers coupled with each other: 'D342=_432B=GD' and 'D342=_432B=GD=D342=_432B=GD'\nText: ");
        textFormatter.pairSwap("D342=_432B=GD");
        System.out.print("\nText: ");
        textFormatter.pairSwap("D342=_432B=GD=D342=_432B=GD");

        System.out.print("\n\nMishmash: 'Litw0=_0jczyzno moja D342=_432B=GD=D342=_432B=GD D342=_432B'\nText: ");
        textFormatter.pairSwap("Litw0=_0jczyzno moja D342=_432B=GD=D342=_432B=GD D342=_432B");
    }
}
