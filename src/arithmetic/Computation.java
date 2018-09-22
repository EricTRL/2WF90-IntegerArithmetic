package arithmetic;

import java.util.LinkedList;

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
    private String answDString;
    private String answAString;
    private String answBString;
    
    private LinkedList<Integer> x;
    private LinkedList<Integer> y;
    private LinkedList<Integer> m;
    private LinkedList<Integer> answer = new LinkedList<>();
    private LinkedList<Integer> answD = new LinkedList<>();
    private LinkedList<Integer> answA = new LinkedList<>();
    private LinkedList<Integer> answB = new LinkedList<>();
    
    private final int radix;
    private final String type;
    private int countAdd = 0;
    private int countMul = 0;
    private boolean error = false; //indicates an error
    //TODO: answ-a, answ-b, and answ-d
    
    //constructor
    public Computation(String x, String y, int radix, String type, String m) {
        this.radix = radix;
        this.type = type;
        this.xString = x;
        this.yString = y;
        this.mString = m;
        
        //convert the read strings to 'our' data structure: an array of ints
        this.x = stringToList(x);
        this.y = stringToList(y);
        if (m.equals("[INVALID]")) {
            this.m = new LinkedList<>();
        } else {
            this.m = stringToList(m);
        }
        
        //safety check
        if (!type.equals("[INVALID]") && (radix > 16 || radix < 1)) {
            System.err.println("ERROR: Invalid Radix! (" + radix + ")");
        }
    }
    
    /* basic getters */
    public int getRadix() {
        return radix;
    }
    
    public boolean getError() {
        return error;
    }
    
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
        return (error || answer.isEmpty()) ? "ERROR" : answerString;
    }
    
    public String getAnswDAsString() {
        return (answer.isEmpty()) ? "ERROR" : answDString;
    }

    public String getAnswAAsString() {
        return (answer.isEmpty()) ? "ERROR" : answAString;
    }

    public String getAnswBAsString() {
        return (answer.isEmpty()) ? "ERROR" : answBString;
    }
    
    public LinkedList<Integer> getX() {
        return x;
    }
    
    public LinkedList<Integer> getY() {
        return y;
    }
    
    public LinkedList<Integer> getM() {
        return m;
    }
    
    public boolean hasModulus() {
        return !m.isEmpty();
    }
    
    public LinkedList<Integer> getAnswer() {
        return answer;
    }
    
    public LinkedList<Integer> getAnswD() {
        return answD;
    }

    public LinkedList<Integer> getAnswA() {
        return answA;
    }

    public LinkedList<Integer> getAnswB() {
        return answB;
    }
    
    public int getCountMultiply() {
        return countMul;
    }
    
    public int getCountAdd() {
        return countAdd;
    }
    
    public String getType() {
        return type;
    }
    
    /*Basic setters */
    public void setAnswer(LinkedList<Integer> answer) {
        this.answer = answer;
        this.answerString = listToString(answer);
    }
    
    public void setAnswD(LinkedList<Integer> answer) {
        this.answD = answer;
        this.answDString = listToString(answer);
    }
    
    public void setAnswA(LinkedList<Integer> answer) {
        this.answA = answer;
        this.answAString = listToString(answer);
    }
    
    public void setAnswB(LinkedList<Integer> answer) {
        this.answB = answer;
        this.answBString = listToString(answer);
    }
    
    
    public void changeCountMultiply(int iChange) {
        countMul += iChange;
    }
    
    public void changeCountAdd(int iChange) {
        countAdd += iChange;
    }
    
    public void setError(boolean b) {
        error = b;
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
     * @return A LinkedList consisting of the integers of xString
    **/
    public static LinkedList<Integer> stringToList(String xString) {
        //handle negative numbers
        boolean negative = xString.charAt(0) == '-';

        //initialize 'our' data structure
        LinkedList<Integer> x = new LinkedList<>();
        
        //convert all characters to our data format
        //start at the 2nd character if the number is negative (to skip '-')
        for (int i = (negative ? 1 : 0); i < xString.length(); i++){
            char c = xString.charAt(i);
            //if the number is negative, all ints in the array should be negative
            //in order for subtraction to properly work
            x.addLast(Character.getNumericValue(c)*(negative ? -1 : 1));
        }
        
        //System.out.println("xString: "+xString);
        //System.out.println("x: "+ x);
        return x;
    }
    
    /**
     * Converts a given linkedlist (in our data format) to a string
     * @param answer LinkedList of Integers
     * @return answer as a proper string, such that stringToList(this.return) == answer
     */
    public static String listToString(LinkedList<Integer> answer) {
        boolean negative = false;
        StringBuilder s = new StringBuilder();
        //handle negative numbers
        if (Arithmetic.isNegative(answer)) {
            s.append("-");
            negative = true;
        }
        for (int i_x : answer) {
            s.append(Integer.toHexString(i_x*(negative ? -1 : 1)));
        }
        return s.toString();
    }
}
