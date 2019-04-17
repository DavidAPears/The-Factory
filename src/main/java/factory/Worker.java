package factory;

public class Worker {
    
    public String[][] workerLeft;       // Worker left of the belt
    public String[][] workerRight;      // Worker right of the belt
    private int[] workerLeftPresent;    // Presence of workers on left
    private int[] workerRightPresent;   // Presence of workers on right
    private int[] stateL;               // State left
    private int[] stateR;               // State right
    private int Length;                 // Belt length
    private int nComponent;             // Number of components

    public String[] belt;


    public Worker(int nComponent, String[] belt){

        Length  = belt.length;
        this.nComponent = nComponent;

        this.belt = new String[Length];
        System.arraycopy(belt, 0, this.belt, 0, Length);

        workerLeft  = new String[Length][nComponent];
        workerRight = new String[Length][nComponent];

        workerLeftPresent   = new int[Length];
        workerRightPresent  = new int[Length];

        stateL = new int[Length];
        stateR = new int[Length];

        init();
    }

    private void init(){

        for(int i=0; i<Length; i++){

            for(int j=0; j<nComponent; j++){
                workerLeft  [i][j] = null;
                workerRight [i][j] = null;
            }

            workerLeftPresent [i] = 1;
            workerRightPresent[i] = 1;
            /*NB: A worker is either present (1) or not (0). By default they are all present.
            Can be amended */

            stateL[i] = 1;
            stateR[i] = 1;
            /* NB: State can be 1 of 3. Initially '1' as workers have free hands
                   state 1 --> hand free
                   state 2 --> hands busy with components
                   state 3 --> product ready */
        }
    }


    public void updateState(int position){

        if(position==0){
            for(int i=0; i<Length; i++){
                if("P".equals(workerLeft[i][0])){
                    stateL[i] = 3;
                    // If first hand has P --> state 3 (product ready)
                } else if(workerLeft[i][nComponent-1]==null){
                    stateL[i] = 1;
                    // If second hand is free --> state 1 (hand free)
                } else stateL[i] = 2;
                // Otherwise both hands are busy
            }
        }

        if(position==1){
            for(int i=0; i<Length; i++){
                if("P".equals(workerRight[i][0])){
                    stateR[i] = 3;
                    // If first hand has P --> state 3 (product ready)
                } else if(workerRight[i][nComponent-1]==null){
                    stateR[i] = 1;
                    // If second hand is free --> state 1 (hand free)
                } else stateR[i] = 2;
                // otherwise both hands are busy
            }
        }
    }

    public void doWork(int[] action, int position){

        String[][] w = new String[Length][nComponent];
        int        k;

        if(position==0){
            for(int i=0; i<Length; i++){
                System.arraycopy(workerLeft[i], 0, w[i], 0, nComponent);
            }
        } else {
            for(int i=0; i<Length; i++){
                System.arraycopy(workerRight[i], 0, w[i], 0, nComponent);
            }
        }


        for(int i=0; i<Length; i++){
            if(action[i]==1){              // TAKES Component from belt into free hand
                k=0;
                while(k>=0){
                    if(w[i][k]==null){      // If workers 'hand' is empty
                        w[i][k] = belt[i];  // Add content of belt to 'hand'
                        k = -100;
                    }
                    k+=1;
                }
                belt[i] = null;
            }
            if(action[i]==3){               // BUILDS Product
                for(int j=1; j<nComponent; j++){
                    w[i][j] = null;
                }
                w[i][0] = "P";
            }
            if(action[i]==4){               // PLACES Product on Belt (& empties 'hand')
                belt[i] = "P";
                for(int j=0; j<nComponent; j++){
                    w[i][j] = null;
                }

            }
        }

        if(position==0){
            for(int i=0; i<Length; i++){
                System.arraycopy(w[i], 0, workerLeft[i], 0, nComponent);
            }
        } else {
            for(int i=0; i<Length; i++){
                System.arraycopy(w[i], 0, workerRight[i], 0, nComponent);
            }
        }

        updateState(position);
    }

    public void updateBelt(String[] belt){
        System.arraycopy(belt, 0, this.belt, 0, Length);
    }

    public int[] getStateL(){
        return stateL;
    }
    public int[] getStateR(){
        return stateR;
    }

    public int[] getWorkersL(){
        return workerLeftPresent;
    }
    public int[] getWorkersR(){
        return workerRightPresent;
    }
}
