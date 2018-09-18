package arithmetic;

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
    }    
    
}