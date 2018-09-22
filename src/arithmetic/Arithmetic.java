package arithmetic;

import io.*;
import java.io.FileNotFoundException;
import java.util.Iterator;

import java.util.LinkedList;
import java.util.List;

/**
 * This is the base class that is run.
 * It reads input with InputReader, applies any of the arithmetic functions
 * and return the output via OutputWriter
 * 
 * @author E.M.A. Arts (1004076)
 * @author K. Degeling (1018025)
 * @author R.M. Jonker (1011291)
 * @author S. Jacobs (1005276)
 * @author M. Schotsman (0995661)
 * 
 * @since 6 SEPTEMBER 2018
 */
public class Arithmetic {   
    
    public static void main(String args[]) throws FileNotFoundException {
        //expects res/Input.txt to exist
        //a FileInputStream can also be passed as a parameter instead
        InputReader reader = new InputReader();
        
        //List of computations to make
        List<Computation> computations = new LinkedList<>();
        
        //while there are computations to read
        Computation c;
        while ((c = reader.getNextComputation()) != null) {
            //the computation's type (and modulus) signify what to compute
            switch (c.getType()) {
                case "[add]":       c.setAnswer(c.hasModulus() ? Modulo.addModulo(c) : Addition.add(c));
                                    break;
                case "[subtract]":  c.setAnswer(c.hasModulus() ? Modulo.subtractModulo(c) : Subtraction.subtract(c));
                                    break;
                case "[multiply]":  c.setAnswer(c.hasModulus() ? Modulo.multiplyModulo(c) : Multiplication.multiply(c));
                                    break;
                case "[karatsuba]": c.setAnswer(c.hasModulus() ? Modulo.multiplyModulo(c) : Karatsuba.karatsuba(c));
                                    break;
                case "[reduce]":    c.setAnswer(Modulo.modulo(c));
                                    break;
                case "[inverse]":   LinkedList<Integer> inverseAnswer = Modulo.modularInversion(c);
                                    if (inverseAnswer == null) {
                                        c.setError(true);
                                    } else {
                                        c.setAnswer(inverseAnswer);
                                    }
                                    break;
                case "[euclid]":    c.setAnswer(Euclid.euclid(c));
                                    break;
                default:            System.err.println("Invalid Computation-Type found: " + c.getType()); break;
            }
            //System.out.println(c.getAnswer()); System.out.println(c.getAnswerAsString());
            computations.add(c);
        }
        OutputWriter.writeOutput(computations); //writes to res/output.txt by default
    }

    /**
     * Makes a given number negative
     * @param x Number to make negative
     * @pre x != null
     * @modifies x
     * @post (\forall x_i; 0 <= x_i < x.size(); x[x_i] <= 0)
     */
    public static void makeNegative(LinkedList<Integer> x) {
        for (int i = 0; i < x.size(); i++) {
            x.set(i, -Math.abs(x.get(i)));
        }
    }
    
    /**
     * Inverts the given number. I.e. positive numbers become negative, and vice
     * versa
     * @param x 
     * @pre x != null
     * @modifies x
     * @post (\forall x_i; 0 <= x_i < x.size(); -x[x_i] == \old(x[x_i]))
     */
    public static void invert(LinkedList<Integer> x) {
        for (int i = 0; i < x.size(); i++) {
            x.set(i, x.get(i)*-1);
        }
    }
    
    /**
     * Adds leading zeros to one number to make its length equal to that of another
     * @param x A number
     * @param y Another number
     * @pre x != null && y != null
     * @modifies x, y
     * @post x.size() == y.size();
     */
    public static void makeLengthsEqual(LinkedList<Integer> x, LinkedList<Integer> y) {
        if (x.size()<y.size()) {
            int sizeDiff = y.size()-x.size();
            for (int i = 0; i < sizeDiff; i++) {
                x.addFirst(0);
            }
        } else if (x.size()>y.size()) {
            int sizeDiff = x.size()-y.size();
            for (int i = 0; i < sizeDiff; i++) {
                y.addFirst(0);
            }
        }
    }
    
    /**
     * Removes all leading zeros from a given number
     * @param x The number to remove the leading zeros from (if any)
     * @pre x != null
     * @modifies x
     * @post x.peekFirst() != 0 || x.size() == 1
     */
    public static void removeLeadingZeros(LinkedList<Integer> x) {
        while (x.peekFirst() == 0 && x.size() > 1) {
            x.removeFirst();
        }
    }
    
