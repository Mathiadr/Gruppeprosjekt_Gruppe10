package forms;

import modules.*;
import tools.DateHandler;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class CarRental extends JFrame {
    private JPanel main, cardLayout, mainPage, availableCarPage, rentOutCarPage, selectedCarPage,allCarsEditPage,
            editInputs, editCarPage, buttons, rentCarButtons, createCarInputs, selectedCarButtons,EditCarPage,
            calendarPage, chooseDatePanel, listingInputPage;
    private JButton createCar, selectCar, rentOutCar, rentCar, rentCarButton, allCarsButton,
            toEditCarPageButton, backToAllCars, deleteCar, editCarButton,
            backToMainPage2, backToMainPage, backFromSelectedCarPage, backFromAllCarsButton, selectDateButton,
            listingInputButton, backFromListingButton;
    private JFormattedTextField owner, model, fuelType;
    private JTextField regNumber, regNumberEdit, ownerEdit, modelEdit;
    private JCheckBox available, unavailable, availableEdit, unavailableEdit;
    private JTextArea showsSelectedCarInfo;
    private JList<Car> carsAvailable,allCarsList;
    private JComboBox<Integer> pickupDayComboBox, deliverDayComboBox, rentOutCarToDay, rentOutCarFromDay;
    private JComboBox<String> rentOutCarToMonth, rentOutCarFromMonth, deliverMonthComboBox, pickupMonthComboBox;
    private JComboBox<String> fuelTypeBox, editCarFuelType;

    CarRepository createdCarRepository = new CarRepository("testRepository.JSON");
    private DefaultListModel<Car> carArrayList  = new DefaultListModel<>();

    private void show_all_Cars_List(ArrayList<Car> carlist) {
        carArrayList.clear();
        for (Car i : carlist) {
            carArrayList.addElement(i);
        }
    }

    public CarRental(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(main);
        this.pack();


        // Initialisation of Lists and comboBoxes ----------------------

        // Lists
        carsAvailable.setModel(carArrayList);
        allCarsList.setModel(carArrayList);

        // Maps the name of the months to their numeric type
        Map<String ,Integer> monthsMap = DateHandler.generateMonthsMapper();
        for (Map.Entry<String, Integer> month : monthsMap.entrySet()){
            pickupMonthComboBox.addItem(month.getKey());
            deliverMonthComboBox.addItem(month.getKey());
            rentOutCarFromMonth.addItem(month.getKey());
            rentOutCarToMonth.addItem(month.getKey());
        }
        // ComboBoxes
        DefaultComboBoxModel<Integer> startDaysInMonthComboBoxModel = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Integer> endDaysInMonthComboBoxModel = new DefaultComboBoxModel<>();
        pickupDayComboBox.setModel(startDaysInMonthComboBoxModel);
        deliverDayComboBox.setModel(endDaysInMonthComboBoxModel);
        rentOutCarToDay.setModel(endDaysInMonthComboBoxModel);
        rentOutCarFromDay.setModel(startDaysInMonthComboBoxModel);
        DateHandler.updateDaysInComboBox(pickupMonthComboBox.getSelectedItem(), startDaysInMonthComboBoxModel);
        DateHandler.updateDaysInComboBox(deliverMonthComboBox.getSelectedItem(), endDaysInMonthComboBoxModel);
        editCarFuelType.setModel(new DefaultComboBoxModel<String>(Car.fuelTypesList));
        fuelTypeBox.setModel(new DefaultComboBoxModel<String >(Car.fuelTypesList));

        // ---------------------------------------------------------------





        // Edit cars
        allCarsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(allCarsEditPage);
                cardLayout.revalidate();
                cardLayout.repaint();

                createdCarRepository.readFromJSON();
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
                Car selectedCar = allCarsList.getSelectedValue();

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
                    selectedCar.getListing().setAvailable(true);
                }
                else if (unavailableEdit.isSelected()) {
                    selectedCar.getListing().setAvailable(false);
                }

                JOptionPane.showMessageDialog(editCarPage, "Changes Approved");

            }
        });

        deleteCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Car selectedCar = allCarsList.getSelectedValue();
                createdCarRepository.removeExistingCar(selectedCar);
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
                cardLayout.add(chooseDatePanel);
                cardLayout.revalidate();
                cardLayout.repaint();

                show_all_Cars_List(createdCarRepository.getAllAvailableCars());
                createdCarRepository.readFromJSON();
            }
        });

        selectDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(availableCarPage);
                cardLayout.revalidate();
                cardLayout.repaint();

                int monthForPickup = monthsMap.get((String) pickupMonthComboBox.getSelectedItem());
                int dayForPickup = (int)pickupDayComboBox.getSelectedItem();

                int monthForDelivery = monthsMap.get((String) deliverMonthComboBox.getSelectedItem());
                int dayForDelivery = (int) deliverDayComboBox.getSelectedItem();

                DateTimeFormatter myformat = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

                LocalDate startDate = LocalDate.of(2022, monthForPickup, dayForPickup);
                LocalDate endDate = LocalDate.of(2022, monthForDelivery, dayForDelivery);

                // TODO: Figure out tf this does


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
                Car selectedCar = carsAvailable.getSelectedValue();
                selectedCar.getListing().setAvailable(false);
                show_all_Cars_List(createdCarRepository.getAllAvailableCars());
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
                createdCarRepository.readFromJSON();
                try {
                    String registrationNumber = regNumber.getText();
                    String ownerName = owner.getText();
                    String carModel = model.getText();


                    Car createdCar = new Car(registrationNumber, ownerName, carModel);

                    if (available.isSelected()) {
                        createdCar.getListing().setAvailable(true);

                    } else if (unavailable.isSelected()) {
                        createdCar.getListing().setAvailable(false);
                    }

                    createdCarRepository.addNewCar(createdCar);
                    createdCarRepository.saveCarsToJSON();

                    //Clears input fields
                    regNumber.setText("");
                    owner.setText("");
                    model.setText("");

                } catch (NullPointerException nullPointerException) {
                    System.out.println("Du har ikke valgt noe");
                }

                cardLayout.removeAll();
                cardLayout.add(listingInputPage);
                cardLayout.revalidate();
                cardLayout.repaint();
                System.out.println(allCarsList);
            }
        });


        listingInputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createdCarRepository.readFromJSON();
                show_all_Cars_List(createdCarRepository.getAllAvailableCars());


                int monthForPickup = monthsMap.get((String) rentOutCarFromMonth.getSelectedItem());
                int dayForPickup = (int)rentOutCarFromDay.getSelectedItem();

                int monthForDelivery = monthsMap.get((String) rentOutCarToMonth.getSelectedItem());
                int dayForDelivery = (int) rentOutCarToDay.getSelectedItem();

                LocalDate startDate = LocalDate.of(2022, monthForPickup, dayForPickup);
                LocalDate endDate = LocalDate.of(2022, monthForDelivery, dayForDelivery);

                try {

                    Car lastCarObject =  allCarsList.getModel().getElementAt(allCarsList.getLastVisibleIndex());

                    Listing createdCarListing = new Listing(startDate, endDate, true, "");

                    System.out.println(lastCarObject.toString());
                    System.out.println(createdCarListing);

                    //createdCarRepository.updateListing(lastCarObject, createdCarListing);
                    lastCarObject.setListing(createdCarListing);
                    createdCarRepository.saveCarsToJSON();

                    JOptionPane.showMessageDialog(selectedCarPage, "Your car is now available to rent!");

                }
                catch (StringIndexOutOfBoundsException se){
                    System.out.println("Out of Bounds");
                }
                cardLayout.removeAll();
                cardLayout.add(mainPage);
                cardLayout.revalidate();
                cardLayout.repaint();
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

        backFromListingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(rentOutCarPage);
                cardLayout.revalidate();
                cardLayout.repaint();
            }
        });


        // ActionListeners --------------------------------------------------------------------------------------

        // Updates the amount of days in ComboBox appropriate to selected month when selecting pickup date
        pickupMonthComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateHandler.updateDaysInComboBox(pickupMonthComboBox.getSelectedItem(), startDaysInMonthComboBoxModel);
            }
        });

        // Updates the amount of days in ComboBox appropriate to selected month when selecting delivery date
        deliverMonthComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateHandler.updateDaysInComboBox(deliverMonthComboBox.getSelectedItem(), endDaysInMonthComboBoxModel);
            }
        });

        // Updates the amount of days in ComboBox appropriate to selected month when selecting end period
        rentOutCarToMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateHandler.updateDaysInComboBox(rentOutCarToMonth.getSelectedItem(), endDaysInMonthComboBoxModel);
            }
        });
        // Updates the amount of days in ComboBox appropriate to selected month when selecting start period
        rentOutCarFromMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateHandler.updateDaysInComboBox(rentOutCarFromMonth.getSelectedItem(), startDaysInMonthComboBoxModel);
            }
        });

        // --------------------------------------------------------------------------------------------------------
    }
}