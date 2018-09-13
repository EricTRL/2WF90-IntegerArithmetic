/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic;

/**
 * Class that adds two large (integer) numbers x,y with radix b.
 */

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
public class Addition {
    public static void main(String[] args) {
        LinkedList<Integer> a = new LinkedList<>();
        a.add(1); a.add(7);
        LinkedList<Integer> b = new LinkedList<>();
        b.add(-2); b.add(-4);
        for(Integer x : add(a, b, 10)) {
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
     * @return x + y in radix b
     */
    public static LinkedList<Integer> add (LinkedList<Integer> x, LinkedList<Integer> y, int b) {
        equal(x, y);
        int carry = 0; //optional carry we need to add.
        LinkedList<Integer> answer = new LinkedList<>();
        Iterator<Integer> it1 = x.descendingIterator(); Iterator<Integer> it2 = y.descendingIterator();

        while (it1.hasNext() && it2.hasNext()) {
            int x_i = it1.next(); int y_i = it2.next();
            answer.addFirst((x_i+y_i+carry)%b);

            if (Math.abs(x_i+y_i+carry)>=b) {
                carry = (x_i+y_i+carry)/b;
            } else {
                carry = 0;
            }
        }
        if (answer.getFirst()<0) {
            negative(answer);
        }
        return answer;
    }
    private static void negative(LinkedList<Integer> answer) {
        for (int i = 0; i < answer.size(); i++) {
            answer.set(i, Math.abs(answer.get(i))*-1);
        }
    }
    private static void equal(LinkedList<Integer> x, LinkedList<Integer> y) {
        if (x.size()<y.size()) {
            for (int i = 0; i < y.size()-x.size(); i++) {
                x.addLast(0);
            }
        } else if (x.size()>y.size()) {
            for (int i = 0; i < x.size()-y.size(); i++) {
                y.addLast(0);
            }
        }
    }
    
}
