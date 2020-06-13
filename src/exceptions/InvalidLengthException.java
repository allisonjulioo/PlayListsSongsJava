package exceptions;

public class InvalidLengthException extends Exception {

    private String lengthSent;

    //Creates a blank InvalidLengthException with no arguements.
    private InvalidLengthException() {

    }

    //Creates an InvalidLengthException that takes in the invalid String.
    public InvalidLengthException(String inString) {
        this.lengthSent = inString;
    }

    //Returns the inString of an InvalidLengthException.
    public String getLengthSet() {
        return this.lengthSent;
    }

    //Returns InvalidLengthException as a String.
    public String toString() {
        String returnString;

        returnString = this.lengthSent + " Essa duração está fora do modelo\n";

        return returnString;
    }

}