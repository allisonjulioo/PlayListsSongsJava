package exceptions;

public class InvalidRatingException extends Exception {

    private int numberSent;

    private InvalidRatingException() {

    }

    //Creates an InvalidRatingException with the invalid number.
    public InvalidRatingException(int inNumberSent) {
        this.numberSent = inNumberSent;
    }

    //Returns the numberSent of an InvalidRatingException.
    public int getNumberSent() {
        return this.numberSent;
    }

    //Returns InvalidRatingException as a String.
    public String toString() {
        String returnString;

        returnString = this.numberSent + " Essa avaliação é inválida, tente um valor entre 1 e 5.\n";

        return returnString;
    }
}