package io;

import arithmetic.Computation;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //input directories
    protected static final File DEFAULT_OUTPUT_FILE_PATH = new File("res/");
    protected static final String DEFAULT_OUTPUT_FILE_NAME = "output.txt";
    protected static final File DEFAULT_OUTPUT_FILE = new File(DEFAULT_OUTPUT_FILE_PATH + "/" + DEFAULT_OUTPUT_FILE_NAME);
    
    private static final Map<String, List<String>> OUTPUT_FOR_CALCULATION;
    static {
        Map<String, List<String>> map = new HashMap<>();
        //radix
        //type
        map.put("y", new ArrayList<>(Arrays.asList("[add]", "[subtract]", "[multiply]", "[karatsuba]", "[euclid]")));
        map.put("m", new ArrayList<>(Arrays.asList("[add]", "[subtract]", "[multiply]", "[karatsuba]", "[euclid]", "[reduce]", "[inverse]")));
        map.put("answer", new ArrayList<>(Arrays.asList("[add]", "[subtract]", "[multiply]", "[karatsuba]", "[reduce]", "[inverse]")));
        map.put("count-add", new ArrayList<>(Arrays.asList("[multiply]", "[karatsuba]")));
        map.put("count-mul", new ArrayList<>(Arrays.asList("[multiply]", "[karatsuba]")));
        map.put("answ-d", new ArrayList<>(Arrays.asList("[euclid]")));
        map.put("answ-a", new ArrayList<>(Arrays.asList("[euclid]")));
        map.put("answ-b", new ArrayList<>(Arrays.asList("[euclid]")));
        
        OUTPUT_FOR_CALCULATION = Collections.unmodifiableMap(map);
    }   
    
    
    public static void writeOutput(List<Computation> computations) {
        DEFAULT_OUTPUT_FILE_PATH.mkdirs();
        try (FileOutputStream streamOut = new FileOutputStream(DEFAULT_OUTPUT_FILE);) {
            writeOutput(streamOut, computations);
        } catch (FileNotFoundException e) {
            //hide error messages
            System.err.println("Could not find file");
        } catch (IOException e) {
            System.err.println("Could not read file");
        }
    }
    
    /**
     * Method which will print the output to systemOut
     *
     * @param streamOut PrintStream of where to print (E.g. System.out)
     * @param computations A list of computations to print
     */
    public static void writeOutput(OutputStream streamOut, List<Computation> computations) {
        if (computations == null) {
            return;
        }
        try (
                BufferedWriter fileWriter =
                        new BufferedWriter(new OutputStreamWriter(streamOut, "UTF-8"));
        ) {
            for (Computation computation : computations) {
                writeOutput(fileWriter, computation);
            }
        } catch (FileNotFoundException e) {
            //hide error messages
            System.err.println("Could not find file");
        } catch (IOException e) {
            System.err.println("Could not read file");
        }
    }
    
    private static void writeOutput(BufferedWriter fileWriter, Computation computation) throws IOException {
        fileWriter.write("[radix] " + computation.getRadix()); fileWriter.newLine();
        
        String type = computation.getType();
        fileWriter.write(type); fileWriter.newLine();
        fileWriter.write("[x] " + computation.getXAsString()); fileWriter.newLine();
        
        if (OUTPUT_FOR_CALCULATION.get("y").contains(type)) {
            fileWriter.write("[y] " + computation.getYAsString()); fileWriter.newLine();
        }
        if (OUTPUT_FOR_CALCULATION.get("m").contains(type) && !computation.getM().isEmpty()) {
            fileWriter.write("[m] " + computation.getMAsString()); fileWriter.newLine();
        }
        if (OUTPUT_FOR_CALCULATION.get("answer").contains(type)) {
            fileWriter.write("[answer] " + computation.getAnswerAsString()); fileWriter.newLine();
        }        
        if (OUTPUT_FOR_CALCULATION.get("count-add").contains(type)) {
            fileWriter.write("[count-add] " + computation.getCountAdd()); fileWriter.newLine();
        }
        if (OUTPUT_FOR_CALCULATION.get("count-mul").contains(type)) {
            fileWriter.write("[count-mul] " + computation.getCountMultiply()); fileWriter.newLine();
        }
        if (OUTPUT_FOR_CALCULATION.get("answ-d").contains(type)) {
            fileWriter.write("[answ-d] " + computation.getAnswDAsString()); fileWriter.newLine();
        }
        if (OUTPUT_FOR_CALCULATION.get("answ-a").contains(type)) {
            fileWriter.write("[answ-a] " + computation.getAnswAAsString()); fileWriter.newLine();
        }
        if (OUTPUT_FOR_CALCULATION.get("answ-b").contains(type)) {
            fileWriter.write("[answ-b] " + computation.getAnswBAsString()); fileWriter.newLine();
        }
        fileWriter.newLine();
    }
}
