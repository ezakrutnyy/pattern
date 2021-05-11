package ru.pattern.behavioral.momento;

import java.util.Stack;

public class SpeedMomentoRunner {

    public static void main(String[] args) throws InterruptedException {

        Stack<Speedometor.SpeedSnapshot> history = new Stack<>();

        System.out.println("Started...");
        Speedometor speedometor = new Speedometor();
        speedometor.setSpeed(30);
        history.push(speedometor.saveSpeed());
        System.out.println("Current speed: " + speedometor);

        System.out.println("Increasing speed");
        speedometor.setSpeed(70);
        history.push(speedometor.saveSpeed());
        System.out.println("Current speed: " + speedometor);

        System.out.println("Increasing speed");
        speedometor.setSpeed(100);
        history.push(speedometor.saveSpeed());
        System.out.println("Current speed: " + speedometor);


        Thread.sleep(3000);

        System.out.println("brake.......");


        System.out.println(history);
        history.pop().restoreSpeed();
        System.out.println("Current speed: " + speedometor);
        history.pop().restoreSpeed();
        System.out.println("Current speed: " + speedometor);
        history.pop().restoreSpeed();
        System.out.println("Current speed: " + speedometor);


    }
}


class Speedometor {

    private int speed;

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public SpeedSnapshot saveSpeed() {
        return new SpeedSnapshot();
    }

    public final class SpeedSnapshot {

        private final int momentoSpeed;

        private SpeedSnapshot() {
            this.momentoSpeed = speed;
        }

        public void restoreSpeed() {
            speed = this.momentoSpeed;
        }
    }

    @Override
    public String toString() {
        return "Speedometor{" +
                "speed=" + speed +
                '}';
    }
}