package arithmetic;
import java.util.LinkedList;
import java.util.Arrays;
import static arithmetic.Arithmetic.removeLeadingZeros;
import static arithmetic.Subtraction.subtract;
import static arithmetic.Addition.add;
import static arithmetic.Multiplication.multiply;


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
    
    public static void main(String[] args) {
        LinkedList<Integer> a = new LinkedList<>(Computation.stringToList("5896363941d32eccd5c"));
        LinkedList<Integer> b = new LinkedList<>(Computation.stringToList("c7eb8a91fbad0d1c1f03"));
        euclid(a,b,16,null);
    }
    
    public static LinkedList<Integer> euclid(Computation c) {
        return euclid(c.getX(), c.getY(), c.getRadix(), c);
    }
    
    public static LinkedList<Integer> euclid(LinkedList<Integer> x, LinkedList<Integer> y, int b, Computation c) {
        //Duplicate x and y
        LinkedList<Integer> inputX = new LinkedList<>(x);
        LinkedList<Integer> inputY = new LinkedList<>(y);
        
        //Declare variables
        LinkedList<Integer> a1 = new LinkedList<>(Arrays.asList(1));
        LinkedList<Integer> a2 = new LinkedList<>(Arrays.asList(0));
        LinkedList<Integer> a3 = new LinkedList<>(Arrays.asList(0));
        LinkedList<Integer> b1 = new LinkedList<>(Arrays.asList(0));
        LinkedList<Integer> b2 = new LinkedList<>(Arrays.asList(1));
        LinkedList<Integer> b3 = new LinkedList<>(Arrays.asList(0));
        LinkedList<Integer> r = new LinkedList<>();
        LinkedList<Integer> q = new LinkedList<>();
        
        //Set this boolean to true if X and Y get swapped, such that a1 and b1 are correct
        Boolean switched = false;
        
        //Make sure x > y
        if(Arithmetic.isLessThan(x,y)){
            LinkedList<Integer> dummy = new LinkedList<>(x);
            x = new LinkedList<>(y);
            y = new LinkedList<>(dummy);
            switched = true;
        }
        
        //Compute Euclids Algorithm
        while(checkNotZero(y)){
            q = divide(x,y,b);
            //Reduce
            r = subtract(x,multiply(q,y,b,null),b,null);
            a3 = subtract(a1,multiply(q,a2,b,null),b,null);
            b3 = subtract(b1,multiply(q,b2,b,null),b,null);
            //Swap
            x = y;
            y = r;
            a1 = a2;
            a2 = a3;
            b1 = b2;
            b2 = b3;
        }
        
        //Check if x and y were switched
        if(switched){
            a2 = a1;
            a1 = b1;
            b1 = a2;
        }
        
        //Remove leading zero's
        removeLeadingZeros(x);
        removeLeadingZeros(a1);
        removeLeadingZeros(b1);
        EuclidResult result = new EuclidResult(b, inputX, inputY, a1, b1, x);
        //result.print();
        
        // set answers in computation instance
        if (c != null) {
            c.setAnswA(result.getAnswA());
            c.setAnswB(result.getAnswB());
            c.setAnswD(result.getAnswD());
        }
        
        // gcd
        return result.getAnswD();
    }
    
    //Check if LinkedList is not zero
    public static boolean checkNotZero(LinkedList<Integer> y){
        for(int i =0 ; i < y.size();i++){
            if(y.get(i) != 0){
                return true;
            }
        }
        return false;
    }
    
    //We can assume that x > y
    //Basic division of 2 linked list, this returns the floor of x/y
    public static LinkedList<Integer> divide(LinkedList<Integer> x_, LinkedList<Integer> y, int b){
        //Declare variables
        LinkedList<Integer> counter = new LinkedList<>(Arrays.asList(0));
        LinkedList<Integer> one = new LinkedList<>(Arrays.asList(1));

        //Keeps subtracting while possible
        while(!Arithmetic.isLessThan(x_, y)){ // dummyX >= y
            x_ = subtract(x_,y,b,null); 
            counter = add(one,counter,b,null);  
        }     
        return counter;
    }
    
    // print linked list as number
    // eg: "list: 215643"
    public static void print(String title, LinkedList<Integer> list) {
        System.out.print(title + ": ");
        for(int i =0;i <list.size();i++){
            System.out.print(list.get(i));
        }
        System.out.println("");
    }    
}
