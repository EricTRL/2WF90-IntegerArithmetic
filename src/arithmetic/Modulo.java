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
     * Function that returns x (mod m)
     * @param x number to be reduced
     * @param m modulo
     * @param b base
     * @return x (mod m)
     */
    public static LinkedList<Integer> modulo (LinkedList<Integer> x, LinkedList<Integer> m, int b) {
        for (int i = x.size(); i > 0; i--) {
            
        }
        return null;
    }

    /**
     * Modular addition of numbers x,y
     * @param x number x
     * @param y number y
     * @param b base
     * @param m the modulo
     * @return x+y (mod m)
     */
    public static LinkedList<Integer> addModulo (LinkedList<Integer> x, LinkedList<Integer> y, int b, LinkedList<Integer> m) {
        x = modulo(x, m, b); y = modulo(y, m, b); //We reduce x and y
        LinkedList<Integer> z = Addition.add(x, y, b, null);
        if (Arithmetic.isLessThan(z,m)) {
            return z;
        } else {
            return Subtraction.subtract(z,m,b, null);
        }
    }

    /**
     * Modular subtraction of x - y
     * @param x number x
     * @param y number y
     * @param m modulus
     * @param b base
     * @return x-y (mod m)
     */
    public static LinkedList<Integer> subtractModulo (LinkedList<Integer> x, LinkedList<Integer> y, LinkedList<Integer>m, int b) {
        x = modulo(x, m, b); y = modulo(y, m ,b); //Reduce x and y
        LinkedList<Integer> z = Subtraction.subtract(x, y, b, null);
        if (Arithmetic.isPositive(z)) {
            return z;
        } else {
            return Addition.add(x, m, b, null);
        }
    }

    /**
     * Naive Modular multiplication
     * @param x number x
     * @param y number y
     * @param m modulo
     * @param b base
     * @return (x*y)(mod m)
     */
    public static LinkedList<Integer> multiplyModulo (LinkedList<Integer> x, LinkedList<Integer> y, LinkedList<Integer> m, int b) {
        x = modulo(x, m, b); y = modulo(y, m, b); //Reduce x and y
        LinkedList<Integer> z = Karatsuba.karatsuba(x, y, b, null, 1);
        return modulo(z, m, b);
    }
}
