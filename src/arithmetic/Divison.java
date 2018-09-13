package arithmetic;
/**
 * @author E.M.A. Arts (1004076)
 * @author K. Degeling (1018025)
 * @author R.M. Jonker (1011291)
 * @author S. Jacobs (1005276)
 * @author M. Schotsman (0995661)
 *
 * @since 12 SEPTEMBER 2018
 */
public class Divison {
    public static void main(String[] args) {

    }

    /**
     * Method which divides 2 large radix b numbers x and y
     * @param x radix b representation of x
     * @param y radix b representation of y
     * @param b radix
     * @return (q,r) s.t. x=qy+r
     */
    public static RemainderQuotient divide(int[] x, int[] y, int b) {
        int[] r = x.clone();
        int k = x.length-y.length+1;
        int[] q = new int[k];

        return new RemainderQuotient(0, 0);
    }

    /**
     * Class which contains the tuple (q,r).
     */
    public static class RemainderQuotient {
        int q,r;
        public RemainderQuotient(int q, int r) {
            this.q = q; this.r = r;
        }
        public int getQ() {return this.q;}
        public int getR() {return this.r;}
    }
}
