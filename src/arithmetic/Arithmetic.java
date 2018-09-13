package arithmetic;

import io.*;

import java.util.LinkedList;

/**
 * This is the base class that is run.
 * It reads input with InputReader, applies any of the arithmetic functions
 * and return the output via OutputWriter
 * 
 * @author E.M.A. Arts (1004076)
 * @author K. Degeling (1018025)
 * @author R.M. Jonker (1011291)
 * @author S. Jacobs (1005276)
 * @author M. Schotsman (0995661)
 * 
 * @since 6 SEPTEMBER 2018
 */
public class Arithmetic {   
    
    public static void main(String args[]) {
        InputReader reader = new InputReader(System.in);
        System.out.println(reader.getNextComputation());
        //System.out.println(reader.getNextComputation());
        //System.out.println(reader.getNextComputation());
        //System.out.println(reader.getNextComputation());
        
    }

    public static void negative(LinkedList<Integer> answer) {
        for (int i = 0; i < answer.size(); i++) {
            answer.set(i, Math.abs(answer.get(i))*-1);
        }
    }
    public static void equal(LinkedList<Integer> x, LinkedList<Integer> y) {
        if (x.size()<y.size()) {
            for (int i = 0; i < y.size()-x.size(); i++) {
                x.addFirst(0);
            }
        } else if (x.size()>y.size()) {
            for (int i = 0; i < x.size()-y.size(); i++) {
                y.addFirst(0);
            }
        }
    }
    
}
