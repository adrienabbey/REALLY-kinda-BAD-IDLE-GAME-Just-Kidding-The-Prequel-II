/*
 * SFX Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Muahmmed Abushamma, et al., Mar. 2024
 */

import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*
 * Similar class to MusicPlayer. The Sound Effect class allows audio files in the .wav format to be played. Contains methods that play music, allow muting/unmuting of sound volume, one to set the volume of the sound, and one to stop all currently playing sound effects. The playMusic method creates a new thread whenever a new track starts playing, and terminates the thread whenever a new track starts playing. 
 *
 * This class is implemented so that sounds do not loop once finished, and multiple sounds can play at once. In order to fix the issue of a player repeadtedly activating sound effects, the stopAllSounds method is used to stop all currently playing sound effects in between activattions. Sometimes it is the case where multiple sound effects want to be play, for instance after the homestead purchase where mash clicking the purchase the button isn't possible, to which the stopAllSound method isn't used. 
 * 
 * TODO: extend SFX with MusicPlayer for simpler use.
 */
public class SFX extends MusicPlayer{

    /* Fields */

    // Keep track of the current clip
    // This is used to stop the current music when a new one starts
    private static Clip currentClip = null;
    private static Control volumeControl = null;
    public static boolean isMutedSFX = false;
    private static float currentVolumeSFX = -5.0f; // set volume
    private static Thread currentThread = null; // Reference to the thread associated with the current clip
    private static List<ClipWrapper> activeClips = new ArrayList<>(); // List to keep track of active sound clips, used for the stopAllSounds method. 

     /* Constructor */
     
    // sets the volume to a predefined amount at start of game
    public SFX() {
        volumeHelperSFX(currentVolumeSFX); 
    }

    /* Methods  */

    public static void playSound(String f) {
        playSound(f, false); // Call the overloaded method with loop set to false by default
    }

    /**
     * This method is designed for to handle sound effects that should loop by default. If the loop parameter is true, the sound will continuously loop until explicitly stopped.
     * 
     * Sound effects do not automically stop when another is played. Longer sound effects must be explicitly stopped during certain points of implementation to stop many sound effects from playing on top of each other. 
     * @param filPath  The filepath of the sound effect. 
     * @param loop     Flag used to determine if sfx should loop.
     */
    public static void playSound(String filePath, boolean loop) {
        // Create a new thread to play the music
        // This allows the music to play in the background without blocking the rest of the program
        new Thread(() -> {
            try { 
                // If the current thread is in use interrupt it. 
                if (currentThread != null) {
                    currentThread.interrupt(); // Interrupt the associated thread
                }
                // Create a File object with the provided file path
                File musicPath = new File(filePath);

                if (musicPath.exists()) {
                    // Create an AudioInputStream from the file
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                    Clip clip = AudioSystem.getClip(); // Get a clip resource
                    clip.open(audioInput); // Open the audio clip

                    if (loop == true) {
                        clip.loop(Clip.LOOP_CONTINUOUSLY); // Set the clip to loop continuously
                    }
                    clip.start(); // Start playing the audio clip

                    // Save the current clip to stop it when the next one is played
                    currentClip = clip;

                   // Save the reference to the current thread
                   currentThread = Thread.currentThread();

                    // Get the volume control
                    volumeControl = clip.getControl(FloatControl.Type.MASTER_GAIN);
                    // Set the initial volume (unmuted)
                    volumeHelperSFX(currentVolumeSFX);  
                    
                    // Add the clip to the list of active clips
                    activeClips.add(new ClipWrapper(clip, loop));
                    
                    if (isMutedSFX && !loop) {
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
        // Stop and flush all active clips
        for (ClipWrapper clipWrapper : activeClips) {
            clipWrapper.clip.stop();
            clipWrapper.clip.close();
        }
        // // Clear the list of active clips
        activeClips.clear();
    }

    public static void stopAllNonLoopingSounds() {
        for (ClipWrapper clipWrapper : activeClips) {
            if (!clipWrapper.loop && clipWrapper.clip.isRunning()) {
                clipWrapper.clip.stop();
                clipWrapper.clip.flush();
            }
        }
    }
    
    // Used to set volume to correct value during calls.
    public static void volumeHelperSFX(float value) {
        if (volumeControl != null) {
            ((FloatControl) volumeControl).setValue(value);
        }
    }

     // Method to stop the current sound from playing
     public static void stopSound() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.close();
        }
    }
    public static void setcurrentVolumeSFX(float volume) {
        currentVolumeSFX = volume;
    }

    // Inner class to wrap Clip and indicate if it should loop
    public static class ClipWrapper {
        public Clip clip;
        public boolean loop;

        public ClipWrapper(Clip clip, boolean loop) {
            this.clip = clip;
            this.loop = loop;
        }
    }
}
