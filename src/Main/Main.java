
package Main;

import DesignPatterns.*;
import circusgame.CircusGame;
import eg.edu.alexu.csd.oop.game.GameEngine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import java.io.File;


public class Main {
   static Clip clip=SoundPlayer.playSound("circus.wav");
    public static void main(String[] args){
        JMenuBar  menuBar = new JMenuBar();
		JMenu menu = new JMenu("Options");
		JMenuItem pauseMenuItem = new JMenuItem("Pause");
		JMenuItem resumeMenuItem = new JMenuItem("Resume");
                JMenuItem PlayMenuItem = new JMenuItem("PlaySound");
                JMenuItem StopMenuItem = new JMenuItem("StopSound");
                
		menu.add(pauseMenuItem);
		menu.add(resumeMenuItem);
                menu.add(PlayMenuItem);
                menu.add(StopMenuItem);
                
		menuBar.add(menu);
		final GameEngine.GameController gameController = GameEngine.start("Circus Game", CircusGame.getInstance(new HardLevel()), menuBar);
                pauseMenuItem.addActionListener(new ActionListener() {
		@Override public void actionPerformed(ActionEvent e) {
				gameController.pause();
			}
		});
		resumeMenuItem.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				gameController.resume();
			}
		});
                PlayMenuItem.addActionListener(new ActionListener() {
		@Override public void actionPerformed(ActionEvent e) {
                    if(!clip.isRunning()){
                        clip = SoundPlayer.playSound("circus.wav");
                        clip.start();
                    }
			}
		});
                StopMenuItem.addActionListener(new ActionListener() {
		@Override public void actionPerformed(ActionEvent e) {
			if(clip!=null)
                        clip.stop();
			}
		});
		menuBar.add(menu);
               

    }
    public class SoundPlayer {
    public static Clip playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Add a listener to handle the clip's completion
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        event.getLine().close(); // Close the line after playing
                    }
                }
            });

            clip.start(); // Start playing the sound
            return clip;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    }
}