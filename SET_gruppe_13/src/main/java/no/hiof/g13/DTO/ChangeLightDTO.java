package no.hiof.g13.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import no.hiof.g13.interfaces.GenericDeviceDTO;

import java.awt.*;
import java.io.Serializable;

public class ChangeLightDTO implements GenericDeviceDTO, Serializable {
    private String lightPattern;
    private int rgb;
    private int lightStrength;

    public ChangeLightDTO(){

    }

    public ChangeLightDTO(
            @JsonProperty("lightPattern") String lightPattern,
            @JsonProperty("rgb") int rgb,
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
