/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author E.M.A. Arts (1004076)
 * @author K. Degeling (1018025)
 * @author R.M. Jonker (1011291)
 * @author S. Jacobs (1005276)
 * @author M. Schotsman (0995661)
 * 
 * @since 6 SEPTEMBER 2018
 */
public class Subtraction {
    public static void main(String[] args) {
        LinkedList<Integer> a = new LinkedList<>();
        a.add(2); a.add(8);
        LinkedList<Integer> b = new LinkedList<>();
        b.add(-5);
        for(Integer x : subtract(a, b, 10, null)) {
            System.out.print(x);
        }
    }
    /**
     * subtraction of two large integers x and y
     *
     * @param x array containing the words of a large integer x
     * @param y array containing the words of large integer y
     * @param b radix to be used
     * @param computation Computation to increment [count-mul] and [count-add] of (or null if we don't care)
     * @pre x.length==y.length
     * @post x - y radix b
     */
    public static LinkedList<Integer> subtract(LinkedList<Integer> x, LinkedList<Integer> y, int b, Computation computation) {
        Arithmetic.makeLengthsEqual(x, y);
        if (lessThan(x, y)) { // x < y
            LinkedList<Integer> answer = subtract(y, x, b, computation);
            Arithmetic.negative(answer);
            return answer;
        }
        //Assumption x > y
        int carry = 0;
        LinkedList<Integer> answer = new LinkedList<>();
        Iterator<Integer> xIt = x.descendingIterator(); Iterator<Integer> yIt = y.descendingIterator();
        while (xIt.hasNext() && yIt.hasNext()) {
            answer.addFirst(xIt.next() - yIt.next() + carry);
            if (answer.getFirst()<0) {
                answer.set(0, answer.getFirst()+b);
                carry = -1;
            } else if (answer.getFirst()>=b) {
                answer.set(0, answer.getFirst()%b);
                carry = 1;
            } else {
                carry = 0;
            }
        }
        if (carry>0) {
            answer.addFirst(carry);
        }
        return answer;
    }

    /**
     * Method that compares x and y and outputs true if x < y
     * @param x
     * @param y
     * @pre x.length == y.length
     * @return x < y
     */
    public static boolean lessThan(LinkedList<Integer> x, LinkedList<Integer> y) {
        Iterator<Integer> xIt = x.descendingIterator(); Iterator<Integer> yIt = y.descendingIterator();
        while (xIt.hasNext() && yIt.hasNext()) {
            if (xIt.next()>yIt.next()) {
                return false; //x>y
            } else if (yIt.next()>xIt.next()) {
                return true; //x<y
            }
        }
        return false; //x==y, thus we output false
    }



}
