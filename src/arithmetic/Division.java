package arithmetic;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class Division {
    public static void main(String[] args) throws Exception {
        /*
        Random r = new Random();
        int count = 0;
        while(true) {
            int intX = r.nextInt(9999) + 1001;
            int intY = r.nextInt(999) + 1;
            if (intY > intX) {
                int temp = intX;
                intX = intY;
                intY = temp;
            }
            
            LinkedList<Integer> x = new LinkedList<>(Computation.stringToList(Integer.toString(intX)));
            LinkedList<Integer> y = new LinkedList<>(Computation.stringToList(Integer.toString(intY)));
            Arithmetic.QuoRem smartDiv = divide(x,y,10,null);
            
            if (!Arithmetic.isEqual(Addition.add(Karatsuba.karatsuba(y, smartDiv.q, 10, null, 1), smartDiv.r, 10, null), x)) {
                System.err.println("Ran " + count + " tests until exception...");
                throw new Exception("got " + smartDiv + " for x = " + x + ", y = " + y);
            }
            
            if (count%1000 == 0) {
                System.out.println("ran " + count + " tests so far");
            }
            count++;
        }     
        */
        ///*
        LinkedList<Integer> x = new LinkedList<>(Computation.stringToList(Integer.toString(1380)));
        LinkedList<Integer> y = new LinkedList<>(Computation.stringToList(Integer.toString(470)));
        //System.out.println("dumbDivide: " + dumbDivide(x, y, 10));
        System.out.println(divide(x, y, 10));
        //*/
    }

    /**
     * Applies naive continual subtraction of y to compute divisor.
     * @param x numerator in base B
     * @param y denominator in base B
     * @param B base
     * @return (q,r) with q=x/y and r=x%y
     */
    public static Arithmetic.QuoRem dumbDivide(LinkedList<Integer> x, LinkedList<Integer> y, int B) {
        LinkedList<Integer> q = new LinkedList<>(); q.add(0);
        LinkedList<Integer> one = new LinkedList<>(); one.add(1);
        LinkedList<Integer> r = new LinkedList<>(x);
        while (!Arithmetic.isLessThan(r,y)) {
            r = Subtraction.subtract(r, y, B, null);
            q = Addition.add(one, q, B, null);
        }
        Arithmetic.removeLeadingZeros(r); Arithmetic.removeLeadingZeros(q);
        return new Arithmetic.QuoRem(q,r);
    }
    
    /**
     * Applies long division on numbers x and y
     * @param x numerator in base B
     * @param y denominator in base B
     * @param b base
     * @pre x >= 0 && y != 0
     * @return (q,r) with q=x/y and r=x%y
     */
    public static Arithmetic.QuoRem divide(LinkedList<Integer> x, LinkedList<Integer> y, int b) {
        //the first 'bit' of y cannot be 0. It doesn't matter for x, but it reduces the amount of computations we have to do, so we do it anyways.
        Arithmetic.removeLeadingZeros(x); Arithmetic.removeLeadingZeros(y);
        int k = x.size();
        int l = y.size();
        
        //if one number is longer than the other one, it must be bigger
        //Moreover, long division will result in q = 0 and r = x
        if (l > k) {
            return new Arithmetic.QuoRem(new LinkedList<>(Arrays.asList(0)), x);
        }
        
        //initialize quotient and remainder
        LinkedList<Integer> q = new LinkedList<>();
        //arraylist as we index this list often (which is slow in LinkedLists)
        ArrayList<Integer> r = new ArrayList<>(x); //LINE 1 ALGO
        r.add(0, 0); //LINE 2 ALGO;
        
        //loop over the high-order words that x has but y does not have + 1
        //E.g. x = 210; y = 40 -> loop over 2 and 1
        for(int i = 0; i < k - l + 1; i++) { //LINE 3 AlGO
            q.addLast((r.get(i)*b+r.get(i+1))/y.getFirst()); //LINE 4 ALGO
            if (q.getLast() >= b) { //LINE 5 ALGO
                q.removeLast();
                q.addLast(b-1);
            }

            int carry = 0; //LINE 6 ALGO
            
            //loop
            {
                //using an iterator to avoid the costly y.get(j)
                Iterator<Integer> yIt = y.descendingIterator();
                int j = l-1;
                
                //for (int j = l-1; j >= 0; j--) 
                while (yIt.hasNext()) { //LINE 7 ALGO
                    int jthElem = yIt.next(); //jthElem = y.get(j)
                    int tmp = r.get(i+j+1) - q.getLast()*jthElem + carry; //LINE 8 ALGO
                    carry = Math.floorDiv(tmp, b); //LINE 9 ALGO (QuoRem puts quotient in carry, remainder in r)
                    r.set(i+j+1, Math.floorMod(tmp, b)); //STILL LINE 9 ALGO
                    j--;
                }
            }
            r.set(i, r.get(i) + carry); //LINE 10 ALGO
            
            while (r.get(i) < 0) { //LINE 11 ALGO
                carry = 0; //LINE 12 ALGO
                
                //using an iterator to avoid the costly y.get(j)
                Iterator<Integer> yIt = y.descendingIterator();                
                int j = l - 1;
                
                //for (int j = l-1; j >= 0; j--)
                while (yIt.hasNext()) { //LINE 13 ALGO
                    int jthElem = yIt.next(); //jthElem = y.get(j)
                    int tmp = r.get(i+j+1) + jthElem + carry; //LINE 14 ALGO
                    carry = Math.floorDiv(tmp,b); //LINE 15 ALGO
                    r.set(i+j+1, Math.floorMod(tmp, b)); //STILL LINE 15 ALGO
                    j--;
                }
                r.set(i, r.get(i) + carry); //LINE 16 ALGO
                q.addLast(q.removeLast() - 1);
            }
        }
        //convert r back to a LinkedList (our expected format)
        LinkedList<Integer> rLinked = new LinkedList<>(r);
        Arithmetic.removeLeadingZeros(rLinked); Arithmetic.removeLeadingZeros(q);
        return new Arithmetic.QuoRem(q, rLinked); //LINE 18 ALGO
    }
    
}
