package forms;


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
    private JFormattedTextField registrationNumberFormattedTextField;
    private JFormattedTextField ownerFormattedTextField;
    private JFormattedTextField modelFormattedTextField;
    private JFormattedTextField fuelTypeFormattedTextField;
    private JButton button1;


    public CarRental(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(main);
        this.pack();

        //Lister

        //Buttons
        rentCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(rentACarPage);
                cardLayout.revalidate();
            }
        });

        rentOutCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(rentOutCarPage);
                cardLayout.revalidate();
            }
        });

        backToMainPage2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(mainPage);
                cardLayout.revalidate();
            }
        });

        backToMainPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(mainPage);
                cardLayout.revalidate();
            }
        });


    }
}