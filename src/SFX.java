import javax.sound.sampled.*;
import java.io.File;

public class SFX {
    private Clip clip;
    private FloatControl volumeControl;
    private boolean isMuted = false;
    private float currentVolume = 0.0f;

    public SFX(String filePath) {
        try {
            // Create a File object with the provided file path
            File musicPath = new File(filePath);

            if (musicPath.exists()) {
                // Create an AudioInputStream from the file
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip(); // Get a clip resource
                clip.open(audioInput); // Open the audio clip
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); // Get the volume control
            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception ex) {
            // If there's an exception, print the stack trace for debugging
            ex.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
            setVolume(currentVolume); // Set initial volume
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void setVolume(float volume) {
        if (volumeControl != null) {
            float maxVolume = volumeControl.getMaximum();
            float minVolume = volumeControl.getMinimum();
            float adjustedVolume = minVolume + (maxVolume - minVolume) * (volume / 100.0f);
            volumeControl.setValue(adjustedVolume);
            currentVolume = volume;
        }
    }

    public void toggleMute() {
        if (volumeControl != null) {
            if (isMuted) {
                // Unmute (set volume to audible volume)
                setVolume(currentVolume);
                isMuted = false;
            } else {
                // Store the current volume before muting
                currentVolume = volumeControl.getValue();
                // Mute (set volume to minimum volume)
                setVolume(-70.0f);
                isMuted = true;
            }
        }
    }
}
