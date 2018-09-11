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
        int[] a = {1,1,1,0,0,0,1};
        int[] b = {1,0,0,1,1,0,1};
        for (int x : multiply(a, b, 2)) {
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
                answerToAdd[(x.length*2)-(x.length-j)-zerosAdded-1+arrayExtendedByAmount] = (x[j-1]*y[i-1] + carry)%b; //compute new value
                //compute carry
                if (Math.abs(x[j-1]*y[i-1]+carry)>=b) {
                    carry = (x[j-1]*y[i-1]+carry)/b;
                } else {
                    carry = 0;
                }
                carryPlacementCount = (x.length*2)-(x.length-j)-zerosAdded-2+arrayExtendedByAmount; //Keep track of where to place carry (only needed after j for-loop)
            }
            
            answerToAdd[carryPlacementCount] = carry; //Place carry
            carry = 0; //reset carry
            zerosAdded++; //Increase zerosAdded counter for next iteration
            
            // Increase size of answerToAdd if Addition.add increased the size of the answer array
            if (answer.length>answerToAdd.length) {
                int[] backupAnswerToAdd = new int[answerToAdd.length];
                for (int a = 0; a < answerToAdd.length; a++) {
                    backupAnswerToAdd[a] = answerToAdd[a];
                }
                answerToAdd = new int[answer.length];
                for (int a = 0; a < backupAnswerToAdd.length; a++) {
                    answerToAdd[a+1] = backupAnswerToAdd[a];
                }
                arrayExtendedByAmount++; //increase array extension counter
            }
            
            answer = Addition.add(answer, answerToAdd, b); //add current result to total result
            answerToAdd = new int[answerToAdd.length]; //reset answerToAdd array
        }
        return answer;
    }
    
}
