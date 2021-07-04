package varios;

import javax.sound.sampled.Clip; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.AudioInputStream; 


import java.net.*; 
import javax.swing.*; 


class PlayClip { 
  public static void main(String[] args) throws Exception { 
    URL soundLocation = new URL( 
      "file:C:/Users/TOSHIBA/Documents/Eclipse%20workspace/varios/sound.wav"); 
    // can use one clip many times 
    Clip clip = AudioSystem.getClip(); 
    AudioInputStream inputStream = 
      AudioSystem.getAudioInputStream(soundLocation); 
    clip.open( inputStream ); 
    clip.loop(50); 
    clip.start(); 
    // kludge to prevent the main() from exiting. 
    JFrame f = new JFrame(); 
    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
    f.getContentPane().add( new 
      JLabel(soundLocation.toURI().toString()) ); 
    f.pack(); 
    f.setVisible(true); 
  } 


} 
