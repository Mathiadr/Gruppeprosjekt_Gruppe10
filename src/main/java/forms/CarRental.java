package forms;

import modules.*;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class CarRental extends JFrame {
    private JPanel main, cardLayout, mainPage, availableCarPage, rentOutCarPage, selectedCarPage,allCarsEditPage,
            editInputs, editCarPage, buttons, rentCarButtons, createCarInputs, selectedCarButtons,EditCarPage, calendarPage;
    private JList carsAvailable,allCarsList;
    private JButton createCar, selectCar, rentOutCar, rentCar, rentCarButton, allCarsButton,
            toEditCarPageButton, backToAllCars, deleteCar, editCarButton,
            backToMainPage2, backToMainPage, backFromSelectedCarPage, backFromAllCarsButton;
    private JFormattedTextField owner, model, fuelType;
    private JTextField regNumber, regNumberEdit, ownerEdit, modelEdit;
    private JCheckBox available, unavailabe, availableEdit, unavailableEdit;
    private JTextArea showsSelectedCarInfo;
    private JComboBox fuelTypeBox, editCarFuelType;
    private JPanel chooseDatePanel;
    private JComboBox pickupMonthComboBox;
    private JComboBox pickupDayComboBox;
    private JButton selectDateButton;
    private JComboBox deliverMonthComboBox;
    private JComboBox deliverDayComboBox;
    private JPanel listingInputPage;
    private JComboBox rentOutCarToDay;
    private JComboBox rentOutCarToMonth;
    private JComboBox rentOutCarFromMonth;
    private JComboBox rentOutCarFromDay;
    private JButton listingInputButton;
    private JButton backFromListingButton;


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
        String[] fuelTypesForList = {"Electric", "Gasoline", "Diesel"};
        Integer[] monthsList = {Calendar.JANUARY, Calendar.FEBRUARY, Calendar.MARCH, Calendar.APRIL, Calendar.MAY,
                Calendar.JUNE, Calendar.JULY, Calendar.AUGUST, Calendar.SEPTEMBER, Calendar.OCTOBER, Calendar.NOVEMBER,
                Calendar.DECEMBER};
        Integer[] dayList = {1,2,3,4,5,6,7,8,9,10,11,12,13,4,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};

        pickupMonthComboBox.setModel(new DefaultComboBoxModel<Integer>(monthsList));
        deliverMonthComboBox.setModel(new DefaultComboBoxModel<Integer>(monthsList));
        rentOutCarFromMonth.setModel(new DefaultComboBoxModel<Integer>(monthsList));
        rentOutCarToMonth.setModel(new DefaultComboBoxModel<Integer>(monthsList));

        pickupDayComboBox.setModel(new DefaultComboBoxModel<Integer>(dayList));
        deliverDayComboBox.setModel(new DefaultComboBoxModel<Integer>(dayList));
        rentOutCarFromDay.setModel(new DefaultComboBoxModel<Integer>(dayList));
        rentOutCarToDay.setModel(new DefaultComboBoxModel<Integer>(dayList));

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
                cardLayout.add(chooseDatePanel);
                cardLayout.revalidate();
                cardLayout.repaint();

                show_all_Cars_List(createdCarRepository.GetAllAvailableCars());
            }
        });

        selectDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(availableCarPage);
                cardLayout.revalidate();
                cardLayout.repaint();

                Integer monthForPickup = (Integer) pickupMonthComboBox.getSelectedItem();
                Integer dayForPickup = (Integer) pickupDayComboBox.getSelectedItem();

                Integer monthForDelivery = (Integer) deliverMonthComboBox.getSelectedItem();
                Integer dayForDelivery = (Integer) deliverDayComboBox.getSelectedItem();

                //FIXME
                Date startDate = new Date(122, monthForPickup-1, dayForPickup);
                Date endDate = new Date(122, monthForDelivery-1, dayForDelivery);
                System.out.println(startDate);


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
                selectedCar.getListing().setAvailable(false);
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
                cardLayout.removeAll();
                cardLayout.add(listingInputPage);
                cardLayout.revalidate();
                cardLayout.repaint();
                try {
                    String registrationNumber = regNumber.getText();
                    String ownerName = owner.getText();
                    String carModel = model.getText();


                    Car createdCar = new Car(registrationNumber, ownerName, carModel);

                    if (available.isSelected()) {
                        createdCar.getListing().setAvailable(true);

                    } else if (unavailabe.isSelected()) {
                        createdCar.getListing().setAvailable(false);
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

        listingInputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int monthForPickup = (int) rentOutCarFromMonth.getSelectedItem();
                int dayForPickup = (int) rentOutCarFromDay.getSelectedItem();

                Integer monthForDelivery = (Integer) rentOutCarToMonth.getSelectedItem();
                Integer dayForDelivery = (Integer) rentOutCarToDay.getSelectedItem();

                DateTimeFormatter myformat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                LocalDate startDate = LocalDate.of(2022, monthForPickup, dayForPickup);
                LocalDate endDate = LocalDate.of(2022, monthForDelivery, dayForDelivery);



                System.out.println(startDate.format(myformat));
                System.out.println(endDate.format(myformat));

                JOptionPane.showMessageDialog(selectedCarPage, "Your car i now avaiable to rent!");

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
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}