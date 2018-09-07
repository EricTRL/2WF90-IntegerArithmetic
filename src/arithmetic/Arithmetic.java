package arithmetic;

import io.*;

/**
 * This is the base class that is run.
 * It reads input with InputReader, applies any of the arithmetic functions
 * and return the output via OutputWriter
 * 
 * @author E.M.A. Arts (1004076)
 * @author K. Degeling (1018025)
 * @author R.M. Jonker (1011291)
 * @author S. Jacobs (1005276)
 * @author M. Schotsman (0995661)
 * 
 * @since 6 SEPTEMBER 2018
 */
public class Arithmetic {   
    
    public static void main(String args[]) {
        InputReader reader = new InputReader(System.in);
        System.out.println(reader.getNextComputation());
        System.out.println(reader.getNextComputation());
        System.out.println(reader.getNextComputation());
        System.out.println(reader.getNextComputation());
        
    }
    
}
