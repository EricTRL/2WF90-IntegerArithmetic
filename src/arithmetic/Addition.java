/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic;


/**
 * Class that adds two large (integer) numbers x,y with radix b.
 */
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
public class Addition {
    public static void main(String[] args) {        
        LinkedList<Integer> a = new LinkedList<>(Computation.stringToList("-10"));
        LinkedList<Integer> b = new LinkedList<>(Computation.stringToList("20"));
        System.out.println(add(a, b, 10, null));
    }
    
    /**
     * Adds c.x and x.y with radix c.radix
     * @param c
     * @return 
     */
    public static LinkedList<Integer> add(Computation c) {
        return add(c.getX(), c.getY(), c.getRadix(), c);
    }
    
    /**
     * Adds two large numbers in radix b
     *
     * @param x array with first number
     * @param y array with second number
     * @param b radix to be used
     * @param computation Computation to increment [count-mul] and [count-add] of (or null if we don't care)
     * 
     * @pre x.length==y.length
     * @return x + y in radix b
     */
    public static LinkedList<Integer> add(LinkedList<Integer> x, LinkedList<Integer> y, int b, Computation computation) {        
        Arithmetic.invert(y);
        return Subtraction.subtract(x, y, b, computation);
        
        /*
        Arithmetic.makeLengthsEqual(x, y);
        if (Arithmetic.isLessThan(Arithmetic.abs(x), Arithmetic.abs(y)) && Arithmetic.isNegative(y)) { // abs(x) < abs(y)
            LinkedList<Integer> answer = add(y, x, b, computation);
            
            Arithmetic.negative(answer);
            
            return answer;
        }
        //Assumption x > y
        int carry = 0;
        LinkedList<Integer> answer = new LinkedList<>();
        Iterator<Integer> xIt = x.descendingIterator(); Iterator<Integer> yIt = y.descendingIterator();
        while (xIt.hasNext() && yIt.hasNext()) {
            answer.addFirst(xIt.next() + yIt.next() + carry);
            if (computation != null) computation.changeCountAdd(1);
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
*/
    }    
    
}
