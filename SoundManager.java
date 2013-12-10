import java.util.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;

public class SoundManager {


	public  static void main(String[] args) {

		while(true) {

			if(r.nextInt(10000) < 1)
			{
				System.out.println("c");
				playRandomSound();
			}

		}
	}

	final static int NUM_SOUNDS = 7;

	static Random r = new Random();

	private static Clip[] soundFiles = new Clip[NUM_SOUNDS];



	//reads in all the zombie sound files by looping through regularly named sound files
	static {
		for(int index = 1; index <= NUM_SOUNDS; index++){
   			try {
   				  soundFiles[index - 1] = AudioSystem.getClip( );
   				  AudioInputStream is = AudioSystem.getAudioInputStream(new File("zom" + index + ".wav"));
   				  soundFiles[index - 1].open(is);
   			} catch(Exception e) {
				System.out.println(index);
				soundFiles[index - 1] = null;
				System.out.println(e);
   			}
		}

	}

	public static void playRandomSound() {
		try{
			int soundNum = r.nextInt(NUM_SOUNDS);
			soundFiles[soundNum].stop();
			soundFiles[soundNum].setFramePosition(0);
			soundFiles[soundNum].start();
		} catch (Exception e) {
			System.out.println(e);
		}

	}




}