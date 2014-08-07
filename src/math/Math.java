package math;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PK
 */
public class Math {

    private static ArrayList<ArrayList<Integer>> listOfLists;
    private static ArrayList<Integer> list;
    private static ArrayList<String> counter;
    private static int upperLimit;
    private static int checkFrom;
    private static int amountOfThreads;
    private static Math math;
    private static ArrayList<Thread> threads;

    private static Date startTime;
    private static Date endTime;
    private static float calculationTime;
    private static float checkTime;
    
    public Math() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        counter = new ArrayList<>();
        listOfLists = new ArrayList<>();
        threads = new ArrayList<>();
        upperLimit = 2350000;
        checkFrom = 2349999;
        amountOfThreads = 2;

        math = new Math();

        //Create multiplication list
        int numbersAdded = 0;
        startTime = new Date();        
        for (int multiplier = 3; multiplier <= upperLimit; multiplier += 2) {

            list = new ArrayList<>();
            for (int amount = 1; amount * multiplier <= upperLimit; amount++) {
                list.add(amount * multiplier);
                numbersAdded++;
            }

            listOfLists.add(list);
            System.out.println(multiplier + " added");
        }
        endTime = new Date();
        calculationTime = (endTime.getTime() - startTime.getTime()) / 1000;

        math.runThreads(amountOfThreads);
        math.joinThreads();
        checkTime = (endTime.getTime() - startTime.getTime()) / 1000;
        

//
//        
//        //Check for correct number
//        for (int amount = checkFrom; amount <= upperLimit; amount += 2) {
//            ArrayList<Integer> sum = new ArrayList<>();
//            int currentMultiplier = 3;
//            for (ArrayList<Integer> l : listOfLists) {
//
//                for (int i : l) {
//                    if (i == amount) {
//                        sum.add(currentMultiplier);
//                    }
//                }
//                currentMultiplier += 2;
//            }
//            int endSum = 0;
//            for (int i : sum) {
//                endSum += i;
//            }
//            if (amount == endSum - amount + 1) {
//                counter.add(amount + " FOUND");
//                System.out.println("FOUND" + amount);
//                break;
//            }
////            counter.add(amount + " - " + (endSum - amount + 1));
//            System.out.println(amount + " Checked");
//        }
//        
        System.out.println("done");
        for (String s : counter) {
            System.out.println(s);
        }
        System.out.println(numbersAdded + " numbers calculated in " + calculationTime + " seconds. " + (numbersAdded / calculationTime) + " per second");
        System.out.println("" + ((upperLimit - checkFrom - 1)/2) + " numbers checked in " + checkTime + " seconds. " + ((upperLimit - checkFrom - 1)/2)/checkTime + " per second");
        System.out.println(numbersAdded + (((upperLimit - checkFrom - 1)/2) * numbersAdded) + " numbers iterated through");
    }

    public void runThreads(int amount) {
        for (int i = 1; i <= amount; i++) {
            System.out.println(i);
            threads.add(new Thread(new Calculation(listOfLists, upperLimit, checkFrom + ((i - 1) * 2), math, amount, "Thread " + i)));
            System.out.println("Thread " + i + " started");
        }

        for (Thread t : threads) {
            t.start();
        }
    }

    public void addResult(int i) {
        counter.add("FOUND" + i);
    }

    public void stopAllThreads() {
        for (Thread t : threads) {
            t = null;
        }
    }
    
    public void joinThreads(){
        startTime = new Date();
        for(Thread t : threads){
            if(t != null){
                try {
                    t.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Math.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        endTime = new Date();
    }

}
