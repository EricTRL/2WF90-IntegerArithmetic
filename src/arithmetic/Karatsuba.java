package arithmetic;

import java.util.Iterator;
import java.util.LinkedList;
import static arithmetic.Addition.add;
import static arithmetic.Multiplication.multiply;
import static arithmetic.Subtraction.subtract;

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
        LinkedList<Integer> a = new LinkedList<>(Computation.stringToList("10"));
        LinkedList<Integer> b = new LinkedList<>(Computation.stringToList("50"));
        System.out.println(karatsuba(a, b, 10, null, 2));
    }
    
    /**
     * Multiplies two large numbers stored in a Computation
     * 
     * @param c Computation to read the values from
     * @return karatsuba(c.getX(), c.getY(), c.getRadix(), c, 1);
     */
    public static LinkedList<Integer> karatsuba(Computation c) {
        return karatsuba(c.getX(), c.getY(), c.getRadix(), c, 1);
    }
    
    /**
     * Multiplies two large numbers in radix b
     *
     * @param x array with first number
     * @param y array with second number
     * @param b radix to be used
     * @param computation Computation to increment [count-mul] and [count-add] of (or null if we don't care)
     * @param minBits The number of bits at which to not apply karatsuba anymore
     * @pre x != null && y != null && b \in N && b <= 16
     * @modifies computation.countMul && computation.countAdd
     * @return The result of x*y using Karatsuba Multiplication
     */
    public static LinkedList<Integer> karatsuba(LinkedList<Integer> x, LinkedList<Integer> y, int b, Computation computation, int minBits) {
        //ensure that x and y can be split up in two even parts (by adding
        //leading 0s)
        if ((x.size() & 1) != 0) {
            x.addFirst(0);
        }//x.size() = even
        
        if ((y.size() & 1) != 0) {
            y.addFirst(0);
        }//y.size() = even

        //ensure that x.size() == y.size()
        Arithmetic.makeLengthsEqual(x, y);
           
        //cache the size (we use it often)
        int size = x.size();
        
        //store the lower and higher order words
        LinkedList<Integer> xLo = new LinkedList<>();
        LinkedList<Integer> yLo = new LinkedList<>();
        LinkedList<Integer> xHi = new LinkedList<>();
        LinkedList<Integer> yHi = new LinkedList<>();
        
        Iterator<Integer> xIterator = x.iterator();
        Iterator<Integer> yIterator = y.iterator();
        for (int i = 0; i < size; i++){
            if (i < size/2) {
                //first half of the numbers are the high-order words
                xHi.addLast(xIterator.next());
                yHi.addLast(yIterator.next());
            } else {
                //second half of the numbers are the low-order words
                xLo.addLast(xIterator.next());
                yLo.addLast(yIterator.next());
            }
        }
        //high and low order words-variables are now set up
        
        //result of several steps of multiplications and additions
        LinkedList<Integer> xHiyHi;
        LinkedList<Integer> xLoyLo;
        LinkedList<Integer> orderMult;
        
        //do the calculations (recursively)
        if (size/2 <= Math.max(minBits, 1)) {
            //end the recursion; apply primary school calculation
            //System.out.println("\t primary school ("+ size/2 +")");
            
            //xHiyHi = xHi*yHi
            xHiyHi = multiply(xHi, yHi, b, computation);
            //xLoyLo = xLo*yLo
            xLoyLo = multiply(xLo, yLo, b, computation);
            //orderMult = (xHi+xLo)*(yHi+yLo)
            orderMult = multiply(add(xHi, xLo, b, computation), add(yHi, yLo, b, computation), b, computation);
        } else {
            //continue the recursion; apply karatsuba (again)
            //System.out.println("\t karatsuba ("+size/2+")");
            
            //xHiyHi = xHi*yHi
            xHiyHi = karatsuba(xHi, yHi, b, computation, minBits);
            //xLoyLo = xLo*yLo
            xLoyLo = karatsuba(xLo, yLo, b, computation, minBits);
            //orderMult = (xHi+xLo)*(yHi+yLo)
            orderMult = karatsuba(add(xHi, xLo, b, computation), add(yHi, yLo, b, computation), b, computation, minBits);            
        }
        
        //xHiyLo_plus_xLoyHi = orderMult - (xHiyHi + xLoyLo)
        LinkedList<Integer> xHiyLo_plus_xLoyHi = subtract(orderMult, add(xHiyHi, xLoyLo, b, computation), b, computation);
        
        //char[] newFourTwo = new char[] {'s','o','r','r','y',' ','m','a','t','t','i','j','s'};
        
        //shift higher order bits 'size' places to the left
        for (int i = 0; i < size; i++) {
            xHiyHi.addLast(0);
        }
        //shift bits 'size/2' places to the left
        for (int i = 0; i < size/2; i++) {
            xHiyLo_plus_xLoyHi.addLast(0);
        }
        
        //return xHiyHi + xHiyLo_plus_xLoyHi + xLoyLo
        LinkedList<Integer> answer = add(add(xHiyHi, xHiyLo_plus_xLoyHi, b, computation), xLoyLo, b, computation);
        Arithmetic.removeLeadingZeros(answer);
        
        //return the final answer (without leading 0s)
        return answer;
    }
}