package forms;

import car.Car;
import car.CarRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class CarRental extends JFrame {
    private JPanel main, cardLayout, mainPage, availableCarPage, rentOutCarPage, selectedCarPage,allCarsEditPage,
            editInputs, editCarPage, buttons, rentCarButtons, createCarInputs, selectedCarButtons,EditCarPage;
    private JList carsAvailable,allCarsList;
    private JButton createCar, selectCar, rentOutCar, rentCar, rentCarButton, allCarsButton,
            toEditCarPageButton, backToAllCars, deleteCar, editCarButton,
            backToMainPage2, backToMainPage, backFromSelectedCarPage, backFromAllCarsButton;
    private JFormattedTextField owner, model, fuelType;
    private JTextField regNumber, regNumberEdit, ownerEdit, modelEdit;
    private JCheckBox available, unavailabe, availableEdit, unavailableEdit;
    private JTextArea showsSelectedCarInfo;
    private JComboBox fuelTypeBox, editCarFuelType;

    CarRepository createdCarRepository = new CarRepository("testRepository.JSON");
    private DefaultListModel<Car> guiCarArraYList  = new DefaultListModel<>();
    private DefaultListModel<Car> guiCarsAvailableList  = new DefaultListModel<>();

    private void show_all_Cars_List(ArrayList<Car> carlist) {
        guiCarArraYList.clear();
        for (Car i : carlist) {
            guiCarArraYList.addElement(i);
        }
    }
    public CarRental(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(main);
        this.pack();

        //Lists
        carsAvailable.setModel(guiCarArraYList);
        allCarsList.setModel(guiCarArraYList);

        //ComboBox
        String fuelTypesForList[] = {"Electric", "Gasoline", "Diesel"};
        editCarFuelType.setModel(new DefaultComboBoxModel<String >(fuelTypesForList));
        fuelTypeBox.setModel(new DefaultComboBoxModel<String >(fuelTypesForList));

        //Edit cars
        allCarsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(allCarsEditPage);
                cardLayout.revalidate();
                cardLayout.repaint();

                show_all_Cars_List(createdCarRepository.getCarArrayList());
            }
        });

        toEditCarPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(editCarPage);
                cardLayout.revalidate();
                cardLayout.repaint();

                Car selectedCar = (Car) allCarsList.getSelectedValue();
                modelEdit.setText(selectedCar.getModel());
                ownerEdit.setText(selectedCar.getOwner());
                regNumberEdit.setText(selectedCar.getRegistrationNumber());

            }
        });

        editCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Car selectedCar = (Car) allCarsList.getSelectedValue();

                String carModel = modelEdit.getText();
                String ownerName = ownerEdit.getText();
                String registrationNumber = regNumberEdit.getText();

                if (regNumberEdit.getText().isEmpty()) {
                    registrationNumber = selectedCar.getRegistrationNumber();
                }
                if (ownerEdit.getText().isEmpty()) {
                    ownerName = selectedCar.getOwner();
                }
                if (modelEdit.getText().isEmpty()){
                    carModel = selectedCar.getModel();
                }

                selectedCar.setRegistrationNumber(registrationNumber);
                selectedCar.setOwner(ownerName);
                selectedCar.setModel(carModel);

                if (availableEdit.isSelected()) {
                    selectedCar.setAvailable(true);
                }
                else if (unavailableEdit.isSelected()) {
                    selectedCar.setAvailable(false);
                }

                JOptionPane.showMessageDialog(editCarPage, "Changes Approved");

            }
        });

        deleteCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Car selectedCar = (Car) allCarsList.getSelectedValue();
                createdCarRepository.RemoveExistingCar(selectedCar);
                JOptionPane.showMessageDialog(editCarPage, "Car with Registration Number " + selectedCar.getRegistrationNumber() +
                        " was removed");
                show_all_Cars_List(createdCarRepository.getCarArrayList());

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

                show_all_Cars_List(createdCarRepository.GetAllAvailableCars());
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
                show_all_Cars_List(createdCarRepository.GetAllAvailableCars());
                JOptionPane.showMessageDialog(selectedCarPage, "You have just rented car " + selectedCar.getRegistrationNumber()
                 + " from owner " + selectedCar.getOwner());
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

                    if (available.isSelected()) {
                        createdCar.setAvailable(true);

                    } else if (unavailabe.isSelected()) {
                        createdCar.setAvailable(false);
                    }

                    createdCarRepository.AddNewCar(createdCar);

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