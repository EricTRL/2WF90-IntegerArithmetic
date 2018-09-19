package arithmetic;

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

public class EuclidResult {
    private int radix; 
    private LinkedList<Integer> x; 
    private LinkedList<Integer> y; 
    private LinkedList<Integer> answD; 
    private LinkedList<Integer> answA; 
    private LinkedList<Integer> answB; 
    
    public EuclidResult(int radix, 
                        LinkedList<Integer> x, 
                        LinkedList<Integer> y, 
                        LinkedList<Integer> answD, 
                        LinkedList<Integer> answA, 
                        LinkedList<Integer> answB) {
        this.radix = radix;
        this.x = x;
        this.y = y;
        this.answD = answD;
        this.answA = answA;
        this.answB = answB;  
    }
    
    public int getRadix() {
        return radix;
    }

    public LinkedList<Integer> getX() {
        return x;
    }

    public LinkedList<Integer> getY() {
        return y;
    }

    public LinkedList<Integer> getAnswD() {
        return answD;
    }

    public LinkedList<Integer> getAnswA() {
        return answA;
    }

    public LinkedList<Integer> getAnswB() {
        return answB;
    }
}
