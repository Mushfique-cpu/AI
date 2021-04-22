import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class PlayingWithWheel {

    static LinkedList<State> openSet = new LinkedList<>();
    static LinkedList<State> closedSet = new LinkedList<>();
    static LinkedList<State> shortPath = new LinkedList<>();
    static LinkedList<State> blockedSet = new LinkedList<>();

    public static void main(String[] args) {
        int startArray[] = {8,0,5,6};
        //int startArray[] = {0,0,0,0};

        int[] finalState = {6,5,0,8};
        //int[] finalState = {5,3,1,7};

        State.finalState = finalState;

        State startState = new State();
        startState.setArray(startArray);
        openSet.add(startState);
        setBlockedSet();
        //setBlockedSet2();
        State currentState = closestState();
        while(true){
            if(openSet.size() == 0){
                System.out.println("Unreachable");
                break;
            }
            if((Math.abs(currentState.array[0] - currentState.finalState[0]) + Math.abs(currentState.array[1] - currentState.finalState[1]) + Math.abs(currentState.array[2] - currentState.finalState[2]) + Math.abs(currentState.array[3] - currentState.finalState[3])) == 0){
                System.out.println("Reached");
                State pathState = currentState;
                while (pathState!=null){
                    shortPath.addFirst(pathState);
                    pathState = pathState.previousState;
                }
                break;
            }
            closedSet.add(currentState);
            openSet.remove(currentState);
            createState(currentState);
            if(openSet.size()!=0){
            currentState = closestState();
            }
        }

        System.out.println("Solution:");
        for (int i=0; i<shortPath.size(); i++){
            State printState = shortPath.get(i);
            System.out.print("[" + printState.array[0]+ "," + printState.array[1]+ "," + printState.array[2]+ "," + printState.array[3]+"]");
            if (i+1<shortPath.size()){
                System.out.print("===>>");
            }
        }
        System.out.println("\nShortest distance to solution: " + (shortPath.size()-1));

    }

    static public State closestState(){
        State state = openSet.element();
        ListIterator<State> listIterator = openSet.listIterator();
        while (listIterator.hasNext()){
            State compareState = listIterator.next();
            if(compareState.fitness<state.fitness){
                state = compareState;
                return state;
            }
        }
        return state;
    }

    static  public void addToOpenSet(State state ,State previousState){
        if(!checkSets(state)){
            state.setPreviousState(previousState);
            openSet.add(state);
        }
    }

    static public boolean checkSets(State state){
        boolean inOpenSet = false;
        boolean inClosedSet = false;
        boolean inBlockedSet = false;
        ListIterator<State> listIterator = openSet.listIterator();
        while (listIterator.hasNext()){
            State compareState = listIterator.next();
            if(compareState.array[0]==state.array[0] && compareState.array[1]==state.array[1] && compareState.array[2]==state.array[2] && compareState.array[3]==state.array[3]){
                inOpenSet = true;
            }
        }
        ListIterator<State> listIterator2 = closedSet.listIterator();
        while (listIterator2.hasNext()){
            State compareState = listIterator2.next();
            if(compareState.array[0]==state.array[0] && compareState.array[1]==state.array[1] && compareState.array[2]==state.array[2] && compareState.array[3]==state.array[3]){
                inClosedSet = true;
            }
        }
        ListIterator<State> listIterator3 = blockedSet.listIterator();
        while (listIterator3.hasNext()){
            State compareState = listIterator3.next();
            if(compareState.array[0]==state.array[0] && compareState.array[1]==state.array[1] && compareState.array[2]==state.array[2] && compareState.array[3]==state.array[3]){
                inBlockedSet = true;
            }
        }
        return inClosedSet || inOpenSet ||inBlockedSet;
    }

    public static void setBlockedSet(){
        int block1[] = {8,0,5,7};
        int block2[] = {8,0,4,7};
        int block3[] = {5,5,0,8};
        int block4[] = {7,5,0,8};
        int block5[] = {6,4,0,8};

        State state11 = new State();
        state11.setArray(block1);
        blockedSet.add(state11);

        State state22 = new State();
        state22.setArray(block2);
        blockedSet.add(state22);

        State state33 = new State();
        state33.setArray(block3);
        blockedSet.add(state33);

        State state44 = new State();
        state44.setArray(block4);
        blockedSet.add(state44);

        State state55 = new State();
        state55.setArray(block5);
        blockedSet.add(state55);
    }

    public static void setBlockedSet2(){
        int block1[] = {0,0,0,1};
        int block2[] = {0,0,0,9};
        int block3[] = {0,0,1,0};
        int block4[] = {0,0,9,0};
        int block5[] = {0,1,0,0};
        int block6[] = {0,9,0,0};
        int block7[] = {1,0,0,0};
        int block8[] = {9,0,0,0};

        State state11 = new State();
        state11.setArray(block1);
        blockedSet.add(state11);

        State state22 = new State();
        state22.setArray(block2);
        blockedSet.add(state22);

        State state33 = new State();
        state33.setArray(block3);
        blockedSet.add(state33);

        State state44 = new State();
        state44.setArray(block4);
        blockedSet.add(state44);

        State state55 = new State();
        state55.setArray(block5);
        blockedSet.add(state55);

        State state66 = new State();
        state66.setArray(block6);
        blockedSet.add(state66);

        State state77 = new State();
        state77.setArray(block7);
        blockedSet.add(state77);

        State state88 = new State();
        state88.setArray(block8);
        blockedSet.add(state88);
    }

    public static void createState(State state){
        State state1 = new State();
        state1.setArray(Arrays.copyOf(state.array,state.array.length));
        state1.increaseArray(0);
        state1.setCost(state.cost);
        state1.setFitness(1);
        addToOpenSet(state1, state);
        State state2 = new State();
        state2.setArray(Arrays.copyOf(state.array,state.array.length));
        state2.increaseArray(1);
        state2.setCost(state.cost);
        state2.setFitness(2);
        addToOpenSet(state2, state);
        State state3 = new State();
        state3.setArray(Arrays.copyOf(state.array,state.array.length));
        state3.increaseArray(2);
        state3.setCost(state.cost);
        state3.setFitness(3);
        addToOpenSet(state3, state);
        State state4 = new State();
        state4.setArray(Arrays.copyOf(state.array,state.array.length));
        state4.increaseArray(3);
        state4.setCost(state.cost);
        state4.setFitness(4);
        addToOpenSet(state4, state);

        State state5 = new State();
        state5.setArray(Arrays.copyOf(state.array,state.array.length));
        state5.decreaseArray(0);
        state5.setCost(state.cost);
        state5.setFitness(5);
        addToOpenSet(state5, state);
        State state6 = new State();
        state6.setArray(Arrays.copyOf(state.array,state.array.length));
        state6.decreaseArray(1);
        state6.setCost(state.cost);
        state6.setFitness(6);
        addToOpenSet(state6, state);
        State state7 = new State();
        state7.setArray(Arrays.copyOf(state.array,state.array.length));
        state7.decreaseArray(2);
        state7.setCost(state.cost);
        state7.setFitness(7);
        addToOpenSet(state7, state);
        State state8 = new State();
        state8.setArray(Arrays.copyOf(state.array,state.array.length));
        state8.decreaseArray(3);
        state8.setCost(state.cost);
        state8.setFitness(8);
        addToOpenSet(state8, state);
    }

}
