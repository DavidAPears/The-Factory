package factory;

public class Simulation {

    public   Belt   belt;
    private  Worker workers;
    private  Draw   draw;
    private  Action action;

    public Simulation(Belt belt, Worker workers, Draw draw){
        this.belt    = belt;
        this.workers = workers;
        this.draw    = draw;
    }

    private void init(){

        System.out.println("---------------- Simulation = 0 ---------------------\n");

        draw.test(belt, workers);

        // CREATES object action based on actual states
        action = new Action(workers.getStateL(),
                workers.getStateR(),
                workers.getWorkersL(),
                workers.getWorkersR(),
                belt.getBeltState());
    }

    public void runSimulation(int time){

        init();

        for(int t=0; t<time; t++){

            action.generateAction(workers.workerLeft, belt.belt, 0);
            // Generates actions for workers on the left
            action.generateAction(workers.workerRight, belt.belt, 1);
            // Generates actions for workers on the right
            action.conflictCheck();
            // Checks presence of conflicts

            workers.doWork(action.leftWorkerAction, 0);
            // Workers on the left execute their job
            workers.doWork(action.rightWorkerAction, 1);
            // Workers on the right execute their job

            belt.updateBelt(workers.belt);
            // Updates belt

            draw.test(belt, workers);
            System.out.println("---------------- Simulation No." + (t+1) + " ---------------------\n");

            belt.slideBelt();
            // Slide/Move the belt
            workers.updateBelt(belt.belt);
            // Workers update their knowledge of belt
            action.updateState(workers.getStateL(), workers.getStateR(), belt.getBeltState());
            // Updates action with new states of belt and workers

            draw.test(belt, workers);

        }

        System.out.println("----------------------------------------------\n");

    }

    public static void main(String args[]){

        // Parameters: length of belt, number of components to assemble
        // (internally we can also set a variable number of workers at the belt)

        int BeltLength = 3;  // Belt length, set as 3 as 3 pairs of workers (This can be amended to scale)
        int numberComp = 2;  // Number of components to assemble (This can be amended to scale)

        Belt myBelt   = new Belt    (BeltLength, numberComp);
        Worker myWork = new Worker  (numberComp, myBelt.belt);
        Draw myDraw   = new Draw    (BeltLength, numberComp);

        // Create and run simulation
        Simulation mySimulation = new Simulation(myBelt, myWork, myDraw);
        int simTime = 100;
        mySimulation.runSimulation(simTime);

        //----------------------------------------------------------------------

        // Computation of frequencies of components. Final components are stored
        // in variable myBelt.beltOutput

        char[]   setComponents = new char[numberComp];
        int[]    quantity      = new int[numberComp];
        int      product       = 0;
        int      value;
        for(int i=0; i<numberComp; i++){
            value = 65+i;                   // ASCII Set: A=65, B=66, etc
            setComponents[i] = (char)value; // Contains all different components
            quantity[i]      = 0;
        }

        Object p1;
        String p2;
        char   p3;

        for(int i=0; i<myBelt.beltOutput.size(); i++){
            p1 = myBelt.beltOutput.get(i);
            p2 = (String)p1;
            p3 = p2.charAt(0);
            for(int j=0; j<numberComp; j++){
                if(p3==setComponents[j]){
                    quantity[j] += 1;
                }
            }
            if(p3=='P'){
                product += 1;
            }
        }


        System.out.println("RESULTS:" );

        for(int i=0; i<numberComp; i++){
            System.out.println("Unused " + setComponents[i] + " Components: " + " Total = " + quantity[i]);
        }
        System.out.println("Assembled Products :" + "  Total = " + product + "\n");
    }

}
