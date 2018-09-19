package arithmetic;

import java.util.Iterator;
import java.util.LinkedList;
import arithmetic.Arithmetic;

public class Division {
    public static void main(String[] args) {
        LinkedList<Integer> x = new LinkedList<>(Computation.stringToList("856486231"));
        LinkedList<Integer> y = new LinkedList<>(Computation.stringToList("65454"));
        System.out.println(dumbDivide(x,y,10,null).q + " " + dumbDivide(x,y,10,null).r);
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
}
