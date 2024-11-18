package models.DTO;

import java.awt.*;
import java.io.Serializable;

public class SendSmartLightDTO implements Serializable {
    private String lightPattern;
    private Color color;
    private int lightStrength;

    public SendSmartLightDTO(String lightPattern, Color color, int lightStrength) {
        this.lightPattern = lightPattern;
        this.color = color;
        this.lightStrength = lightStrength;
    }

    @Override
    public String toString() {
        return "SendSmartLightDTO{" +
                "lightPattern='" + lightPattern + '\'' +
                ", color=" + color +
                ", lightStrength=" + lightStrength +
                '}';
    }

    public String getLightPattern() {
        return lightPattern;
    }

    public void setLightPattern(String lightPattern) {
        this.lightPattern = lightPattern;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getLightStrength() {
        return lightStrength;
    }

    public void setLightStrength(int lightStrength) {
        this.lightStrength = lightStrength;
    }
}