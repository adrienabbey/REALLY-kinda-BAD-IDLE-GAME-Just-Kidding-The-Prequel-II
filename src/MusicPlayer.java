// Import the necessary libraries for audio processing and file handling
import javax.sound.sampled.*;
import java.io.File;

public class MusicPlayer {

    // Method to play music
    public static void playMusic(String filePath) {
        // Create a new thread to play the music
        new Thread(() -> {
            try {
                // Create a File object with the provided file path
                File musicPath = new File(filePath);

                // Check if the file exists
                if (musicPath.exists()) {
                    // Create an AudioInputStream from the file
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                    // Get a clip resource to play the audio
                    Clip clip = AudioSystem.getClip();
                    // Open the audio clip
                    clip.open(audioInput);
                    // Start playing the audio clip
                    clip.start();

                } else {
                    // If the file doesn't exist, print an error message
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
