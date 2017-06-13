package ru.pattern.structural.adapter.example2;

/**
 * Created by Евгений on 09.01.2017.
 */
public class AdapterPatternPlayerDemo {
    public static void main(String[] args) {
        MediaPlayer player = new AudioPlayer();
        player.play("mp3", "pesnya.mp3");
        player.play("mp4", "harrypotter.mp4");
        player.play("vlc", "cinema.vlc");
        player.play("avi", "oldcineme.avi");
    }
}

interface MediaPlayer {
    void play(String typeFile, String nameFile);
}

class AudioPlayer implements  MediaPlayer {

    MediaAdapter adapter;

    @Override
    public void play(String typeFile, String nameFile) {
        if (typeFile.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file. Name: "+nameFile);
        } else if (typeFile.equalsIgnoreCase("mp4") || typeFile.equalsIgnoreCase("vlc")) {
            adapter = new MediaAdapter(typeFile);
            adapter.play(typeFile, nameFile);
        } else {
            System.out.println("Invalid media. " + typeFile + " format not supported");
        }
    }
}



interface AdvancedMediaPlayer {
    void playVlc(String nameFile);
    void playMp4(String nameFile);
}

class VlcPlayer implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String nameFile) {
        System.out.println("Playing vlc file. Name: "+nameFile);
    }

    @Override
    public void playMp4(String nameFile) {

    }
}

class Mp4Player implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String nameFile) {

    }

    @Override
    public void playMp4(String nameFile) {
        System.out.println("Playing mp4 file. Name: "+nameFile);
    }
}

class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedPlayer;

    public MediaAdapter(String typeFile) {
        if (typeFile.equalsIgnoreCase("vlc")) {
            advancedPlayer = new VlcPlayer();
        } else if (typeFile.equalsIgnoreCase("mp4")) {
            advancedPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String typeFile, String nameFile) {
        if (typeFile.equalsIgnoreCase("vlc")) {
            advancedPlayer.playVlc(nameFile);
        } else if (typeFile.equalsIgnoreCase("mp4")) {
            advancedPlayer.playMp4(nameFile);
        }
    }
}

