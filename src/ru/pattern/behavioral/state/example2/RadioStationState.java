package ru.pattern.behavioral.state.example2;

/**
 * Created by Евгений on 19.01.2017.
 */
public class RadioStationState {
    public static void main(String[] args) {

        RadioStation station = new RadioStation();
        station.setStation(new VestiFM());

        for (int i = 0; i < 10; i++) {
            station.play();
        }
    }
}

class RadioStation {
    State state;

    public void setStation(State state) {
        this.state = state;
    }

    void play() {
        state.play(this);
    }
}

interface State {
    void play(RadioStation context);
}

class Radio7 implements State {

    @Override
    public void play(RadioStation context) {
        System.out.println("Играет классическая музыка....");
        context.setStation(new VestiFM());
    }
}

class VestiFM implements State {
    @Override
    public void play(RadioStation context) {
        System.out.println("В Москве полдень....");
        context.setStation(new Shanson());
    }
}

class Shanson implements State {
    @Override
    public void play(RadioStation context) {
        System.out.println("Играет радио шансон");
        context.setStation(new Radio7());
    }
}