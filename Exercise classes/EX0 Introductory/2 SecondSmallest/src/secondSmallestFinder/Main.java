package secondSmallestFinder;

public class Main {

    public static void main(String[] args) {

        int[] tableOfInts = {-2,-3};

        if(tableOfInts !=null) {

            int smallest = tableOfInts[0], secondSmallest = tableOfInts[0];

            for (int i = 1; i < tableOfInts.length; i++) {
                if (tableOfInts[i] < smallest) {
                    secondSmallest = smallest;
                    smallest = tableOfInts[i];
                }
                else if ((tableOfInts[i] > smallest && tableOfInts[i] < secondSmallest) || (smallest == secondSmallest))
                    secondSmallest = tableOfInts[i];
            }

            try {
                if (smallest == secondSmallest) throw new NoAnswerException("There is no answer");
                System.out.println("Second smallest: " + secondSmallest);
            } catch (NoAnswerException e) {
                e.printStackTrace();
            }
        }
    }
}
