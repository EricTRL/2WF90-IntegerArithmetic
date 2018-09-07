/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic;

/**
 *
 * @author E.M.A. Arts (1004076)
 */
public class Computation {
    private String xString;
    private String yString;
    private String mString;
    private String answerString;
    
    private int[] x;
    private int[] y;
    private int[] m;
    private int[] answer;
    private boolean answered = false;
    
    public int radix;
    public String type;
    public int countAdd = 0;
    public int countMul = 0;
    //TODO: answ-a, answ-b, and answ-d
    
    //constructor
    public Computation(String x, String y, int radix, String type, String m) {
        this.radix = radix;
        this.type = type;
        this.xString = x;
        this.yString = y;
        this.mString = m;
        
        //TODO: convert x, y, and m to int[]-things + store them
    }
    
    /* basic getters */
    public String getXAsString() {
        return xString;
    }
    
    public String getYAsString() {
        return yString;
    }
    
    public String getMAsString() {
        return mString;
    }
    
    public String getAnswerAsString() {
        return answered ? answerString : null;
    }
    
    public int[] getX() {
        return x;
    }
    
    public int[] getY() {
        return y;
    }
    
    public int[] getM() {
        return m;
    }
    
    public int[] getAnswer() {
        return answered ? answer : null;
    }
    
    /*Basic setters */
    public void setAnswer(int[] answer) {
        this.answer = answer;
        answered = true;
        //TODO: do sth with answerString
    }
    
    /*Other stuff*/
    @Override
    public String toString() {
        return "(" + type + ", " + xString + ", " + yString + ", " + mString + ")";
    }

}
