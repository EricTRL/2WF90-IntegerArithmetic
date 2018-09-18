package arithmetic;
import java.util.LinkedList;
import java.util.Arrays;
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

//GreaterOrEqual doesnt work
public class Euclid {
    public static void main(String[] args) {
        LinkedList<Integer> a = new LinkedList<>(Computation.stringToList("100"));
        LinkedList<Integer> b = new LinkedList<>(Computation.stringToList("20"));
        System.out.println(euclid(a,b,10));
    }
    public static LinkedList<Integer> euclid(LinkedList<Integer> x, LinkedList<Integer> y, int b) {
        LinkedList<Integer> a1 = new LinkedList<>(Arrays.asList(1));
        LinkedList<Integer> a2 = new LinkedList<>(Arrays.asList(0));
        LinkedList<Integer> a3 = new LinkedList<>(Arrays.asList(0));
        LinkedList<Integer> b1 = new LinkedList<>(Arrays.asList(0));
        LinkedList<Integer> b2 = new LinkedList<>(Arrays.asList(1));
        LinkedList<Integer> b3 = new LinkedList<>(Arrays.asList(0));
        
        if(Arithmetic.isLessThan(x,y)){
            LinkedList<Integer> dummy = (LinkedList) x.clone();
            x = (LinkedList) y.clone();
            y = (LinkedList) dummy.clone();
        }
        LinkedList<Integer> r = new LinkedList<>();
        LinkedList<Integer> q = new LinkedList<>();
        
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
        System.out.println("X");
        for(int i = 0; i < x.size();i++){
            System.out.print(x.get(i));
        }
        System.out.println("");
        System.out.println("Y");
        for(int i = 0; i < y.size();i++){
            System.out.print(y.get(i));
        }
        System.out.println("");
        System.out.println("A1");
        for(int i = 0; i < a1.size();i++){
            System.out.print(a1.get(i));
        }
        System.out.println("");
        System.out.println("B1");
        for(int i = 0; i < b1.size();i++){
            System.out.print(b1.get(i));
        }
        System.out.println("");
        
        return b1;
    }
    
    public static boolean isGreaterOrEqual(LinkedList<Integer> x, LinkedList<Integer> y){
        int size = Math.min(x.size(),y.size());
        for(int i = 0; i < size;i++){
            if(x.get(i) > y.get(i)){
                return true;
            }else if(y.get(i) > x.get(i)){
                return false;
            }
        }
        //If equal return true
        return true;
    }
    
    public static boolean checkNotZero(LinkedList<Integer> y){
        for(int i =0 ; i < y.size();i++){
            if(y.get(i) != 0){
                return true;
            }
        }
        return false;
    }
    
    //We can assume that x > y
    public static LinkedList<Integer> divide(LinkedList<Integer> x, LinkedList<Integer> y, int b){
        LinkedList<Integer> counter = new LinkedList<>(Arrays.asList(0));
        LinkedList<Integer> one = new LinkedList<>(Arrays.asList(1));
        LinkedList<Integer> dummyX = x;

        while(!Arithmetic.isLessThan(dummyX, y)){ // dummyX >= y
            dummyX = subtract(dummyX,y,b,null);
            counter = add(one,counter,b,null);
        }        
        return counter;
    }
    
}
