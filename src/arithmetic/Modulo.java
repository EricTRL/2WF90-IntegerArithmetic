/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * This is the class handling modulo arithmetic
 * 
 * @author E.M.A. Arts (1004076)
 * @author K. Degeling (1018025)
 * @author R.M. Jonker (1011291)
 * @author S. Jacobs (1005276)
 * @author M. Schotsman (0995661)
 * 
 * @since 18 SEPTEMBER 2018
 */
public class Modulo {
    public static void main(String[] args) {
        LinkedList<Integer> a = new LinkedList<>(Arrays.asList(6));
        LinkedList<Integer> m = new LinkedList<>(Arrays.asList(2));
        System.out.println(modulo(a, m, 10));
    }
    
    public static LinkedList<Integer> modulo(Computation c) {
        return modulo(c.getX(), c.getM(), c.getRadix());
    }
    /**
     * Multiply large numbers in radix b
     *
     * @param x array with first number
     * @param y array with second number
     * @param b radix to be used
     * @param computation Computation to increment [count-mul] and [count-add] of (or null if we don't care)
     * @pre x.length==y.length
     * @return x*y in radix b
     */
    public static LinkedList<Integer> modulo (LinkedList<Integer> x, LinkedList<Integer> m, int b) {
        for (int i = x.size(); i > 0; i--) {
            
        }
        Arithmetic.removeLeadingZeros(answer);
        return answer;
    }
}
