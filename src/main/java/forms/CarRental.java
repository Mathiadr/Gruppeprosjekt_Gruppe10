package forms;


import car.Car;
import car.CarRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class CarRental extends JFrame {
    private JPanel main;
    private JList carList;
    private JPanel cardLayout;
    private JPanel mainPage;
    private JButton rentCar;
    private JButton rentOutCar;
    private JPanel buttons;
    private JPanel availableCarPage;
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
    private JPanel selectedCarPage;
    private JButton rentCarButton;
    private JButton backFromSelectedCarPage;
    private JPanel selectedCarButtons;
    private JTextArea showsSelectedCarInfo;
    private JButton allCarsButton;
    private JPanel allCarsEditPage;
    private JList allCarsList;
    private JButton toEditCarPageButton;
    private JButton backFromAllCarsButton;
    private JPanel editCarPage;
    private JTextField regNumberEdit;
    private JTextField ownerEdit;
    private JTextField modelEdit;
    private JButton backToAllCars;
    private JButton editCarButton;
    private JTextField fueltypeEdit;
    private JCheckBox availableEdit;
    private JCheckBox unavailableEdit;
    private JButton deleteCar;
    private JPanel editInputs;
    private JComboBox fuelTypeBox;

    CarRepository createdCarRepository = new CarRepository("testRepository.JSON");
    private DefaultListModel<Car> guiCarArraYList  = new DefaultListModel<>();
    private DefaultListModel<Car> guiCarsAvailableList  = new DefaultListModel<>();

    private void update_GUIList() {
        for (Car i : createdCarRepository.getCarArrayList()) {
            guiCarArraYList.addElement(i);
        }
        createdCarRepository.GetAllAvailableCars();
    }


    public CarRental(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(main);
        this.pack();

        //Lists
        carsAvailable.setModel(guiCarsAvailableList);
        allCarsList.setModel(guiCarArraYList);

        //Edit cars
        allCarsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(allCarsEditPage);
                cardLayout.revalidate();
                cardLayout.repaint();
                update_GUIList();
            }
        });

        toEditCarPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(editCarPage);
                cardLayout.revalidate();
                cardLayout.repaint();

            }
        });

        editCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Car selectedCar = (Car) allCarsList.getSelectedValue();

                if (regNumberEdit.getText().isEmpty()) {
                    regNumberEdit.setText(regNumber.getText());
                }
                if (ownerEdit.getText().isEmpty()) {
                    ownerEdit.setText(owner.getText());
                }
                if (modelEdit.getText().isEmpty()){
                    modelEdit.setText(model.getText());
                }

                String carModel = modelEdit.getText();
                String ownerName = ownerEdit.getText();
                String registrationNumber = regNumberEdit.getText();

                selectedCar.setRegistrationNumber(registrationNumber);
                selectedCar.setOwner(ownerName);
                selectedCar.setModel(carModel);

                if (availableEdit.isSelected()) {
                    selectedCar.setAvailable(true);
                }
                else if (unavailableEdit.isSelected()) {
                    selectedCar.setAvailable(false);
                }
            }
        });

        deleteCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });


        //Rent car buttons
        rentCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(availableCarPage);
                cardLayout.revalidate();
                cardLayout.repaint();

                update_GUIList();
            }
        });

        selectCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(selectedCarPage);
                cardLayout.revalidate();
                cardLayout.repaint();

                showsSelectedCarInfo.selectAll();
                showsSelectedCarInfo.replaceSelection("");

                showsSelectedCarInfo.append(carsAvailable.getSelectedValue().toString());
                }
        });

        rentCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Car selectedCar = (Car) carsAvailable.getSelectedValue();
                selectedCar.setAvailable(false);
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
                    CarRepository createdCarRepository = new CarRepository("testRepository.JSON");

                    createdCarRepository.AddNewCar(createdCar);
                    update_GUIList();



                    if (available.isSelected()) {
                        createdCar.setAvailable(true);

                    } else if (unavailabe.isSelected()) {
                        createdCar.setAvailable(false);
                    }

                    //Clears input fields
                    regNumber.setText("");
                    owner.setText("");
                    model.setText("");
                    fuelType.setText("");

                } catch (NullPointerException nullPointerException) {
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

        backFromSelectedCarPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(availableCarPage);
                cardLayout.revalidate();
                cardLayout.repaint();
            }
        });

        backToAllCars.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(allCarsEditPage);
                cardLayout.revalidate();
                cardLayout.repaint();
            }
        });

        backFromAllCarsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(mainPage);
                cardLayout.revalidate();
                cardLayout.repaint();
            }
        });





    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}