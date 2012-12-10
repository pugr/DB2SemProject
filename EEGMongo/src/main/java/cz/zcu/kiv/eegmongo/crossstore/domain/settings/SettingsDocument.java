package cz.zcu.kiv.eegmongo.crossstore.domain.settings;

import cz.zcu.kiv.eegmongo.crossstore.domain.Scenario;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 16.5.12
 */
@Document
public class SettingsDocument extends Scenario {

    private String audioSource;
    private String audioCompression;
    private String videoSource;
    private String videoCompression;

    public String getAudioSource() {
        return audioSource;
    }

    public void setAudioSource(String audioSource) {
        this.audioSource = audioSource;
    }

    public String getAudioCompression() {
        return audioCompression;
    }

    public void setAudioCompression(String audioCompression) {
        this.audioCompression = audioCompression;
    }

    public String getVideoSource() {
        return videoSource;
    }

    public void setVideoSource(String videoSource) {
        this.videoSource = videoSource;
    }

    public String getVideoCompression() {
        return videoCompression;
    }

    public void setVideoCompression(String videoCompression) {
        this.videoCompression = videoCompression;
    }
}
