package application;

import java.io.File;
import javax.sound.sampled.*;

public class SoundPlayer{
    public static void playSound(String soundFile) {
        try {
            // Load the audio input stream using a relative path
            File file = new File("src/sounds/" + soundFile);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

