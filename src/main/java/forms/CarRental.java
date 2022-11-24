package forms;

import modules.*;
import tools.DateHandler;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.*;


public class CarRental extends JFrame {
    private JPanel main, cardLayout, mainPage, availableCarPage, rentOutCarPage, selectedCarPage,allCarsEditPage,
            editInputs, editCarPage, buttons, rentCarButtons, createCarInputs, selectedCarButtons,EditCarPage,
            calendarPage, chooseDatePanel, listingInputPage, contractPanel;
    public JButton createCar;
    private JButton selectCar;
    private JButton rentOutCar;
    private JButton rentCar;
    private JButton rentCarButton;
    private JButton allCarsButton;
    private JButton toEditCarPageButton;
    private JButton backToAllCars;
    private JButton deleteCar;
    private JButton editCarButton;
    private JButton backToMainPage2;
    private JButton backToMainPage;
    private JButton backFromSelectedCarPage;
    private JButton backFromAllCarsButton;
    private JButton selectDateButton;
    private JButton listingInputButton;
    private JButton backFromListingButton;
    private JButton agreeButton;
    private JButton cancelContractButton;
    private JFormattedTextField owner, model, fuelType;
    private JTextField regNumber, regNumberEdit, ownerEdit, modelEdit;
    private JCheckBox available, unavailable, availableEdit, unavailableEdit;
    private JTextArea showsSelectedCarInfo, contractArea;
    private JList<Car> carsAvailable,allCarsList;
    private JComboBox<Integer> pickupDayComboBox, deliverDayComboBox, rentOutCarToDay, rentOutCarFromDay;
    private JComboBox<String> rentOutCarToMonth, rentOutCarFromMonth, deliverMonthComboBox, pickupMonthComboBox;
    private JComboBox<String> fuelTypeBox, editCarFuelType;
    private JLabel logoLabel;
    private JButton backFromSelectDateButton;
    private JScrollPane scrollPane;


    CarRepository carRepository = new CarRepository("testRepository.JSON");
    private final DefaultListModel<Car> carDefaultListModel = new DefaultListModel<>();

    private void show_all_Cars_List(ArrayList<Car> newCarArrayList) {
        carDefaultListModel.clear();
        for (Car i : newCarArrayList) {
            carDefaultListModel.addElement(i);
        }
    }

