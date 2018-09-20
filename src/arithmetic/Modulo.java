package arithmetic;

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
        LinkedList<Integer> a = new LinkedList<>(Computation.stringToList("138"));
        LinkedList<Integer> m = new LinkedList<>(Computation.stringToList("47"));
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
        return Division.dumbDivide(x, m, b, null).r;
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

    
    public static LinkedList<Integer> modularInversion(Computation c) {
        return modularInversion(c.getX(), c.getM(), c.getRadix());
    }
    
    /**
     * Modular inversion of number x
     * @param x number
     * @param m modulo
     * @param b base
     * @return x^{-1}
     */
    public static LinkedList<Integer> modularInversion(LinkedList<Integer> x, LinkedList<Integer> m, int b) {
        LinkedList<Integer> x_prime = x; LinkedList<Integer> m_prime = m;
        int x_1 = 1; int x_2 = 0;
        while (Arithmetic.isPositive(m_prime)) {
            LinkedList<Integer> q = Division.dumbDivide(x_prime, m_prime, b, null).q;
            LinkedList<Integer> r = Subtraction.subtract(x_prime, Karatsuba.karatsuba(q, m_prime, b, null, 1), b, null);
            x_prime = m_prime; m_prime = r;
        }
        return x_prime;
    }
}
