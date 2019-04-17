package factory;

public class Draw {

/*  For each time point we plot the Components taken by the Workers and the Belt.
    E.g. Two components (A,B); belt of length (Length=3)

    Piece 1       - B B   <--- First  'hand' of Workers in positions 1,2 & 3 (to left of the Belt)
    Piece 2       - - -   <--- Second 'hand' of Workers in positions 1,2 & 3 (to left of the Belt)
    BELT          A P =   <--- Positions 1,2,3 of the Belt
    Piece 1       P B B   <--- First  'hand' of Workers in positions 1,2 & 3 (to right of the Belt)
    Piece 2       - - -   <--- Second 'hand' of Workers in positions 1,2 & 3 (to right of the Belt)

    For each time there are two plots:

    1) As soon as the belt has slid/moved
    2) After workers have performed their actions   */


    private int Length;
    private int nComponent;

    public Draw(int Length, int nComponent){

        this.Length     = Length;
        this.nComponent = nComponent;
    }


    public void test(Belt B, Worker W){

        for(int i=0; i<nComponent; i++){
            System.out.print("piece " + (i+1) + "       ");
            for(int j=0; j<Length; j++){
                if(W.workerLeft[j][i]==null){
                    System.out.print("-" + " ");
                } else{
                    System.out.print(W.workerLeft[j][i] + " ");
                }
            }
            System.out.println();
        }

        System.out.print(" BELT         ");
        for(int i=0; i<Length; i++){
            if(B.belt[i]==null){
                System.out.print("=" + " ");
            } else {
                System.out.print(B.belt[i] + " ");
            }
        }
        System.out.println();

        for(int i=0; i<nComponent; i++){
            System.out.print("piece " + (i+1) + "       ");
            for(int j=0; j<Length; j++){
                if(W.workerRight[j][i]==null){
                    System.out.print("-" + " ");
                } else{
                    System.out.print(W.workerRight[j][i] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();

    }
}

