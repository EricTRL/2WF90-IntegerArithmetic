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

    private LinkedList<Integer> inputX; 
    private LinkedList<Integer> inputY; 
    private LinkedList<Integer> answD; 
    private LinkedList<Integer> answA; 
    private LinkedList<Integer> answB; 
    
    public EuclidResult(int radix, 
                        LinkedList<Integer> inputX, 
                        LinkedList<Integer> inputY, 
                        LinkedList<Integer> answD, 
                        LinkedList<Integer> answA, 
                        LinkedList<Integer> answB) {
        this.radix = radix;
        this.inputX = inputX;
        this.inputY = inputY;
        this.answD = answD;
        this.answA = answA;
        this.answB = answB;  
    }
    
    public int getRadix() {
        return radix;
    }

    public LinkedList<Integer> getInputX() {
        return inputX;
    }

    public LinkedList<Integer> getInputY() {
        return inputY;
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
    
    // print the result
    public void print() {
        Euclid.print("[x]", inputX);
        Euclid.print("[y]", inputY);
        Euclid.print("[answ-d]", answD);
        Euclid.print("[answ-a]", answA);
        Euclid.print("[answ-b]", answB);
    }
}
