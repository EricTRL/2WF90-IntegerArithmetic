/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic;

/**
 * @author E.M.A. Arts (1004076)
 * @author K. Degeling (1018025)
 * @author R.M. Jonker (1011291)
 * @author S. Jacobs (1005276)
 * @author M. Schotsman (0995661)
 * 
 * @since 6 SEPTEMBER 2018
 */
public class Euclid {
    public static void main(String args[]) {

    }
    /**
     * Extended Eucledian algorithm for finding the gcd(x,y)=xa+yb
     * @param x array containing first number
     * @param y array with second number
     * @param b radix to be used in th computation
     * @return  answer[0][0, k]= gcd(x,y)
     *          answer[1][0, l]= a
     *          answer[2][0, m]= b
     *          with a,b s.t. gcd(x,y)=ax+by
     */
    public static EuclidTriple euclid(int[] x, int[] y, int[] b) {
        int[] x_prime = x;
        int[] y_prime = y;



        return new EuclidTriple(0, 0, 0);
    }

    public static class EuclidTriple {
        int d, a, b;
        public EuclidTriple(int d, int a, int b) {
            this.d = d; this.a = a; this.b = b;
        }
        public int getA() {return this.a;}
        public int getB() {return this.b;}
        public int getD() {return this.d;}
    }

}
