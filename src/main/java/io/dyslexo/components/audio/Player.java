package io.dyslexo.components.audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

/**
 * Created by stefano on 05/02/2017.
 */
public class Player extends Thread {
    private Clip clip;
    private boolean isClipPaused;
    private long timeClipPaused;
    private boolean isStopped;

    private BlockingQueue<Optional<AudioInputStream>> queue;
    private AudioInputStream audioInputStream;

    public Player() {
        super("Dyslexo Player");
        try {
            this.clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        this.isClipPaused = false;
        this.timeClipPaused = 0;
        this.isStopped = false;
    }

    public Player(AudioInputStream audioInputStream) {
        super("Dyslexo Player");
        try {
            this.clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        this.audioInputStream = audioInputStream;

        this.isClipPaused = false;
        this.timeClipPaused = 0;
        this.isStopped = false;
    }

    public Player(BlockingQueue<Optional<AudioInputStream>> queue) {
        super("Dyslexo Player");
        this.isClipPaused = false;
        this.timeClipPaused = 0;
        this.queue = queue;

        try {
            this.clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        this.isStopped = false;
    }

    public void run() {
        if(audioInputStream != null)
            try {
                play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        else {
            try {
                audioInputStream = queue.take().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clip.addLineListener(e -> {
                if(e.getType() == LineEvent.Type.STOP && !isClipPaused && !isStopped){
                    try {
                        audioInputStream = queue.take().get();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    clip.close();
                    try {
                        play();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            try {
                play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void play() throws IOException, LineUnavailableException {
        if(isClipPaused) {
            isClipPaused = false;
            clip.setMicrosecondPosition(timeClipPaused);
        }
        else
            clip.open(audioInputStream);
        clip.start();
    }

    public void pause() {
        timeClipPaused = clip.getMicrosecondPosition();
        isClipPaused = true;
        clip.stop();
    }

    public void clear() {
        isStopped = true;
        clip.stop();
        clip.close();
    }

    public void setQueue(BlockingQueue<Optional<AudioInputStream>> queue) {
        this.queue = queue;
    }

    public void setAudioInputStream(AudioInputStream audioInputStream) {
        this.audioInputStream = audioInputStream;
    }

    public boolean isPlaying() {
        return clip.isActive();
    }

    public boolean isPaused() {
        return isClipPaused;
    }
}
