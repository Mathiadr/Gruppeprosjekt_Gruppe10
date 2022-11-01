package forms;


import car.Car;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CarRental extends JFrame {
    private JPanel main;
    private JList carList;
    private JPanel cardLayout;
    private JPanel mainPage;
    private JButton rentCar;
    private JButton rentOutCar;
    private JPanel buttons;
    private JPanel rentACarPage;
    private JButton backToMainPage;
    private JPanel rentCarButtons;
    private JList carsAvailable;
    private JPanel rentOutCarPage;
    private JButton createCar;
    private JButton backToMainPage2;
    private JButton selectCar;
    private JFormattedTextField owner;
    private JFormattedTextField model;
    private JFormattedTextField fuelType;
    private JTextField regNumber;
    private JPanel createCarInputs;
    private JCheckBox available;
    private JCheckBox unavailabe;

    private DefaultListModel<Car> carArrayList = new DefaultListModel<>();


    public CarRental(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(main);
        this.pack();

        //Lister
        Car dummyCar = new Car ("Eb 60770", "Jesper", "Nissan Leaf");
        carList.setModel(carArrayList);
        carArrayList.addElement(dummyCar);

        //Buttons
        rentCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(rentACarPage);
                cardLayout.revalidate();
                cardLayout.repaint();
            }
        });

        // Rent out car buttons
        rentOutCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(rentOutCarPage);
                cardLayout.revalidate();
                cardLayout.repaint();
            }
        });

        createCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String registrationNumber = regNumber.getText();
                    String ownerName = owner.getText();
                    String carModel = model.getText();


                    Car createdCar = new Car(registrationNumber, ownerName, carModel);

                    carArrayList.addElement(createdCar);
                }
                catch (NullPointerException nullPointerException) {
                        System.out.println("Du har ikke valgt noe");
                }
            }
        });

        // Back Buttons

        backToMainPage2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(mainPage);
                cardLayout.revalidate();
                cardLayout.repaint();
            }
        });

        backToMainPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(mainPage);
                cardLayout.revalidate();
                cardLayout.repaint();
            }
        });


    }
}