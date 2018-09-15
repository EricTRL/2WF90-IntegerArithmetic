package arithmetic;
import java.util.LinkedList;
import java.util.Arrays;
import static arithmetic.Subtraction.subtract;


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
        LinkedList<Integer> a = new LinkedList<>(Arrays.asList(1,2));
        LinkedList<Integer> b = new LinkedList<>(Arrays.asList(2,3));
        
        for (int x : euclid(a, b, 10)) {
            System.out.print(x);
        }
    }
    public static int[] euclid(LinkedList<Integer> x, LinkedList<Integer> y, int b) {
        LinkedList<Integer> a1 = new LinkedList<>(Arrays.asList(1));
        LinkedList<Integer> a2 = new LinkedList<>(Arrays.asList(0));
        LinkedList<Integer> b1 = new LinkedList<>(Arrays.asList(0));
        LinkedList<Integer> b2 = new LinkedList<>(Arrays.asList(1));
        
        if(!isGreaterThan(x,y)){
            LinkedList<Integer> dummy = (LinkedList) x.clone();
            x = (LinkedList) y.clone();
            y = (LinkedList) dummy.clone();
        }
        
        while(checkNotZero(y)){
            
        }
        
        
        return null;
    }
    
    public static boolean isGreaterThan(LinkedList<Integer> x, LinkedList<Integer> y){
        for(int i = 0; i < x.size();i++){
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
        int q = 0;
        while( isGreaterThan(x,y)){
            x = subtract(x,y,b);
            q++;
        }
        LinkedList<Integer> power = new LinkedList<>(Arrays.asList(1));
        LinkedList<Integer> radix = new LinkedList<>(Arrays.asList(b));
        LinkedList<Integer> q_ = new LinkedList<>();
        
        int counter = 0;
        while(power.get(0) < q){
            power.set(0,multiplicate(power,radix,b));
            counter++;
        }
        q_.set(counter-1,)
    }
    
}
