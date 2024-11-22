package DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * DTO for storing and sending only variables connected to light settings.
 * Will use the core DTO when the bug is fixed but for now this one is needed for receiving ChangeLightDTO through
 * socket
 */
public class ChangeLightDTO implements Serializable {
    private String lightPattern;
    private int rgb;
    private int lightStrength;

    public ChangeLightDTO(){

    }

    public ChangeLightDTO(
            @JsonProperty("lightPattern") String lightPattern,
            //TODO:Fix bug where rgb requires to be attached to color
            @JsonProperty("color") int rgb,
            @JsonProperty("lightStrength") int lightStrength) {
        this.lightPattern = lightPattern;
        this.rgb = rgb;
        this.lightStrength = lightStrength;
    }

    @Override
    public String toString() {
        return "SendSmartLightDTO{" +
                "lightPattern='" + lightPattern + '\'' +
                ", rgb=" + rgb +
                ", lightStrength=" + lightStrength +
                '}';
    }

    public String getLightPattern() {
        return lightPattern;
    }

    public void setLightPattern(String lightPattern) {
        this.lightPattern = lightPattern;
    }

    public int getRgb() {
        return rgb;
    }

    public void setRgb(int rgb) {
        this.rgb = rgb;
    }

    public int getLightStrength() {
        return lightStrength;
    }

    public void setLightStrength(int lightStrength) {
        this.lightStrength = lightStrength;
    }

    public void setAllVariables(String lightPattern, int rgb, int lightStrength){
        this.lightPattern = lightPattern;
        this.rgb = rgb;
        this.lightStrength = lightStrength;
    }
}
