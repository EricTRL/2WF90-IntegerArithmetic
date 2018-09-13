package arithmetic;


import static arithmetic.Addition.add;
import static arithmetic.Multiplication.multiply;
import static arithmetic.Subtraction.subtract;
import java.util.Arrays;

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
        int[] a = {9, 0, 1, 8};
        int[] b = a;
        for (int x : karatsuba(a, b, 10)) {
            System.out.print(x);
        }
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
    public static int[] karatsuba (int[] x, int[] y, int b) {
        if (x.length % 2 != 0){
            //add leading 0
            int[] newX = new int[x.length + 1];
            int[] newY = new int[y.length + 1];
            
            newX[0] = 0;
            newY[0] = 0;
            for(int i=1; i < newX.length; i++) {
                newX[i] = x[i-1];
                newY[i] = y[i-1];
            }
            x = newX;
            y = newY;
        }
        //x.length = even
        
        int xLoIndex = (x.length/2)-1;
        int yLoIndex = (y.length/2)-1;
        
        int[] xLo = new int[x.length/2];
        int[] yLo = new int[y.length/2];
        int[] xHi = new int[x.length/2];
        int[] yHi = new int[y.length/2];
        
        for (int i = 0; i < x.length/2; i++){
            xHi[i] = x[i];
            yHi[i] = y[i];
            xLo[i] = x[i+x.length/2];
            yLo[i] = y[i+y.length/2];
        }
        
        int[] xHiyHi = multiply(xHi, yHi, b);
        int[] xLoyLo = multiply(xLo, yLo, b);
        //three = (xHi+xLo)*(yHi+yLo)
        int[] three = multiply(add(xHi, xLo, b), add(yHi, yLo, b), b);
        
        int[] four = subtract(three, add(xHiyHi, xLoyLo, b), b);
        
        System.out.println(Arrays.toString(xHiyHi));
        
        int[] one = new int[xHiyHi.length + x.length];
        for(int i=0; i < xHiyHi.length; i++) {
            one[i] = xHiyHi[i];
        }
        System.out.println(Arrays.toString(one));
        
        int[] fourTwo = new int[four.length + x.length/2];
        for(int i=0; i < four.length; i++) {
            fourTwo[i] = four[i];
        }
        
        int[] newFourTwo = new int[one.length];
        for(int i=0; i < fourTwo.length; i++) {
            newFourTwo[i + (newFourTwo.length - fourTwo.length)] = fourTwo[i];
        }
        
        int[] newxLoyLo = new int[one.length];
        for(int i=0; i < xLoyLo.length; i++) {
            newxLoyLo[i + (newxLoyLo.length - xLoyLo.length)] = xLoyLo[i];
        }
        System.out.println(one.length == newFourTwo.length);
        System.out.println(one.length == newxLoyLo.length);        
        
        //return one + fourTwo + xLoyLo
        return add(add(one, newFourTwo, b), newxLoyLo, b);
    }
}