    public CarRental(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(main);
        this.pack();


        // Initialisation of visual elements, Lists and ComboBoxes ----------------------

        // Lists
        DefaultListModel<Car> carDefaultListModel = new DefaultListModel<>();
        carsAvailable.setModel(carDefaultListModel);
        allCarsList.setModel(carDefaultListModel);

        // Maps the name of the months to their numerical version
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
        editCarFuelType.setModel(new DefaultComboBoxModel<>(Car.fuelTypesList));
        fuelTypeBox.setModel(new DefaultComboBoxModel<>(Car.fuelTypesList));

        // Set selected items to comboBox to current date by default
        LocalDate currentDate = LocalDate.now();
        pickupMonthComboBox.setSelectedItem(pickupMonthComboBox.getItemAt(currentDate.getMonthValue()-1));
        pickupDayComboBox.setSelectedItem(pickupDayComboBox.getItemAt(currentDate.getDayOfMonth()-1));
        deliverMonthComboBox.setSelectedItem(deliverMonthComboBox.getItemAt(currentDate.getMonthValue()-1));
        deliverDayComboBox.setSelectedItem(deliverDayComboBox.getItemAt(currentDate.getDayOfMonth()-1));
        rentOutCarFromMonth.setSelectedItem(rentOutCarFromMonth.getItemAt(currentDate.getMonthValue()-1));
        rentOutCarFromDay.setSelectedItem(rentOutCarFromDay.getItemAt(currentDate.getDayOfMonth()-1));
        rentOutCarToMonth.setSelectedItem(rentOutCarToMonth.getItemAt(currentDate.getMonthValue()-1));
        rentOutCarToDay.setSelectedItem(rentOutCarToDay.getItemAt(currentDate.getDayOfMonth()-1));

        //Logo
        ImageIcon logo = new ImageIcon("src/logo2.png");
        logoLabel.setIcon(logo);

        // ------------------------------------------------------------------------------



        // Edit cars
        allCarsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPage(allCarsEditPage);

                carRepository.readFromJSON();
                updateCarList(carRepository.getCarArrayList(), carDefaultListModel);
            }
        });

        toEditCarPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPage(editCarPage);

                Car selectedCar = allCarsList.getSelectedValue();
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
                carRepository.removeExistingCar(selectedCar);
                JOptionPane.showMessageDialog(editCarPage, "Car with Registration Number " + selectedCar.getRegistrationNumber() +
                        " was removed");
                show_all_Cars_List(carRepository.getCarArrayList());

            }
        });


        //Rent car buttons
        rentCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPage(chooseDatePanel);
            }
        });

        selectDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int monthForPickup = monthsMap.get((String) pickupMonthComboBox.getSelectedItem());
                int dayForPickup = (int)pickupDayComboBox.getSelectedItem();

                int monthForDelivery = monthsMap.get((String) deliverMonthComboBox.getSelectedItem());
                int dayForDelivery = (int) deliverDayComboBox.getSelectedItem();

                LocalDate startOfRentPeriod = LocalDate.of(2022, monthForPickup, dayForPickup);
                LocalDate endOfRentPeriod = LocalDate.of(2022, monthForDelivery, dayForDelivery);
                carRepository.readFromJSON();

                ArrayList<Car> carArrayList = carRepository.getAllAvailableCars(startOfRentPeriod, endOfRentPeriod);
                updateCarList(carArrayList, carDefaultListModel);


                switchPage(availableCarPage);

            }
        });

        selectCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPage(selectedCarPage);
                Car selectedCar = carsAvailable.getSelectedValue();
                int monthForPickup = monthsMap.get((String) pickupMonthComboBox.getSelectedItem());
                int dayForPickup = (int)pickupDayComboBox.getSelectedItem();

                int monthForDelivery = monthsMap.get((String) deliverMonthComboBox.getSelectedItem());
                int dayForDelivery = (int) deliverDayComboBox.getSelectedItem();

                LocalDate startOfRentPeriod = LocalDate.of(2022, monthForPickup, dayForPickup);
                LocalDate endOfRentPeriod = LocalDate.of(2022, monthForDelivery, dayForDelivery);

                showsSelectedCarInfo.selectAll();
                showsSelectedCarInfo.replaceSelection("");

                showsSelectedCarInfo.append(carsAvailable.getSelectedValue().toString());
                }
        });

        rentCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Car selectedCar = carsAvailable.getSelectedValue();
                int monthForPickup = monthsMap.get((String) pickupMonthComboBox.getSelectedItem());
                int dayForPickup = (int)pickupDayComboBox.getSelectedItem();

                int monthForDelivery = monthsMap.get((String) deliverMonthComboBox.getSelectedItem());
                int dayForDelivery = (int) deliverDayComboBox.getSelectedItem();

                LocalDate startOfRentPeriod = LocalDate.of(2022, monthForPickup, dayForPickup);
                LocalDate endOfRentPeriod = LocalDate.of(2022, monthForDelivery, dayForDelivery);

                switchPage(contractPanel);

                contractArea.setText(CarContract.getCarContract(selectedCar.getOwner(), selectedCar.getModel(),
                        startOfRentPeriod, endOfRentPeriod));
                contractArea.setCaretPosition(0);

            }
        });

        agreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Car selectedCar = carsAvailable.getSelectedValue();
                int monthForPickup = monthsMap.get((String) pickupMonthComboBox.getSelectedItem());
                int dayForPickup = (int)pickupDayComboBox.getSelectedItem();

                int monthForDelivery = monthsMap.get((String) deliverMonthComboBox.getSelectedItem());
                int dayForDelivery = (int) deliverDayComboBox.getSelectedItem();

                LocalDate startOfRentPeriod = LocalDate.of(2022, monthForPickup, dayForPickup);
                LocalDate endOfRentPeriod = LocalDate.of(2022, monthForDelivery, dayForDelivery);
                carRepository.initiateRentProcess(selectedCar, startOfRentPeriod, endOfRentPeriod);
                carRepository.saveCarsToJSON();
                carRepository.readFromJSON();
                switchPage(mainPage);


                JOptionPane.showMessageDialog(contractPanel, "The car is now available, drive safe!");
            }
        });

        // Rent out car buttons
        rentOutCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPage(rentOutCarPage);
            }
        });

        createCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carRepository.readFromJSON();
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

                    carRepository.addNewCar(createdCar);
                    carRepository.saveCarsToJSON();

                    //Clears input fields
                    regNumber.setText("");
                    owner.setText("");
                    model.setText("");

                } catch (NullPointerException nullPointerException) {
                    System.out.println("Du har ikke valgt noe");
                }

                switchPage(listingInputPage);
            }
        });


        listingInputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carRepository.readFromJSON();

                int monthForPickup = monthsMap.get((String) rentOutCarFromMonth.getSelectedItem());
                int dayForPickup = (int)rentOutCarFromDay.getSelectedItem();

                int monthForDelivery = monthsMap.get((String) rentOutCarToMonth.getSelectedItem());
                int dayForDelivery = (int) rentOutCarToDay.getSelectedItem();

                LocalDate startDate = LocalDate.of(2022, monthForPickup, dayForPickup);
                LocalDate endDate = LocalDate.of(2022, monthForDelivery, dayForDelivery);

                try {

                    Car lastCarObject =  allCarsList.getModel().getElementAt(allCarsList.getLastVisibleIndex());

                    Listing createdCarListing = new Listing(startDate, endDate, true, "");


                    carRepository.updateListing(lastCarObject, createdCarListing);
                    carRepository.saveCarsToJSON();

                    JOptionPane.showMessageDialog(selectedCarPage, "Your car is now available to rent!");

                }
                catch (StringIndexOutOfBoundsException se){
                    System.out.println("Out of Bounds");
                }
                switchPage(mainPage);
            }
        });


        // Back Buttons

        cancelContractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(selectedCarPage);
                cardLayout.revalidate();
                cardLayout.repaint();
            }
        });

        backToMainPage2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPage(mainPage);
            }
        });

        backToMainPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPage(chooseDatePanel);
            }
        });

        backFromSelectedCarPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPage(availableCarPage);
            }
        });

        backToAllCars.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPage(allCarsEditPage);
            }
        });

        backFromAllCarsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPage(mainPage);
            }
        });

        backFromListingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPage(rentOutCarPage);
            }
        });

        backFromSelectDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPage(mainPage);
            }
        });


        // ComboBox ActionListeners --------------------------------------------------------------------------------------

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

        // Updates the amount of days in ComboBox appropriate to selected month when selecting end of rent period
        rentOutCarToMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateHandler.updateDaysInComboBox(rentOutCarToMonth.getSelectedItem(), endDaysInMonthComboBoxModel);
            }
        });
        // Updates the amount of days in ComboBox appropriate to selected month when selecting start of rent period
        rentOutCarFromMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateHandler.updateDaysInComboBox(rentOutCarFromMonth.getSelectedItem(), startDaysInMonthComboBoxModel);
            }
        });

        // --------------------------------------------------------------------------------------------------------
    }

    // Updates the given DefaultListModel with the contents of a given ArrayList containing cars
    public void updateCarList(ArrayList<Car> carArrayList, DefaultListModel<Car> carDefaultListModel){
        carDefaultListModel.removeAllElements();
        // Foreach loops DO NOT work with DefaultListModels. Avoid using them.
        for (int car = 0; car < carArrayList.size(); car++){
            carDefaultListModel.addElement(carArrayList.get(car));
        }
    }
    // Updates the GUI to show specified page
    public void switchPage(JPanel page) {
        cardLayout.removeAll();
        cardLayout.add(page);
        cardLayout.revalidate();
        cardLayout.repaint();
    }


}