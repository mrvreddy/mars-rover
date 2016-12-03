package com.nasa.mars;

import com.nasa.mars.model.*;
import lombok.AllArgsConstructor;

/**
 * Created by rmamilla on 12/3/16.
 */
public class App {
    //Adding a global variable for simplicity of co ordinating b/w threads
    public volatile static int roverOrder = 0;

    public static void main(String[] s){
        final Plateau plateau = new Plateau(5, 5);

        //Rover 1
        final Rover rover1 = new Rover(1, new Position(1, 2, Position.Direction.NORTH));
        final RoverInPlateau roverInPlateau1 = new RoverInPlateau(rover1, plateau);
        final String instructions1 = "LMLMLMLMM";

        final Rover rover2 = new Rover(2, new Position(3, 3, Position.Direction.EAST));
        final RoverInPlateau roverInPlateau2 = new RoverInPlateau(rover2, plateau);
        final String instructions2 = "MMRMMRMRRM";
        final Object lock = new Object();
        Thread t1 =new Thread(new RoverInPlateauRunnable(roverInPlateau1, instructions1, lock), "Thread-1");
        Thread t2 = new Thread(new RoverInPlateauRunnable(roverInPlateau2, instructions2, lock), "Thread-2");

        //Introduced a rover order concept and externalized to another thread
        Thread roverOrderThread = new Thread(new RoverOrderRunnable(new int[]{1, 2}, lock), "Rover-order");
        roverOrderThread.start();

        //Staring thread 1
        t2.start();
        try {
            Thread.sleep(1000l);
        }catch (InterruptedException ie){
            //ignoring the exception
        }
        t1.start();


        try {
            t1.join();
            t2.join();
        }catch (InterruptedException ie){
            //ignoring the exception
        }

        System.out.println("New coordinates:" + rover1);
        System.out.println("New coordinates:" + rover2);

    }

    @AllArgsConstructor
    public static class RoverOrderRunnable implements Runnable{
        final int[] roverNumberOrder;
        final Object lock;

        @Override
        public void run() {
            System.out.println("Thread started: " + Thread.currentThread().getName());

            int i = 0;
            while (i < roverNumberOrder.length) {
                synchronized (lock) {
                    while (roverOrder != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            //
                        }
                    }
                    roverOrder = roverNumberOrder[i++];
                    System.out.println(Thread.currentThread().getName() + " : assigned next rover to run is " + roverOrder );
                    lock.notifyAll();
                }
            }
        }
    }
}
