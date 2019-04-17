package factory;
import java.util.Random;


public class Action {

    private int[] stateL;
    private int[] stateR;
    private int[] workerPresentLeft;
    private int[] workerPresentRight;
    private int[] beltState;
    private int[] conflict;
    private int   Length;

    public int[] leftWorkerAction;
    public int[] rightWorkerAction;
                    /*  NB: Types of actions for left/right hand side workers:
                        1 -> take component      2 -> wait
                        3 -> build product       4 -> place product  */

    public Action(int[] stateL, int[] stateR, int[] workerPresentLeft, int[] workerPresentRight, int[] beltState){

        this.stateL              = stateL;
        this.stateR              = stateR;
        this.workerPresentLeft   = workerPresentLeft;
        this.workerPresentRight  = workerPresentRight;
        this.beltState           = beltState;

        Length = beltState.length;
        conflict = new int[Length];

        for(int i=0; i<Length; i++){
            conflict[i] = workerPresentLeft[i]+workerPresentRight[i];
            // Number of workers for each belt step/position
        }

        leftWorkerAction  = new int[Length];
        rightWorkerAction = new int[Length];
    }


    public void generateAction(String[][] w, String[] belt, int position){

        int   nComponent = w[0].length;
        int[] state      = new int[Length];
        int[] action     = new int[Length];

        if(position==0){
            System.arraycopy(stateL, 0, state, 0, Length);
        } else {
            System.arraycopy(stateR, 0, state, 0, Length);
        }

        for(int i=0; i<Length; i++){

            if(state[i]==2){
                action[i] = 3;
                // No more components needed
            }
            if((state[i]==3)&(beltState[i]==3)){
                action[i] = 4;
                // Product ready, belt free
            }
            if((state[i]==1)&(beltState[i]==1)){
                // Component on the belt, hand free
                action[i] = 1;
                // Component required
                for(int j=0; j<nComponent; j++){
                    if(belt[i].equals(w[i][j])){
                        action[i] = 2;
                        // If already present, component not needed
                    }
                }

            }
            if(((state[i]==3)&(beltState[i]==1))|((state[i]==3)&(beltState[i]==2))){
                action[i] = 2;
                // Other cases
            }
            if(((state[i]==1)&(beltState[i]==2))|((state[i]==1)&(beltState[i]==3))){
                action[i] = 2;
                // Other cases
            }

        }

        if(position==0){
            for(int i=0; i<Length; i++){
                if(workerPresentLeft[i]==0){
                    action[i] = 2;
                    // If no worker is present at position 'i' - WAIT (i.e. do nothing)
                }
            }
        } else {
            for(int i=0; i<Length; i++){
                if(workerPresentRight[i]==0){
                    action[i] = 2;
                    // If no worker is present at position 'i' - WAIT (i.e. do nothing)
                }
            }
        }

        if(position==0){
            System.arraycopy(state,  0, stateL,  0, Length);
            System.arraycopy(action, 0, leftWorkerAction, 0, Length);
        } else {
            System.arraycopy(state,  0, stateR,  0, Length);
            System.arraycopy(action, 0, rightWorkerAction, 0, Length);
        }

    }

    public void conflictCheck(){

        for(int i=0; i<Length; i++){
            if(conflict[i]>1){
                if((leftWorkerAction[i]==1)&(rightWorkerAction[i]==1)){
                    // Take - Take
                    conflictSolve(i);
                }
                if((leftWorkerAction[i]==1)&(rightWorkerAction[i]==4)){
                    // Take - Place
                    conflictSolve(i);
                }
                if((leftWorkerAction[i]==4)&(rightWorkerAction[i]==4)){
                    // Place - Place
                    conflictSolve(i);
                }
                if((leftWorkerAction[i]==4)&(rightWorkerAction[i]==1)){
                    // Place - Take
                    conflictSolve(i);
                }
            }
        }

    }

    private void conflictSolve(int k){

        // In case of a conflict, assumption is that one of the two workers will
        // wait (i.e. a random choice)

        Random randomGenerator = new Random();

        if(randomGenerator.nextInt(2)>0){
            leftWorkerAction[k] = 2;
            // Left worker waits
        } else{
            rightWorkerAction[k] = 2;
            // Right worker waits
        }

    }

    public void updateState(int[] stateL, int[] stateR, int[] beltState){
        this.stateL    = stateL;
        this.stateR    = stateR;
        this.beltState = beltState;
    }

}
