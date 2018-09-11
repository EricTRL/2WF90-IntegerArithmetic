/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic;

/**
 * Class that adds two large (integer) numbers x,y with radix b.
 */

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
        int[] a = {-2, -5, 0};
        int[] b = {2, 5, 0};
        for (int x : add(a, b, 10)) {
            System.out.print(x+" ");
        }
    }
    /**
     * Adds to large numbers in radix b
     *
     * @param x array with first number
     * @param y array with second number
     * @param b radix to be used
     * @pre x.length==y.length
     * @return x + y in radix b
     */
    public static int[] add (int[] x, int[] y, int b) {
        int carry = 0; //optional carry we need to add.
        int[] answer = new int[x.length+1];
        for (int i = answer.length-1; i > 0; i--) {
            answer[i] = (x[i-1] + y[i-1] + carry)%b; //compute new value
            //compute carry
            if (Math.abs(x[i-1]+y[i-1]+carry)>=b) {
                carry = (x[i-1]+y[i-1]+carry)/b;
            } else {
                carry = 0;
            }
        }
        answer[0] = carry;
        return answer;
    }
    
}
