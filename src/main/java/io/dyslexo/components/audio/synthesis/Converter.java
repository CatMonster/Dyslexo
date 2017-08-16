package io.dyslexo.components.audio.synthesis;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

/**
 * Created by stefano on 10/02/2017.
 */
public class Converter {

    private AudioInputStream concat(BlockingQueue<Optional<AudioInputStream>> queue, int queueLength) throws InterruptedException {
        AudioInputStream temp = null;
        for(int i = 0; i < queueLength ; i++) {
            try {
                if(queueLength == 1)
                    return queue.take().get();
                else if(queueLength > 1 && temp == null)
                    temp = queue.take().get();
                else {
                    AudioInputStream audioInputStream = queue.take().get();
                    temp = new AudioInputStream(new SequenceInputStream(temp, audioInputStream), temp.getFormat(), temp.getFrameLength() + audioInputStream.getFrameLength());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

    public void toWAW(String path, BlockingQueue<Optional<AudioInputStream>> queue, int queueLength) throws InterruptedException, UnsupportedAudioFileException, IOException {
        AudioSystem.write(concat(queue, queueLength), AudioFileFormat.Type.WAVE, new File(path));
    }
}
