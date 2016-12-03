package com.nasa.mars;

import com.nasa.mars.model.RoverAction;
import com.nasa.mars.model.RoverInPlateau;
import lombok.AllArgsConstructor;

/**
 * Created by rmamilla on 12/3/16.
 */
@AllArgsConstructor
public class RoverInPlateauRunnable implements Runnable{
    final RoverInPlateau roverInPlateau;
    final String instructions;
    final Object lock;
    @Override
    public void run() {
        System.out.println("Thread started: " + Thread.currentThread().getName());

        synchronized (lock){
            while(roverInPlateau.getRover().getRoverNumber() != App.roverOrder) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    //
                }
            }
            System.out.println("Rover " + roverInPlateau.getRover().getRoverNumber() + " is moving" + " : " + Thread.currentThread().getName());
            for(int i = 0; i<instructions.length();i++){
                roverInPlateau.performAction(RoverAction.valueOf(instructions.charAt(i)));
            }
            System.out.println("Rover " + roverInPlateau.getRover().getRoverNumber() + " moving is complete" + " : " + Thread.currentThread().getName());

            App.roverOrder = 0;
            lock.notifyAll();
        }



    }
}
