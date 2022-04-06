package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    static Clip clip;
    URL soundURL[] = new URL[30];
    
    public Sound() {

        soundURL[0] = getClass().getResource("/res/sound/World1.wav");
        soundURL[1] = getClass().getResource("/res/sound/6ABoots.wav");
        soundURL[2] = getClass().getResource("/res/sound/98DoorEcho.wav");
        soundURL[3] = getClass().getResource("/res/sound/c7ChestClose.wav");
        soundURL[4] = getClass().getResource("/res/sound/D1Ting.wav");
        soundURL[5] = getClass().getResource("/res/sound/Fanfare.wav");
        soundURL[6] = getClass().getResource("/res/sound/MenuMusic.wav");
        soundURL[7] = getClass().getResource("/res/sound/02WhiteMagic.wav");
        soundURL[8] = getClass().getResource("/res/sound/00BlueMagic.wav");
        soundURL[9] = getClass().getResource("/res/sound/14 - Eden.wav");
        soundURL[10] = getClass().getResource("/res/sound/10Forest.wav");
        soundURL[11] = getClass().getResource("/res/sound/20Light.wav");
        soundURL[12] = getClass().getResource("/res/sound/00Sanct.wav");
        soundURL[13] = getClass().getResource("/res/sound/pizza.wav");
    }
    public void setFile(int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);


        }catch(Exception e) {

        }

    }

    public void play() {
        
        clip.start();

    }
    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        
        clip.stop();

    }
}
