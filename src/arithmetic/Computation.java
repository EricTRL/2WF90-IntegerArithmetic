package arithmetic;

import java.util.Arrays;

/**
 * Data structure that stores a computation. Includes things like the type (add,
 * subtract, karatsuba, etc.), the numbers to operate on as a string (for easy
 * printing), the numbers to operate on as the expected data structure for
 * each of the classes (an array of ints), and the radix.
 * 
 * @author E.M.A. Arts (1004076)
 * @author K. Degeling (1018025)
 * @author R.M. Jonker (1011291)
 * @author S. Jacobs (1005276)
 * @author M. Schotsman (0995661)
 * 
 * @since 6 SEPTEMBER 2018
 */
public class Computation {
    private String xString;
    private String yString;
    private String mString;
    private String answerString;
    
    private int[] x;
    private int[] y;
    private int[] m;
    private int[] answer;
    private boolean answered = false;
    
    public int radix;
    public String type;
    public int countAdd = 0;
    public int countMul = 0;
    //TODO: answ-a, answ-b, and answ-d
    
    //constructor
    public Computation(String x, String y, int radix, String type, String m) {
        this.radix = radix;
        this.type = type;
        this.xString = x;
        this.yString = y;
        this.mString = m;
        
        //convert the read strings to 'our' data structure: an array of ints
        this.x = convertStringToIntArray(x);
        this.y = convertStringToIntArray(y);
        this.m = convertStringToIntArray(m);
        
        //safety check
        if (radix > 16 || radix < 1) {
            System.err.println("ERROR: Invalid Radix! (" + radix + ")");
        }
    }
    
    /* basic getters */
    public String getXAsString() {
        return xString;
    }
    
    public String getYAsString() {
        return yString;
    }
    
    public String getMAsString() {
        return mString;
    }
    
    public String getAnswerAsString() {
        return answered ? answerString : null;
    }
    
    public int[] getX() {
        return x;
    }
    
    public int[] getY() {
        return y;
    }
    
    public int[] getM() {
        return m;
    }
    
    public int[] getAnswer() {
        return answered ? answer : null;
    }
    
    /*Basic setters */
    public void setAnswer(int[] answer) {
        this.answer = answer;
        answered = true;
        //TODO: do sth with answerString
    }
    
    /*Other stuff*/
    @Override
    public String toString() {
        return "(" + type + ", " + xString + ", " + yString + ", " + mString + ")";
    }

    /**
     * Converts a given string to our data format (an array of integers)
     * Each character will be stored as an int. E.g. A = 10, F = 15, 3 = 3, etc.
     * @param xString
     * @pre xString.length <= Integer.MAX_VALUE
     *      && 1 <= radix of each character in xString <= 16
     * @returns 
    **/
    private int[] convertStringToIntArray(String xString) {
        //handle negative numbers
        boolean negative = xString.charAt(0) == '-';

        //initialize 'our' data structure
        int[] x = new int[xString.length() - (negative ? 1 : 0)];
        
        //convert all characters to our data format
        //start at the 2nd character if the number is negative (to skip '-')
        int iStart = negative ? 1 : 0;
        for (int i = iStart; i < xString.length(); i++){
            char c = xString.charAt(i);
            //if the number is negative, all ints in the array should be negative
            //in order for subtraction to properly work
            x[i - iStart] = Character.getNumericValue(c)*(negative ? -1 : 1);
        }
        
        //System.out.println("xString: "+xString);
        //System.out.println("x: "+Arrays.toString(x));
        return x;
    }

}
