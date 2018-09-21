package arithmetic;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class that adds two large (integer) numbers x,y with radix b.
 * 
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
        LinkedList<Integer> a = new LinkedList<>(Computation.stringToList("9665"));
        LinkedList<Integer> b = new LinkedList<>(Computation.stringToList("4444"));
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
     * @return x + y in radix b
     */
    public static LinkedList<Integer> add(LinkedList<Integer> x, LinkedList<Integer> y, int b, Computation computation) {        
        Arithmetic.makeLengthsEqual(x, y);
        if (Arithmetic.isNegative(x)) {
            return Subtraction.subtract(y, Arithmetic.abs(x), b, null);
        } else if (Arithmetic.isNegative(y)) {
            return Subtraction.subtract(x, Arithmetic.abs(y), b, null);
        }
        /* Here both x and y are positive */
        LinkedList<Integer> answer = new LinkedList<>();
        Iterator<Integer> it_x = x.descendingIterator(); Iterator<Integer> it_y = y.descendingIterator();
        int carry = 0;
        while (it_x.hasNext() && it_y.hasNext()) {
            int a = it_x.next(); int c = it_y.next();
            answer.addFirst((a+c+carry)%b);
            if (a+c+carry>=b) carry = 1;
            else carry = 0;
        }
        if (carry > 0) answer.addFirst(carry);
        return answer;
    }    
    
}