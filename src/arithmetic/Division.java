package arithmetic;

import java.util.Iterator;
import java.util.LinkedList;
import arithmetic.Arithmetic;
import java.util.ArrayList;
import java.util.Arrays;

public class Division {
    public static void main(String[] args) {
        LinkedList<Integer> x = new LinkedList<>(Computation.stringToList("856486231"));
        LinkedList<Integer> y = new LinkedList<>(Computation.stringToList("65454"));
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
        System.out.println(x + " " + x.size());
        System.out.println(y + " " + y.size());
        int k = x.size();
        int l = y.size();
        
        if (l > k) {
            return new Arithmetic.QuoRem(new LinkedList<>(Arrays.asList(0)), x);
        }
        
        //a, b must be positive
        //k >= l
        
        //q has at most m = k-l+1 base-b digits
        
        LinkedList<Integer> q = new LinkedList<>();
        LinkedList<Integer> r = new LinkedList<>(x);
        r.addFirst(0);
        int carry;
        int tmp;
        System.out.println("lets start outer loop, k.size = " + k + ", and l.size = " + l);
        for(int i = k-l; i >= 0; i--) {
            //System.out.println("in outer loop " + i);
            //System.out.println("r.get(i+l): " + r.get(i+l) + ", b: " + b + ", r.get(i+l-1): " + r.get(i+l-1) + ", y.get(l-1): " + y.get(l-1));
            int numberToAddInQ = (r.get(i+l)*b+r.get(i+l-1))/y.get(l-1);
            System.out.println("NumberToAddInQ: " + numberToAddInQ);
            if (numberToAddInQ >= b) {
                numberToAddInQ = b-1;
            }
            q.addFirst(numberToAddInQ);
            /*
            carry = 0;
            System.out.println(q + " " + numberToAddInQ);
            for (int j = 0; j < l; j++) {
                //System.out.println("r.get(i+j): " + r.get(i+j) + ", q.get(0): " + q.get(0) + ", y.get(j): " + y.get(j) + ", carry: " + carry);
                tmp = r.get(i+j) - q.get(0)*y.get(j) + carry;
                carry = tmp/b;
                //System.out.println("end of first nested loop " + j + ", carry = " + carry + " and tmp = " + tmp);
                r.set(i+j, tmp%b);
            }
            
            r.set(i+l, r.get(i+l)+carry);
            */
            System.out.println(r);
            LinkedList<Integer> qiList = new LinkedList<>(Arrays.asList(q.get(0)));
            LinkedList<Integer> qiListTimesY = Karatsuba.karatsuba(qiList, y, b, null, 1);
            System.out.println(Karatsuba.karatsuba(qiList, y, b, null, 1));
            r = Subtraction.subtract(r, qiListTimesY, b, null);
            System.out.println(qiList + " " + qiListTimesY + " " + y + " " + r);
            while (r.get(i+l) < 0) {
                System.out.println("in while loop, current r(i+1): " + r.get(i+1));
                carry = 0;
                for (int j = 0; j < l; j++) {
                    System.out.println("in second nested loop " + j);
                    tmp = r.get(i+j) - y.get(i) + carry;
                    carry = tmp/b;
                    r.set(i+j, tmp%b);
                }
                r.set(i+l, r.get(i+l)+carry);
                q.set(0, q.get(0)-1);
            }
        }
        Arithmetic.removeLeadingZeros(r); Arithmetic.removeLeadingZeros(q);
        return new Arithmetic.QuoRem(q,r);
    }
    
}
