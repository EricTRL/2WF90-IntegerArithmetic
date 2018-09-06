package io;

import java.io.PrintStream;

/**
 * Class related to writing outputs.
 * 
 * @author E.M.A. Arts (1004076)
 * @author K. Degeling (1018025)
 * @author R.M. Jonker (1011291)
 * @author S. Jacobs (1005276)
 * @author M. Schotsman (0995661)
 * 
 * @since 6 SEPTEMBER 2018
 */
public class OutputWriter {
    /**
     * Method which will print the output to systemOut
     *
     * @param systemOut PrintStream of where to print (E.g. System.out)
     * @param p pack which contains the rectangle input
     * @param prefix String to print before the actual calculated outputs
     *              E.g. a String from an InputReader's getInputMessage()-method.
     */
    public static void printOutput(PrintStream systemOut, Pack p, String prefix) {
        systemOut.print(prefix);
        systemOut.println("placement of rectangles");
        for (RectangleRotatable r : p.getOrderedRectangles()) {
            if (p.canRotate()) {
                if (r.isRotated()) {
                    systemOut.print("yes ");
                } else {
                    systemOut.print("no ");
                }
            }
            systemOut.println(r.x + " " + r.y);
        }
    }
}
