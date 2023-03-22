package secondSmallestFinder;

public class NoAnswerException extends Exception{

    public NoAnswerException(String s){
        super(s);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\nThe second smallest number is equal to the smallest one!\n";
    }

}
