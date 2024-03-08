import javax.sound.sampled.*;
import java.io.File;

public class MusicPlayer {
    // Keep track of the current clip
    // This is used to stop the current music when a new one starts
    private static Clip currentClip = null;

    public static void playMusic(String filePath) {
        // Create a new thread to play the music
        // This allows the music to play in the background without        blocking the rest of your program
        new Thread(() -> {
            try {
                 // If there's a clip playing, stop it before starting the new one
                // This ensures that only one music track plays at a time
                if (currentClip != null && currentClip.isRunning()) {
                    currentClip.stop();
                }

                // Create a File object with the provided file path
                File musicPath = new File(filePath);

                if (musicPath.exists()) {
                    // Create an AudioInputStream from the file
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                    Clip clip = AudioSystem.getClip(); // Get a clip resource
                    clip.open(audioInput); // Open the audio clip
                    clip.start(); // Start playing the audio clip

                    // Save the current clip
                    // This allows us to stop it when a new one starts
                    currentClip = clip;

                } else {
                    System.out.println("Can't find file");
                }
            } catch (Exception ex) {
                // If there's an exception, print the stack trace for debugging
                ex.printStackTrace();
            }
        // Start the new thread
        }).start();
    }
}



