package arithmetic;
import java.util.LinkedList;
import java.util.Arrays;

/**
 * @author E.M.A. Arts (1004076)
 * @author K. Degeling (1018025)
 * @author R.M. Jonker (1011291)
 * @author S. Jacobs (1005276)
 * @author M. Schotsman (0995661)
 * 
 * @since 6 SEPTEMBER 2018
 */
public class Multiplication {
    public static void main(String[] args) {
        LinkedList<Integer> a = new LinkedList<>(Arrays.asList(6));
        LinkedList<Integer> b = new LinkedList<>(Arrays.asList(8));
        System.out.println(multiply(a, b, 10, null));
    }
    
    public static LinkedList<Integer> multiply(Computation c) {
        return multiply(c.getX(), c.getY(), c.getRadix(), c);
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
    public static LinkedList<Integer> multiply (LinkedList<Integer> x, LinkedList<Integer> y, int b, Computation computation) {
        Arithmetic.makeLengthsEqual(x, y);
        int carry = 0; //optional carry we need to add.
        int zerosAdded = 0; //amount of zeros added at the end (increases by one every iteration of i)
        LinkedList<Integer> answer = new LinkedList<>(Arrays.asList(0));
        LinkedList<Integer> answerToAdd = new LinkedList<>();
        //equal(x,y); //Make the numbers of the same size by adding 0's to the smaller number
        for (int i = y.size(); i > 0; i--) {
            for (int j = x.size(); j > 0; j--) {
                //answerToAdd.set((x.size()*2)-(x.size()-j)-zerosAdded-1+arrayExtendedByAmount, (x.get(j-1)*y.get(i-1) + carry)%b); //compute new value
                answerToAdd.addFirst((x.get(j-1)*y.get(i-1) + carry)%b); // insert result of multiplication at beginning of list
                //compute carry
                if (Math.abs(x.get(j-1)*y.get(i-1)+carry)>=b) {
                    carry = (x.get(j-1)*y.get(i-1)+carry)/b;
                } else {
                    carry = 0;
                }
                computation.changeCountMultiply(1);
                //carryPlacementCount = (x.size()*2)-(x.size()-j)-zerosAdded-2+arrayExtendedByAmount; //Keep track of where to place carry (only needed after j for-loop)
            }
            
            //answerToAdd.set(carryPlacementCount, carry); //Place carry
            if (carry != 0) {
                answerToAdd.addFirst(carry);
            }
            carry = 0; //reset carry
            for (int z = zerosAdded; z > 0; z--) {
                answerToAdd.addLast(0);
            }
            zerosAdded++; //Increase zerosAdded counter for next iteration

            answer = Addition.add(answer, answerToAdd, b, computation); //add current result to total result
            answerToAdd = new LinkedList<>(); //reset answerToAdd array
            
        }
        Arithmetic.removeLeadingZeros(answer);
        return answer;
    }
    
}
