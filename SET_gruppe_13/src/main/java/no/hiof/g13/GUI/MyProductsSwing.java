package no.hiof.g13.GUI;
import javax.swing.*;
import java.util.ArrayList;
import no.hiof.g13.models.*;

public class MyProductsSwing extends JPanel {
    private int i;
    public static void main(String[] args) {
        ArrayList<IOTDevice> testIOT = new ArrayList<>();
    }

    public void runMyProducts(ArrayList<IOTDevice> devices){
        JFrame mainWindow = new JFrame();

        for(i=0; i < devices.size(); i++){
            this.add(new JLabel("label" + String.valueOf(i), JLabel.CENTER));
        }

        mainWindow.setSize(600,600);
        mainWindow.setVisible(true);
    }
}
