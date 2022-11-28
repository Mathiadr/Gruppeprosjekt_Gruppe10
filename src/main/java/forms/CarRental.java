package forms;

import modules.*;
import tools.DateHandler;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.*;


public class CarRental extends JFrame {
    private JPanel main, cardLayout, mainPage, availableCarPage, rentOutCarPage, selectedCarPage,allCarsEditPage,
            editInputs, editCarPage, rentCarButtons, createCarInputs, selectedCarButtons,EditCarPage,
            calendarPage, listingInputPage, contractPanel, editListingPanel;
    private JButton createCar, selectCar, rentOutCar, rentCar, rentCarButton, allCarsButton,
            toEditCarPageButton, backToAllCars, deleteCar, approveEdit, FromCreateCarPageToMain, backToMainPage,
            backFromSelectedCarPage, backFromAllCarsButton, selectDateButton,
            listingInputButton, backFromListingButton, agreeButton, cancelContractButton;
    private JFormattedTextField owner, model, fuelType;
    private JTextField regNumber, regNumberEdit, ownerEdit, modelEdit;
    private JCheckBox availableEdit, unavailableEdit;
    private JTextArea showsSelectedCarInfo, contractArea;
    private JList<Car> carsAvailable,allCarsList;
    private JComboBox<Integer> pickupDayComboBox, deliverDayComboBox, rentOutCarToDay, rentOutCarFromDay,
            editToDay, editFromDay;
    private JComboBox<String> rentOutCarToMonth, rentOutCarFromMonth, deliverMonthComboBox, pickupMonthComboBox,
            editFromMonth, editToMonth;
    private JComboBox<String> fuelTypeBox, editCarFuelType;
    private JLabel logoLabel;
    private JButton backFromSelectDateButton;
    private JButton applyChangesButton;
    private JButton backFromEditDatesPanel;
    private JTextArea carDescriptionTextArea;
    private JRadioButton availableYesRadioButton;
    private JRadioButton availableNoRadioButton;
    private JTextField PLACEHOLDERCURRENTLYNOTUSABLETextField;
    private JTextArea editCarDescription;
    private JPanel editRentPeriod;
    private JRadioButton unavailableRadioButton;
    private JRadioButton availableRadioButton;
    private JPanel dateBoxMainPage;
    private JScrollPane scrollPane;


    CarRepository carRepository = new CarRepository("CarRepository.JSON");

    private final DefaultListModel<Car> carDefaultListModel = new DefaultListModel<>();

