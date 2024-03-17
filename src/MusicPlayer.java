import javax.sound.sampled.*;
import java.io.File;

public class MusicPlayer {
    // Keep track of the current clip
    // This is used to stop the current music when a new one starts
    private static Clip currentClip = null;
    private static FloatControl volumeControl = null;
    private static boolean isMuted = false;
    private static float currentVolume = 0.0f;

    public static void setcurrentVolume(float volume) {
        currentVolume = volume;
    }

    public MusicPlayer() {
        volumeHelper(currentVolume); 
    }

    public static void playMusic(String filePath) {
        // Create a new thread to play the music
        // This allows the music to play in the background without blocking the rest of the program
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
                    clip.loop(Clip.LOOP_CONTINUOUSLY); // This line makes the song repeat itself
                    clip.start(); // Start playing the audio clip

                    // Save the current clip
                    // This allows us to stop it when a new one starts
                    currentClip = clip;

                    // Get the volume control
                    volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    // Set the initial volume (unmuted)
                    volumeHelper(currentVolume);     
                    
                    if (isMuted == true) {
                        volumeHelper(-70.0f);
                    }         

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

    // Method to mute/unmute the volume
    public static void toggleMute() {
        if (volumeControl != null) {
            if (isMuted) {
                // Unmute (set volume to audible volume)
                volumeHelper(currentVolume);
                isMuted = false;
            } else {
                // Store the current volume before muting
                currentVolume = volumeControl.getValue();
                // Mute (set volume to minimum volume)
                volumeHelper(-70.0f);
                isMuted = true;
            }
        }
    }

    // Method to toggle volume for the java slider.
    public static void setVolume(float volume) {
        if (currentClip != null && currentClip.isRunning() && isMuted == false) {
            FloatControl volumeControl = (FloatControl) currentClip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume);
            setcurrentVolume(volume);
        }
    }

    // Used to set volume to correct value during calls.
    public static void volumeHelper(float value) {
        if (volumeControl != null) {
            volumeControl.setValue(value);
        }
    }
}

