import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SFX {
    // Keep track of the current clip
    // This is used to stop the current music when a new one starts
    private static Clip currentClip = null;
    private static FloatControl volumeControl = null;
    public static boolean isMutedSFX = false;
    private static float currentVolumeSFX = -16.0f;
    private static Thread currentThread = null; // Reference to the thread associated with the current clip
    private static List<Clip> activeClips = new ArrayList<>(); // List to keep track of active sound clips, used for the stopAllSounds method. 

    public static void setcurrentVolumeSFX(float volume) {
        currentVolumeSFX = volume;
    }

    public SFX() {
        volumeHelperSFX(currentVolumeSFX); 
    }

    public static void playSound(String filePath) {
        // Create a new thread to play the music
        // This allows the music to play in the background without blocking the rest of the program
        new Thread(() -> {
            try {
                // If there's a clip playing, stop it before starting the new one
                // This ensures that only one music track plays at a time.
                if (currentClip != null && currentClip.isRunning()) {
                    currentClip.stop();
                    // If the current thread is in use interrupt it. 
                    if (currentThread != null) {
                        currentThread.interrupt(); // Interrupt the associated thread
                    }
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

                   // Save the reference to the current thread
                   currentThread = Thread.currentThread();

                    // Get the volume control
                    volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    // Set the initial volume (unmuted)
                    volumeHelperSFX(currentVolumeSFX);  
                    
                    // Add the clip to the list of active clips
                    activeClips.add(clip);
                    
                    if (isMutedSFX) {
                        volumeHelperSFX(-70.0f);
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

    // Method to toggle volume for the java slider.
    public static void setVolumeSFX(float volume) {
        if (currentClip != null && currentClip.isRunning() && isMutedSFX == false) {
            FloatControl volumeControl = (FloatControl) currentClip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume);
            setcurrentVolumeSFX(volume);
        }
    }

    // ***Might use later for a Mute SFX button***
    // public static void toggleMuteSFX() {
    //     if (volumeControl != null) {
    //         if (isMutedSFX) {
    //             // Unmute (set volume to audible volume)
    //             setcurrentVolumeSFX(currentVolumeSFX);
    //             isMutedSFX = false;
    //         } else {
    //             // Store the current volume before muting
    //             currentVolumeSFX = volumeControl.getValue();
    //             // Mute (set volume to minimum volume)
    //            setcurrentVolumeSFX(-70.0f);
    //             isMutedSFX = true;
    //         }
    //     }
    // }
    
    public static void stopAllSounds() {
        // Stop and close all active clips
        for (Clip clip : activeClips) {
            clip.stop();
            clip.flush();
        }
        // Clear the list of active clips
        activeClips.clear();
    }

    // Used to set volume to correct value during calls.
    public static void volumeHelperSFX(float value) {
        if (volumeControl != null) {
            volumeControl.setValue(value);
        }
    }

     // Method to stop the current sound from playing
     public static void stopSound() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.close();
        }
    }
}