    /**
     * Method that compares x and y and outputs true if x < y
     * @param x A Number
     * @param y Another Number
     * @pre x != null && y != null
     * @return x < y
     */
    public static boolean isLessThan(LinkedList<Integer> x, LinkedList<Integer> y) {
        Arithmetic.makeLengthsEqual(x, y);
        Iterator<Integer> xIt = x.iterator(); Iterator<Integer> yIt = y.iterator();
        while (xIt.hasNext() && yIt.hasNext()) {
            int x_i = xIt.next(); int y_i = yIt.next();
            if (x_i > y_i) {
                return false; //x>y
            } else if (x_i < y_i) {
                return true; //x<y
            }
        }
        return false; //x==y, thus we output false
    }
    
    /**
     * Method that compares x and y and outputs true if x > y
     * @param x A Number
     * @param y Another Number
     * @pre x != null && y != null
     * @return x > y
     */
    public static boolean isMoreThan(LinkedList<Integer> x, LinkedList<Integer> y) {
        Arithmetic.makeLengthsEqual(x, y);
        Iterator<Integer> xIt = x.iterator(); Iterator<Integer> yIt = y.iterator();
        while (xIt.hasNext() && yIt.hasNext()) {
            int x_i = xIt.next(); int y_i = yIt.next();
            if (x_i < y_i) {
                return false; //x>y
            } else if (x_i > y_i) {
                return true; //x<y
            }
        }
        return false; //x==y, thus we output false
    }
    
    /**
     * Method that compares x and y and outputs true if they are equal
     * @param x A Number
     * @param y Another Number
     * @pre x != null && y != null
     * @return x == y
     */
    public static boolean isEqual(LinkedList<Integer> x, LinkedList<Integer> y) {
        Arithmetic.makeLengthsEqual(x, y);
        Iterator<Integer> xIt = x.iterator(); Iterator<Integer> yIt = y.iterator();
        while (xIt.hasNext() && yIt.hasNext()) {
            int x_i = xIt.next(); int y_i = yIt.next();
            if (x_i != y_i) {
                return false; //x!=y
            }
        }
        return true; //x==y, thus we output true
    }
    
    /**
     * Makes all words in a number positive
     * @param x The number to make positive
     * @pre x != null
     * @post (\forall x_i; 0 <= x_i < x.size(); x[x_i] == abs(\old(x[x_i])))
     * @return absX
     */
    public static LinkedList<Integer> abs(LinkedList<Integer> x) {
        LinkedList<Integer> absX = new LinkedList<>();
        for (int i_x : x) {
            absX.addLast(Math.abs(i_x));
        }
        return absX;
    }
    
    /**
     * Method that checks if a given number x is negative
     * @param x Number to check
     * @pre x != null
     * @return x < 0
     */
    public static boolean isNegative(LinkedList<Integer> x) {
        for (int i_x : x) {
            if (i_x < 0) {
                return true; //x < 0
            }
        }
        return false; //x >= 0
    }
    
    /**
     * Method that checks if a given number x is positive
     * @param x Number to check
     * @pre x != null
     * @return x > 0
     */
    public static boolean isPositive(LinkedList<Integer> x) {
        for (int i_x : x) {
            if (i_x > 0) {
                return true; //x > 0
            }
        }
        return false; //x <= 0
    }
    
    /**
     * Shifts the words of a given number
     * @param x
     * @pre x != null
     * @modifies x
     * @post x.size() == \old(x.size()) + num
     * @param num Number of places to shift (negative indicates a right shift)
     */
    public static void shiftBits(LinkedList<Integer> x, int num) {
        //handle shifting to the left
        for (int i = 0; i < num; i++) {
            x.addLast(0);
        }
        //handle shifting to the right
        for (int i = 0; i < -num; i++) {
            x.removeLast();
        }
    }

    /**
     * Data Structure that stores a quotient and a remainder in one spot
     * q: Quotient
     * r: Remainder
     */
    public static class QuoRem {
        public LinkedList<Integer> q;
        public LinkedList<Integer> r;
        public QuoRem(LinkedList<Integer> q, LinkedList<Integer> r) {
            this.q = q; this.r = r;
        }
        
        @Override
        public String toString() {
            return "(" + q + "," + r + ")";
        }
    }
    
    
}
