package no.hiof.g13.GUI;

import java.awt.*;

public abstract class imageLoaderSwing extends Canvas {
    public void image(Graphics graphics){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("images/yaleDoorman.png");
        graphics.drawImage(image,0,0,this);
    }
}
