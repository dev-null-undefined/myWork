import java.util.Scanner;

import javax.sound.sampled.*;
import javax.sound.sampled.AudioFormat.Encoding;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		AudioFormat format=new AudioFormat(new Encoding("PCM_FLOAT"), 44100, 24, 2, 0, 24, false);
//		TargetDataLine line;
//		DataLine.Info info = new DataLine.Info(TargetDataLine.class, 
//		    format); // format is an AudioFormat object
//		if (!AudioSystem.isLineSupported(info)) {
//		    // Handle the error.
//			System.out.println("Fuck of ");
//		    }
//		System.out.println("Obtain ");
//		try {
//		    line = (TargetDataLine) AudioSystem.getLine(info);
//		    line.open(format);
//			System.out.println("Opened ");
//			line.close();
//		} catch (LineUnavailableException ex) {
//		        // Handle the error.
//		    //... 
//		}
		Port line;
		if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
		    try {
		        line = (Port) AudioSystem.getLine(Port.Info.MICROPHONE);
		        line.open();
		        line.addLineListener(new LineListener() {
					
					@Override
					public void update(LineEvent event) {
						// TODO Auto-generated method stub

						System.out.println("Opened ");
					}
				});
				System.out.println("Opened ");
		    }catch (Exception ex) {
				System.out.println("Fuck of ");
		    }
		}
		(new Scanner(System.in)).nextLine();
		
	}

}