    public CarRental(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(main);
        this.pack();


        //Logo and background
        ImageIcon logo = new ImageIcon("src/carlogoresized.png");
        Image logoImage = logo.getImage();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double windowHeight = screenSize.height*0.15;
        double windowWidth = screenSize.width*0.12;
        Image newImageLogo = logoImage.getScaledInstance((int)windowWidth, (int) windowHeight, Image.SCALE_SMOOTH);
        ImageIcon newLogo = new ImageIcon(newImageLogo);
        logoLabel.setIcon(newLogo);





        // Initialisation of visual elements, Lists and ComboBoxes ----------------------

        // Lists
        DefaultListModel<Car> carDefaultListModel = new DefaultListModel<>();

        carsAvailable.setModel(carDefaultListModel);
        allCarsList.setModel(carDefaultListModel);

        // Maps the name of the months to their numerical version
        DefaultComboBoxModel<String> startMonthComboBoxModel = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> endMonthComboBoxModel = new DefaultComboBoxModel<>();
        Map<String ,Integer> monthsMap = DateHandler.generateMonthsMapper();
        for (Map.Entry<String, Integer> month : monthsMap.entrySet()){
            startMonthComboBoxModel.addElement(month.getKey());
            endMonthComboBoxModel.addElement(month.getKey());
        }
        // ComboBoxes
        DefaultComboBoxModel<Integer> startDaysInMonthComboBoxModel = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Integer> endDaysInMonthComboBoxModel = new DefaultComboBoxModel<>();
        pickupDayComboBox.setModel(startDaysInMonthComboBoxModel);
        deliverDayComboBox.setModel(endDaysInMonthComboBoxModel);
        rentOutCarToDay.setModel(endDaysInMonthComboBoxModel);
        rentOutCarFromDay.setModel(startDaysInMonthComboBoxModel);
        DateHandler.updateDaysInComboBox(startMonthComboBoxModel.getSelectedItem(), startDaysInMonthComboBoxModel);
        DateHandler.updateDaysInComboBox(endMonthComboBoxModel.getSelectedItem(), endDaysInMonthComboBoxModel);
        editCarFuelType.setModel(new DefaultComboBoxModel<>(Car.fuelTypesList));
        fuelTypeBox.setModel(new DefaultComboBoxModel<>(Car.fuelTypesList));
        editFromDay.setModel(startDaysInMonthComboBoxModel);
        editToDay.setModel(endDaysInMonthComboBoxModel);
        pickupMonthComboBox.setModel(startMonthComboBoxModel);
        deliverMonthComboBox.setModel(endMonthComboBoxModel);
        rentOutCarFromMonth.setModel(startMonthComboBoxModel);
        rentOutCarToMonth.setModel(endMonthComboBoxModel);
        editFromMonth.setModel(startMonthComboBoxModel);
        editToMonth.setModel(endMonthComboBoxModel);

        // Set selected items to comboBox to current date by default
        LocalDate currentDate = LocalDate.now();
        startMonthComboBoxModel.setSelectedItem(currentDate.getMonth().toString());
        endMonthComboBoxModel.setSelectedItem(currentDate.getMonth().toString());
        startDaysInMonthComboBoxModel.setSelectedItem(currentDate.getDayOfMonth());
        endDaysInMonthComboBoxModel.setSelectedItem(currentDate.getDayOfMonth());
        // -----------------------------------------------------------------------------
        carsAvailable.setFixedCellHeight(160);
        allCarsList.setFixedCellHeight(160);
        int cellWidth = 1000;
        ListingCellRenderer listingCellRenderer = new ListingCellRenderer(cellWidth);
        carsAvailable.setCellRenderer(listingCellRenderer);
        allCarsList.setCellRenderer(listingCellRenderer);





        // Edit cars
        allCarsButton.addActionListener(e -> {
            switchPage(allCarsEditPage);

            carRepository.readFromJSON();
            updateCarList(carRepository.getCarArrayList(), carDefaultListModel);
        });

        toEditCarPageButton.addActionListener(e -> {
            switchPage(editCarPage);

            Car selectedCar = allCarsList.getSelectedValue();
            Listing selectedCarListing = selectedCar.getListing();
            modelEdit.setText(selectedCar.getModel());
            ownerEdit.setText(selectedCar.getOwner());
            regNumberEdit.setText(selectedCar.getRegistrationNumber());
            editCarDescription.setText(selectedCarListing.getDescription());
            if (selectedCarListing.isAvailable()){
                availableRadioButton.setSelected(true);
            } else unavailableRadioButton.setSelected(true);
            startMonthComboBoxModel.setSelectedItem(selectedCarListing.getStartDate().getMonth().toString());
            endMonthComboBoxModel.setSelectedItem(selectedCarListing.getEndDate().getMonth().toString());
            startDaysInMonthComboBoxModel.setSelectedItem(selectedCarListing.getStartDate().getDayOfMonth());
            endDaysInMonthComboBoxModel.setSelectedItem(selectedCarListing.getEndDate().getDayOfMonth());

        });

        // Apply changes to specified car in Edit page
        approveEdit.addActionListener(e -> {
            Car selectedCar = allCarsList.getSelectedValue();
            Listing selectedCarListing = selectedCar.getListing();
            int monthForPickup = monthsMap.get(startMonthComboBoxModel.getSelectedItem().toString());
            int dayForPickup = (int)startDaysInMonthComboBoxModel.getSelectedItem();

            int monthForDelivery = monthsMap.get(endMonthComboBoxModel.getSelectedItem().toString());
            int dayForDelivery = (int)endDaysInMonthComboBoxModel.getSelectedItem();

            LocalDate startDate = LocalDate.of(2022, monthForPickup, dayForPickup);
            LocalDate endDate = LocalDate.of(2022, monthForDelivery, dayForDelivery);

            boolean validInformation = !regNumberEdit.getText().isEmpty() &&
                    !ownerEdit.getText().isEmpty() &&
                    !modelEdit.getText().isEmpty();
            boolean validDate = DateHandler.dateIsValid(startDate, endDate);


            if (validInformation && validDate){

                String carModel = modelEdit.getText();
                String ownerName = ownerEdit.getText();
                String registrationNumber = regNumberEdit.getText();

                selectedCar.setRegistrationNumber(registrationNumber);
                selectedCar.setOwner(ownerName);
                selectedCar.setModel(carModel);


                Listing createdCarListing = new Listing(startDate, endDate,
                        availableRadioButton.isSelected(), editCarDescription.getText());


                carRepository.updateListing(selectedCar, createdCarListing);
                carRepository.saveCarsToJSON();

                JOptionPane.showMessageDialog(editCarPage, "Changes Approved");
                switchPage(allCarsEditPage);
            }
            else if (!validDate){
                JOptionPane.showMessageDialog(editCarPage, "Invalid date");
                startMonthComboBoxModel.setSelectedItem(selectedCarListing.getStartDate().getMonth());
                endMonthComboBoxModel.setSelectedItem(selectedCarListing.getEndDate().getMonth());
                startDaysInMonthComboBoxModel.setSelectedItem(selectedCarListing.getStartDate().getDayOfMonth());
                endDaysInMonthComboBoxModel.setSelectedItem(selectedCarListing.getStartDate().getDayOfMonth());
            }
            else if (!validInformation){
                JOptionPane.showMessageDialog(editCarPage, "None of the fields in " +
                        "car information can be empty. Please fill out each field with information.");

                if (regNumberEdit.getText().isEmpty()) {
                    regNumberEdit.setText(selectedCar.getRegistrationNumber());
                }
                if (ownerEdit.getText().isEmpty()) {
                    ownerEdit.setText(selectedCar.getOwner());
                }
                if (modelEdit.getText().isEmpty()){
                    modelEdit.setText(selectedCar.getModel());
                }
            }
        });

        //Delete specified car in Edit page
        deleteCar.addActionListener(e -> {
            Car selectedCar = allCarsList.getSelectedValue();
            if(carRepository.removeExistingCar(selectedCar) == 1){
                JOptionPane.showMessageDialog(editCarPage, "Car with Registration Number " + selectedCar.getRegistrationNumber() +
                        " was removed");
                updateCarList(carRepository.getCarArrayList(), carDefaultListModel);
                carRepository.saveCarsToJSON();
                switchPage(mainPage);
            } else JOptionPane.showMessageDialog(createCarInputs, "Error deleting specified car.");
        });


        //Rent car buttons
        selectDateButton.addActionListener(e -> {
            int monthForPickup = monthsMap.get(startMonthComboBoxModel.getSelectedItem().toString());
            int dayForPickup = (int) startDaysInMonthComboBoxModel.getSelectedItem();

            int monthForDelivery = monthsMap.get(endMonthComboBoxModel.getSelectedItem().toString());
            int dayForDelivery = (int) endDaysInMonthComboBoxModel.getSelectedItem();

            LocalDate startOfRentPeriod = LocalDate.of(2022, monthForPickup, dayForPickup);
            LocalDate endOfRentPeriod = LocalDate.of(2022, monthForDelivery, dayForDelivery);

            boolean validDate = DateHandler.dateIsValid(startOfRentPeriod, endOfRentPeriod);
            if (validDate) {
                carRepository.readFromJSON();

                ArrayList<Car> carArrayList = carRepository.getAllAvailableCars(startOfRentPeriod, endOfRentPeriod);
                updateCarList(carArrayList, carDefaultListModel);

                switchPage(availableCarPage);
            } else {
                JOptionPane.showMessageDialog(mainPage, "Invalid date");
            }

        });

        // Select specified car within all stored cars list
        selectCar.addActionListener(e -> {
            switchPage(selectedCarPage);
            showsSelectedCarInfo.append(carsAvailable.getSelectedValue().toString());
        });

        rentCarButton.addActionListener(e -> {
            Car selectedCar = carsAvailable.getSelectedValue();
            int monthForPickup = monthsMap.get(startMonthComboBoxModel.getSelectedItem().toString());
            int dayForPickup = (int)startDaysInMonthComboBoxModel.getSelectedItem();

            int monthForDelivery = monthsMap.get(endMonthComboBoxModel.getSelectedItem().toString());
            int dayForDelivery = (int) endDaysInMonthComboBoxModel.getSelectedItem();

            LocalDate startOfRentPeriod = LocalDate.of(2022, monthForPickup, dayForPickup);
            LocalDate endOfRentPeriod = LocalDate.of(2022, monthForDelivery, dayForDelivery);

            switchPage(contractPanel);

            contractArea.setText(CarContract.getCarContract(selectedCar.getOwner(), selectedCar.getModel(),
                    startOfRentPeriod, endOfRentPeriod));
            contractArea.setCaretPosition(0);

        });

        // Agree to contract
        agreeButton.addActionListener(e -> {
            Car selectedCar = carsAvailable.getSelectedValue();
            int monthForPickup = monthsMap.get(startMonthComboBoxModel.getSelectedItem().toString());
            int dayForPickup = (int) startDaysInMonthComboBoxModel.getSelectedItem();

            int monthForDelivery = monthsMap.get(endMonthComboBoxModel.getSelectedItem().toString());
            int dayForDelivery = (int) endDaysInMonthComboBoxModel.getSelectedItem();

            LocalDate startOfRentPeriod = LocalDate.of(2022, monthForPickup, dayForPickup);
            LocalDate endOfRentPeriod = LocalDate.of(2022, monthForDelivery, dayForDelivery);
            carRepository.initiateRentProcess(selectedCar, startOfRentPeriod, endOfRentPeriod);
            carRepository.saveCarsToJSON();
            carRepository.readFromJSON();
            switchPage(mainPage);


            JOptionPane.showMessageDialog(contractPanel, "The car is now available, drive safe!");
        });

        // Send user to "Create car" page
        rentOutCar.addActionListener(e -> {
            startMonthComboBoxModel.setSelectedItem(currentDate.getMonth().toString());
            endMonthComboBoxModel.setSelectedItem(currentDate.getMonth().toString());
            startDaysInMonthComboBoxModel.setSelectedItem(currentDate.getDayOfMonth());
            endDaysInMonthComboBoxModel.setSelectedItem(currentDate.getDayOfMonth());
            switchPage(rentOutCarPage);
        });

        // Create car out of specified information provided by user in "Create car" Page. Send user to Listing input page.
        createCar.addActionListener(e -> {
            carRepository.readFromJSON();
            boolean validInformation = !regNumber.getText().isEmpty() &&
                    !owner.getText().isEmpty() &&
                    !model.getText().isEmpty();
            if (validInformation){
                try {
                    String registrationNumber = regNumber.getText();
                    String ownerName = owner.getText();
                    String carModel = model.getText();

                    Car createdCar = new Car(registrationNumber, ownerName, carModel);
                    if(carRepository.addNewCar(createdCar) == 1){
                        //Clears input fields
                        regNumber.setText("");
                        owner.setText("");
                        model.setText("");
                        switchPage(listingInputPage);
                    } else JOptionPane.showMessageDialog(createCarInputs, "Registration number already exists.");
                } catch (NullPointerException nullPointerException) {
                    System.out.println("You have not chosen anything");
                }
            } else JOptionPane.showMessageDialog(listingInputPage, "Invalid fields. Please fill out the form.");
        });


        listingInputButton.addActionListener(e -> {
            updateCarList(carRepository.getCarArrayList(), carDefaultListModel);
            String carDescription = carDescriptionTextArea.getText();

            int monthForPickup = monthsMap.get(startMonthComboBoxModel.getSelectedItem().toString());
            int dayForPickup = (int) startDaysInMonthComboBoxModel.getSelectedItem();

            int monthForDelivery = monthsMap.get(endMonthComboBoxModel.getSelectedItem().toString());
            int dayForDelivery = (int) endDaysInMonthComboBoxModel.getSelectedItem();

            LocalDate startDate = LocalDate.of(2022, monthForPickup, dayForPickup);
            LocalDate endDate = LocalDate.of(2022, monthForDelivery, dayForDelivery);

            boolean validDate = DateHandler.dateIsValid(startDate, endDate);
            if (validDate) {
                boolean isAvailable = availableYesRadioButton.isSelected();
                try {

                    Car lastCarObject = carDefaultListModel.lastElement();

                    Listing createdCarListing = new Listing(startDate, endDate, isAvailable, carDescription);


                    carRepository.updateListing(lastCarObject, createdCarListing);
                    carRepository.saveCarsToJSON();

                    JOptionPane.showMessageDialog(selectedCarPage, "Your listing is now published!");

                }
                catch (StringIndexOutOfBoundsException se){
                    System.out.println("Out of Bounds");
                }
                switchPage(mainPage);
            } else JOptionPane.showMessageDialog(createCarInputs, "Invalid date");

        });


        // Back Buttons

        cancelContractButton.addActionListener(e -> switchPage(selectedCarPage));

        FromCreateCarPageToMain.addActionListener(e -> {
            switchPage(mainPage);
            //Clears input fields
            regNumber.setText("");
            owner.setText("");
            model.setText("");

        });

        backFromSelectedCarPage.addActionListener(e -> switchPage(availableCarPage));

        backToAllCars.addActionListener(e -> switchPage(allCarsEditPage));

        backFromAllCarsButton.addActionListener(e -> switchPage(mainPage));

        backFromListingButton.addActionListener(e -> switchPage(rentOutCarPage));

        backToMainPage.addActionListener(e -> switchPage(mainPage));



        // ComboBox ActionListeners --------------------------------------------------------------------------------------

        // Updates the amount of days in ComboBox appropriate to selected month when selecting pickup date
        pickupMonthComboBox.addActionListener(e ->
                DateHandler.updateDaysInComboBox(startMonthComboBoxModel.getSelectedItem(), startDaysInMonthComboBoxModel));

        // Updates the amount of days in ComboBox appropriate to selected month when selecting delivery date
        deliverMonthComboBox.addActionListener(e ->
                DateHandler.updateDaysInComboBox(endMonthComboBoxModel.getSelectedItem(), endDaysInMonthComboBoxModel));

        // Updates the amount of days in ComboBox appropriate to selected month when selecting end of rent period
        rentOutCarToMonth.addActionListener(e ->
                DateHandler.updateDaysInComboBox(endMonthComboBoxModel.getSelectedItem(), endDaysInMonthComboBoxModel));
        // Updates the amount of days in ComboBox appropriate to selected month when selecting start of rent period
        rentOutCarFromMonth.addActionListener(e ->
                DateHandler.updateDaysInComboBox(startMonthComboBoxModel.getSelectedItem(), startDaysInMonthComboBoxModel));

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