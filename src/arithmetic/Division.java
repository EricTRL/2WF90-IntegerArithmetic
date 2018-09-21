package arithmetic;

import java.util.Iterator;
import java.util.LinkedList;
import arithmetic.Arithmetic;
import java.util.ArrayList;
import java.util.Arrays;

public class Division {
    public static void main(String[] args) {
        LinkedList<Integer> x = new LinkedList<>(Computation.stringToList("480"));
        LinkedList<Integer> y = new LinkedList<>(Computation.stringToList("30"));
        System.out.println(dumbDivide(x,y,10,null).q + " " + dumbDivide(x,y,10,null).r);
        System.out.println("did one");
        System.out.println(verryVerrrySmartDivide(x,y,10,null).q + " " + verryVerrrySmartDivide(x,y,10,null).r);
        System.out.println("did two");
    }

    /**
     * Applies naive continual subtraction of y to compute divisor.
     * @param x numerator in base B
     * @param y denominator in base B
     * @param B base
     * @param c Computation
     * @return (q,r) with q=x/y and r=x%y
     */
    public static Arithmetic.QuoRem dumbDivide (LinkedList<Integer> x, LinkedList<Integer> y, int B, Computation c) {
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
     * @param B base
     * @param c Computation
     * @return (q,r) with q=x/y and r=x%y
     */
    public static Arithmetic.QuoRem divide (LinkedList<Integer> x, LinkedList<Integer> y, int B, Computation c) {
        int k = x.size(); int l = y.size(); int m = k-l;
        if (l>k) {return new Arithmetic.QuoRem(new LinkedList<>(), x);} // if y>x, return q=0,r=x
        LinkedList<Integer> r = new LinkedList<>(x); r.addFirst(0);
        LinkedList<Integer> q = new LinkedList<>();
        int carry;
        for (int i = 0; i < m; i++) {
            /*                      */
            q.add((r.get(i)*B+r.get(i+1))/y.getFirst());
            if (q.getLast()>=B) q.set(q.size()-1, B-1);
            /*                      */
            carry = 0;
            for (int j = l-1; j > 0; j--) {
                int tmp = r.get(i+j)-q.getLast()*y.get(j) + carry;
                carry = tmp/B; r.set(i+j, tmp%B);
            }
            r.set(i, r.get(i)+carry);
            /*                      */
            while (r.get(i)<0) {
                carry = 0;
                for (int j = l-1; j > 0; j--) {
                    int tmp = r.get(i+j)-y.get(j) + carry;
                    carry = tmp/B; r.set(i+j, tmp%B);
                }
                r.set(i, r.get(i)+carry);
                q.set(i, q.get(i)-1);
            }
            /*                      */
        }
        return new Arithmetic.QuoRem(q,r);
    }
    
    
    public static Arithmetic.QuoRem verrySmartDivide(LinkedList<Integer> u, LinkedList<Integer> v, int b) {
        //nonnegative u,v
        //no leading zeros in v
        //v != 0
        Arithmetic.removeLeadingZeros(v);
        Arithmetic.removeLeadingZeros(u);
        
        int m = u.size() - v.size();
        int n = v.size();
        
        LinkedList<Integer> d;
        
        int iD = (b-1)/v.getFirst(); //integer division = floor
        if (iD != 1) {
            d = new LinkedList<>(Arrays.asList(iD));
            v = Karatsuba.karatsuba(v, d, b, null, 1);
            u = Karatsuba.karatsuba(u, d, b, null, 1);
        } else {
            u.addFirst(0);
        }        
        
        //convert to arraylist
        ArrayList<Integer> uArr = new ArrayList<>(u);
        ArrayList<Integer> vArr = new ArrayList<>(v);
        
        for (int j = m; true; j++) {
            //cacluate
            int ujnB = u.get(j+n)*b;
            
            int ujn = v.get(j+n-1);
            
            int qHat = (ujnB + ujn)/v.get(n-1);
            int rHat = (ujnB + ujn) % v.get(n-1);
            
            
            while (rHat < b) {
                if (qHat == b || qHat*v.get(n-2) > b*rHat + u.get(j+n-2)) {
                    qHat--;
                    rHat += v.get(n-1);
                }
            }
            
            LinkedList<Integer> result = new LinkedList<>();
            for (int i = n; i >= 0; i--) {
                result.add(u.get(i+j));
            }
            result = Subtraction.subtract(result, Karatsuba.karatsuba(v, new LinkedList<>(Arrays.asList(qHat)), b, null, 1), b, null);         
            
            boolean negative = Arithmetic.isNegative(result);
            if (negative) {
                //fix stuff
                Addition.add(result, null /*b^n+1*/, b, null);
            }
            
            for (int i = 0; i <= n; i++) {
                u.set(i+j, result.get(i));
            }
            
            int qj = qHat;
            if (negative) {
                qj--;
                
            }
            
            return null;
        }
        
        //return null;
    }
    
    public static Arithmetic.QuoRem verryVerrrySmartDivide(LinkedList<Integer> x, LinkedList<Integer> y, int b, Computation c) {
        Arithmetic.removeLeadingZeros(x); Arithmetic.removeLeadingZeros(y);
        int k = x.size();
        int l = y.size();
        
        if (l > k) {
            return new Arithmetic.QuoRem(new LinkedList<>(Arrays.asList(0)), x);
        }
        
        //a, b must be positive
        //k >= l
        
        //q has at most m = k-l+1 base-b digits
        
        LinkedList<Integer> q = new LinkedList<>();
        LinkedList<Integer> r = new LinkedList<>(x); //LINE 1 ALGO
        r.addFirst(0); //LINE 2 ALGO
        
        int carry;
        int tmp;
        
        for(int i = k-l; i >= 0; i--) { //LINE 3 AlGO
            int numberToAddInQ = (r.get(i+l)*b+r.get(i+l-1))/y.get(l-1); //LINE 4 ALGO. Breaks if y.get(l-1) is 0, in other words when the last digit of y is 0
            if (numberToAddInQ >= b) { //LINE 5 ALGO
                numberToAddInQ = b-1; //STILL LINE 5 ALGO
            }
            q.addFirst(numberToAddInQ); // Here, the result is actually put in q, as was suggested in LINE 4 ALGO
            
            carry = 0; //LINE 6 ALGO
            
            for (int j = 0; j < l; j++) { //LINE 7 ALGO
                tmp = r.get(i+j) - q.get(0)*y.get(j) + carry; //LINE 8 ALGO
                carry = tmp/b; //LINE 9 ALGO (QuoRem puts quotient in carry, remainder in r)
                r.set(i+j, tmp%b); //STILL LINE 9 ALGO
            }
            
            r.set(i+l, r.get(i+l)+carry); //LINE 10 ALGO
            
            /* This code was an attempt to use existing arithmetic functions for r = r-qi*b. Not sure if functional.
            //System.out.println(r);
            LinkedList<Integer> qiList = new LinkedList<>(Arrays.asList(q.get(0)));
            LinkedList<Integer> qiListTimesY = Karatsuba.karatsuba(qiList, y, b, null, 1);
            System.out.println(Karatsuba.karatsuba(qiList, y, b, null, 1) + " " + y);
            Arithmetic.removeLeadingZeros(y);
            r = Subtraction.subtract(r, qiListTimesY, b, null);
            //System.out.println(qiList + " " + qiListTimesY + " " + y + " " + r);
            //Fix the missing leading zeros (removed by arithmetic functions)
            while (r.size() < x.size()+1) {
                r.addFirst(0);
            }
            */
            
            while (r.get(i+l) < 0) { //LINE 11 ALGO
                carry = 0; //LINE 12 ALGO
                for (int j = 0; j < l; j++) { //LINE 13 ALGO
                    tmp = r.get(i+j) + y.get(i) + carry; //LINE 14 ALGO
                    carry = tmp/b; //LINE 15 ALGO
                    r.set(i+j, tmp%b); //STILL LINE 15 ALGO
                }
                r.set(i+l, r.get(i+l)+carry); //LINE 16 ALGO
                q.set(0, q.get(0)-1); //LINE 17 ALGO
            }
        }
        Arithmetic.removeLeadingZeros(r); Arithmetic.removeLeadingZeros(q);
        return new Arithmetic.QuoRem(q,r); //LINE 18 ALGO
    }
    
}
