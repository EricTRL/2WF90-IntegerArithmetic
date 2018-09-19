package arithmetic;

import java.util.Iterator;
import java.util.LinkedList;
import arithmetic.Arithmetic;

public class Division {
    public static void main(String[] args) {
        LinkedList<Integer> x = new LinkedList<>(Computation.stringToList("12"));
        LinkedList<Integer> y = new LinkedList<>(Computation.stringToList("5"));
        System.out.println(divide(x,y,10,null).q + " " + divide(x,y,10,null).r);
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
        LinkedList<Integer> r = new LinkedList<>(x);
        LinkedList<Integer> q = new LinkedList<>();
        r.addFirst(0);
        int kl = x.size()-y.size();
        Iterator<Integer> it_r = r.iterator();
        Iterator<Integer> it_y = y.descendingIterator();
        int r_i1 = it_r.next();
        int y_l = it_y.next();
        int l = y.size();
        int carry;
        for (int i = 0; i <= kl; i++) {
            int r_i = r_i1;
            r_i1 = it_r.next();
            q.add((r_i*B+r_i1)/y_l);
            if (q.getFirst()>=B) q.set(0, q.getFirst()-1);
            carry = 0;
            Iterator<Integer> it_r_j = r.listIterator(i);
            Iterator<Integer> it_y_j = y.iterator();
            for (int j = l-1; j >= 0; j--) {
               int tmp = it_r_j.next() - q.getFirst()*it_y_j.next()+carry;
                carry = tmp/B; r.set(i+j, tmp%B);
            }
            r.set(i+y.size(), r.get(i+y.size())+carry);
            while (r.get(i+y.size())<0) {
                carry = 0;
                for (int j = l-1; j >= 1; j--) {
                    int tmp = r.get(i+j)+y.get(i)+carry;
                    carry = tmp/B; r.set(i+j, tmp%B);
                }
                r.set(i+y.size(), r.get(i+y.size())+carry);
                q.set(0, q.getFirst()-1);
            }
        }
        return new Arithmetic.QuoRem(q,r);
    }
}
