package arithmetic;


import static arithmetic.Addition.add;
import static arithmetic.Multiplication.multiply;
import static arithmetic.Subtraction.subtract;
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
public class Karatsuba {    
    public static void main(String[] args) {
        LinkedList<Integer> a = new LinkedList<>();
        a.addLast(9);
        a.addLast(0);
        a.addLast(1);
        a.addLast(8);
        
        LinkedList<Integer> b = new LinkedList<>(a);
        System.out.println(karatsuba(a,b,10));
    }
    
    /**
     * Adds to large numbers in radix b
     *
     * @param x array with first number
     * @param y array with second number
     * @param b radix to be used
     * @pre x.length==y.length
     * @return 
     */
    public static LinkedList<Integer> karatsuba (LinkedList<Integer> x, LinkedList<Integer> y, int b) {
        int size = x.size();
        
        if (x.size() % 2 != 0) {
            x.addFirst(0);
            y.addFirst(0);
            size++;
        }//x.size() = even
        LinkedList<Integer> xLo = new LinkedList<>();
        LinkedList<Integer> yLo = new LinkedList<>();
        LinkedList<Integer> xHi = new LinkedList<>();
        LinkedList<Integer> yHi = new LinkedList<>();
        
        Iterator<Integer> xIterator = x.iterator();
        Iterator<Integer> yIterator = y.iterator();
        for (int i = 0; i < size/2; i++){
            if (i < size/2) {
                xLo.addFirst(xIterator.next());
                yLo.addFirst(yIterator.next());
            } else {
                xHi.addFirst(xIterator.next());
                yHi.addFirst(xIterator.next());
            }
        }
        
        LinkedList<Integer> xHiyHi = multiply(xHi, yHi, b);
        LinkedList<Integer> xLoyLo = multiply(xLo, yLo, b);
        //three = (xHi+xLo)*(yHi+yLo)
        LinkedList<Integer> three = multiply(add(xHi, xLo, b), add(yHi, yLo, b), b);
        
        LinkedList<Integer> four = null;//subtract(three, add(xHiyHi, xLoyLo, b), b);
        
        //shift bits n places to the left
        for (int i = 0; i < size; i++) {
            xHiyHi.addLast(0);
        }
        
        for (int i = 0; i < size/2; i++) {
            four.addLast(0);
        }        
        
        //return one + fourTwo + xLoyLo
        return add(add(xHiyHi, four, b), xLoyLo, b);
    }
}
