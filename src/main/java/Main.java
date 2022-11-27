import modules.Car;
import modules.CarRepository;

import forms.CarRental;
import modules.Listing;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args){
        CarRepository carRepository = new CarRepository("testRepository.json");
        carRepository.readFromJSON();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double windowHeight = screenSize.height*0.50;
        double windowWidth = screenSize.width*0.50;
        CarRental carRental = new CarRental("Car rental");
        carRental.setSize((int)windowWidth, (int)windowHeight);
        carRental.setLocation((int) (windowWidth*0.5), (int)(windowHeight*0.5));
        carRental.setVisible(true);

        ImageIcon logo = new ImageIcon("src/windowLogo.png");
        Image logoImage = logo.getImage();
        Image scaledImage = logoImage.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        carRental.setIconImage(scaledImage);

    }
}
