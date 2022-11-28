import modules.CarRepository;
import forms.CarRental;
import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args){
        CarRepository carRepository = new CarRepository("CarRepository.json");
        carRepository.readFromJSON();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double windowHeight = screenSize.height*0.50;
        double windowWidth = screenSize.width*0.50;
        CarRental carRental = new CarRental("Car rental");
        carRental.setSize((int)windowWidth, (int)windowHeight);
        carRental.setLocation((int) (windowWidth*0.5), (int)(windowHeight*0.5));
        carRental.setVisible(true);

    }
}
