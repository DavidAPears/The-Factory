package factory;
import java.util.ArrayList;
import java.util.Random;


public class Belt {

    public  String[] belt;
    private int      Length;
    private int      nComponent;
    private int[]    beltState; // State of the belt:
                                // Has three states: 'Empty', 'With Component' & 'With Product'

    ArrayList beltOutput = new ArrayList();

    public Belt(int Length, int nComponent){

        this.Length       = Length;
        this.nComponent   = nComponent;
        belt              = new String[Length];

        for(int i=0; i<Length; i++){
            belt[i] = null;
            // Initial belt is empty
        }

        beltState = new int[Length];
        init();
    }


    private void init(){

        // ADDS new component to the belt
        Random randomGenerator = new Random();
        int value = randomGenerator.nextInt(nComponent+1);
        if (value>0){
            value = value + 64; // ASCII Set: A=65, B=66, etc
            belt[0] = Character.toString((char)value);
        } else {
            belt[0] = null;
        }
        updateBeltState();

    }


    private void updateBeltState(){

        // Sets state of belt (1 of 3 states)
        for(int i=0; i<Length; i++){
            if(belt[i]==null){
                beltState[i] = 3;     // 3 = Belt 'Empty'
            } else if("P".equals(belt[i])){
                beltState[i] = 2;     // 2 = Belt 'With Product'
            } else beltState[i] = 1;  // 1 = Belt 'With Component'
        }

    }


    public void updateBelt(String[] B){
        System.arraycopy(B, 0, belt, 0, Length);
        updateBeltState();
    }


    public void slideBelt(){

        String[] beltTemp = new String[Length];

        if(belt[Length-1]!=null){
            beltOutput.add(belt[Length-1]);
        }
        for(int i=1; i<Length; i++){
            beltTemp[i] = belt[i-1];
        }
        System.arraycopy(beltTemp, 0, belt, 0, Length);
        init();
    }


    public int[] getBeltState(){
        return beltState;
    }

}