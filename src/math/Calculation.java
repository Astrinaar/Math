package math;

import java.util.ArrayList;

/**
 *
 * @author PK
 */
public class Calculation implements Runnable {

    private ArrayList<ArrayList<Integer>> listOfLists;
    private int upperLimit;
    private int checkFrom;
    private Math math;
    private int amountOfThreads;
    private String name;
    private boolean running = true;
    

    public Calculation(ArrayList<ArrayList<Integer>> listOfLists, int upperLimit, int checkFrom, Math math, int amountOfThreads, String name) {
        this.listOfLists = listOfLists;
        this.upperLimit = upperLimit;
        this.checkFrom = checkFrom;
        this.math = math;
        this.amountOfThreads = amountOfThreads;
        this.name = name;
    }

    @Override
    public void run() {
        while (running) {
            //Check for correct number
            for (int amount = checkFrom; amount <= upperLimit; amount += 2*amountOfThreads) {
                ArrayList<Integer> sum = new ArrayList<>();
                int currentMultiplier = 3;
                for (ArrayList<Integer> l : listOfLists) {

                    for (int i : l) {
                        if (i == amount) {
                            sum.add(currentMultiplier);
                        }
                    }
                    currentMultiplier += 2;
                }
                int endSum = 0;
                for (int i : sum) {
                    endSum += i;
                }
                if (amount == endSum - amount + 1) {
                    math.addResult(amount);
                    System.out.println("FOUND" + amount);
                    math.stopAllThreads();
                    break;
                }
//            counter.add(amount + " - " + (endSum - amount + 1));
                System.out.println(amount + " Checked by " + name);
            }
            stop();
        }
    }

    public void stop() {
        running = false;
    }

}
