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
public class Subtraction {
    public static void main(String[] args) {
        int[] a = {-8, 0}; int[] b = {-5, -1};
        int[] x = subtract(a, b, 10);
        for (int y : x) {
            System.out.print(y+" ");
        }
    }
    /**
     * subtraction of two large integers x and y
     *
     * @param x array containing the words of a large integer x
     * @param y array containing the words of large integer y
     * @param b radix to be used
     * @pre x.length==y.length
     * @post x - y radix b
     */
    public static int[] subtract(int[] x, int[] y, int b) {
        if (lessThan(x, y)) { // x < y
            int[] answer = subtract(y, x, b);
            for (int i = 0; i < answer.length; i++) {
                answer[i] = -1*answer[i];
            }
            return answer;
        }
        //Assumption x > y
        int carry = 0;
        int[] answer = new int[x.length+1];
        for (int i = answer.length-1; i > 0; i--) {
            answer[i] = x[i-1] - y[i-1] - carry;
            if (answer[i]<0) {
                answer[i] = answer[i]+b;
                carry = 1;
            } else if (answer[i]>=b) {
                answer[i] = answer[i]%b;
                carry = 1;
            } else {
                carry = 0;
            }
        }
        answer[0] = carry;
        return answer;
    }

    /**
     * Method that compares x and y and outputs true if x < y
     * @param x
     * @param y
     * @pre x.length == y.length
     * @return x < y
     */
    public static boolean lessThan(int[] x, int[] y) {
        for (int i = 0; i < x.length; i++) {
            if (y[i]<x[i]) {
                return false;
            } else if (x[i]<y[i]) {
                return true;
            }
        }
        return false; //x==y, thus we output false
    }



}
