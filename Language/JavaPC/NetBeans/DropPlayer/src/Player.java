
import com.sun.javafx.application.PlatformImpl;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFileChooser;

/**
 *
 * @author marti
 */
public class Player {

    private static final Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        try {
            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(null);
            File f = fc.getSelectedFile();
            if (f == null) {
                System.out.println("FUCK YOU!!");
                System.exit(0);
            }
            System.out.println("Zadejte kdy je drop HH:mm:ss:SSS    :");
            String stringDate = sc.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss:SSS");
            Date date = format.parse(stringDate);
            date.setHours(date.getHours() + 1);
            System.out.println("Zadejte kdy chcete drop yyyy-MM-dd HH:mm:ss    :");
            stringDate = sc.nextLine();
            Date date2 = new Date();
            if (!stringDate.equals("")) {
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date2 = format.parse(stringDate);
            }
            long time = date2.getTime() - date.getTime();
            System.out.println("time:" + time);
            System.out.println("Now:" + (new Date()).toString() + "   Time:" + (new Date(time)).toString());
            Thread.sleep(time - (new Date()).getTime());
            play(f);
        } catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void play(File f) {
        System.out.println("Playing");

        /*PlatformImpl.startup(() -> {
         MediaPlayer player = new MediaPlayer(new Media(f.toURI().toString()));
         player.play();
         });*/
        new Thread(new Runnable() {
            public void run() {
                try {
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(f);
                    Clip clip = AudioSystem.getClip();
                    clip.open(inputStream);
                    clip.start();
                    Thread.sleep(clip.getMicrosecondLength());
                } catch (Exception ex) {
                }
            }
        }).start();
    }
}
