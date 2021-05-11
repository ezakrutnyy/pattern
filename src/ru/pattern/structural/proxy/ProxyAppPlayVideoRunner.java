package ru.pattern.structural.proxy;

/**
 * Created by Евгений on 10.12.2016.
 */
public class ProxyAppPlayVideoRunner {
    public static void main(String[] args) {
        String path = "Один дома 3";
        //PlayAllInterface film = new PlayVideoServiceImpl(path);
        PlayAllInterface film = new PlayVideoServiceProxy(path);
        film.play();
    }
}

interface PlayAllInterface {
    void play();
}

class PlayVideoServiceImpl implements PlayAllInterface {
    String path;

    public PlayVideoServiceImpl(String path) {
        this.path = path;
        load();
    }

    public void load() {
        System.out.println(String.format("Загрузка фильма... %s", path));
    }

    @Override
    public void play() {
        System.out.println(String.format("Начинаем смотреть фильм %s", path));
    }
}

class PlayVideoServiceProxy implements PlayAllInterface {
    String path;
    PlayVideoServiceImpl realVideo;

    public PlayVideoServiceProxy(String path) {
        this.path = path;
    }

    @Override
    public void play() {
        if (realVideo == null) {
            realVideo = new PlayVideoServiceImpl(this.path);
        }
        realVideo.play();
    }
}