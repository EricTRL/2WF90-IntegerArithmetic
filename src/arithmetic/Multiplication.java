/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic;

/**
 * @author E.M.A. Arts (1004076)
 * @author K. Degeling (1018025)
 * @author R.M. Jonker (1011291)
 * @author S. Jacobs (1005276)
 * @author M. Schotsman (0995661)
 * 
 * @since 6 SEPTEMBER 2018
 */
public class Multiplication {
    public static void main(String[] args) {
        int[] a = {-3, -2, -5, -0, -5, -8};
        int[] b = {6, 3, 2, 5, 7, 7};
        for (int x : multiply(a, b, 10)) {
            System.out.print(x+" ");
        }
    }
    /**
     * Multiply large numbers in radix b
     *
     * @param x array with first number
     * @param y array with second number
     * @param b radix to be used
     * @pre x.length==y.length
     * @return x*y in radix b
     */
    public static int[] multiply (int[] x, int[] y, int b) {
        int carry = 0; //optional carry we need to add.
        int zerosAdded = 0; //amount of zeros added at the end (increases by one every iteration of i)
        int carryPlacementCount = 0; // place where carry has to be added
        int arrayExtendedByAmount = 0; // to compensate for a longer array cause of addition implementation
        int[] answer = new int[x.length*2];
        int[] answerToAdd = new int[x.length*2];
        for (int i = y.length; i > 0; i--) {
            for (int j = x.length; j > 0; j--) {
                //System.out.println((x.length*2)-(x.length-j)-zerosAdded-1+arrayExtendedByAmount);
                answerToAdd[(x.length*2)-(x.length-j)-zerosAdded-1+arrayExtendedByAmount] = (x[j-1]*y[i-1] + carry)%b; //compute new value
                //compute carry
                //System.out.println("x[j-1]: " + x[j-1] + ", y[i-1]: " + y[i-1] + ", carry: " + carry + ", answerToAdd: " + (x[j-1]*y[i-1] + carry)%b);
                if (Math.abs(x[j-1]*y[i-1]+carry)>=b) {
                    carry = (x[j-1]*y[i-1]+carry)/b;
                } else {
                    carry = 0;
                }
                carryPlacementCount = (x.length*2)-(x.length-j)-zerosAdded-2+arrayExtendedByAmount; //Keep track of where to place carry (only needed after j for-loop)
                //System.out.println("carry: " + carry + ", carryPlacementCount: " + carryPlacementCount);
            }
            
            /*
            for (int digit : answerToAdd) {
                System.out.print(digit);
                System.out.println("carry: " + carry + ", carryPlacementCount: " + carryPlacementCount);
            }
            */
            
            //System.out.println(zerosAdded);
            //System.out.println("carry: " + carry + ", carryPlacementCount: " + carryPlacementCount);
            //System.out.println();
            answerToAdd[carryPlacementCount] = carry; //Place carry
            carry = 0; //reset carry
            //Add zeros where needed
            /*
            for(int z = 0; z < zerosAdded; z++){
                answerToAdd[x.length*2-1-z] = 0;
            }
            */
            zerosAdded++; //Increase zerosAdded counter for next iteration
            
            /*
            for (int digit : answerToAdd) {
                System.out.print(digit);
            }
            */
            
            // Wrong implementation of copying of the array. Probably not needed.
            if (answer.length>answerToAdd.length) {
                int[] backupAnswerToAdd = new int[answerToAdd.length];
                for (int a = 0; a < answerToAdd.length; a++) {
                    backupAnswerToAdd[a] = answerToAdd[a];
                }
                answerToAdd = new int[answer.length];
                for (int a = 0; a < backupAnswerToAdd.length; a++) {
                    //System.out.println("a:" + a);
                    answerToAdd[a+1] = backupAnswerToAdd[a];
                }
                arrayExtendedByAmount++; //increase array extension counter
            }
            
            /*
            for (int digit : answerToAdd) {
                System.out.print(digit);
            }
            */
            
            answer = Addition.add(answer, answerToAdd, b);
            answerToAdd = new int[answerToAdd.length]; //reset answerToAdd array
        }
        return answer;
    }
    
}
