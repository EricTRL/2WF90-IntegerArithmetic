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
        LinkedList<Integer> a = new LinkedList<>(Computation.convertStringToIntLinkedList("10"));
        LinkedList<Integer> b = new LinkedList<>(Computation.convertStringToIntLinkedList("-100"));
        System.out.println(subtract(a, b, 10, null));
    }
    /**
     * subtraction of two large integers x and y
     *
     * @param x array containing the words of a large integer x
     * @param y array containing the words of large integer y
     * @param b radix to be used
     * @param computation Computation to increment [count-mul] and [count-add] of (or null if we don't care)
     * @post x - y radix b
     */
    public static LinkedList<Integer> subtract(LinkedList<Integer> x, LinkedList<Integer> y, int b, Computation computation) {
        Arithmetic.makeLengthsEqual(x, y);
        if (Arithmetic.isLessThan(x, y)) { // x < y
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
}
