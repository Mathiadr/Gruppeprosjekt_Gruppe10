package forms;

import car.Car;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CarRental extends JFrame {
    private JPanel main;
    private JList carList;
    private JList userList;
    private JPanel list;
    private JPanel cardLayout;
    private JPanel mainPage;
    private JButton addUser;
    private JButton addCar;
    private JPanel buttons;
    private JPanel addUserPage;
    private JButton createUser;
    private JButton backToMainPage;
    private JTextField nameInput;
    private JTextField ageInput;
    private JTextField personalIdInput;
    private JPanel addUserInputFields;
    private JPanel userPageButtons;

    private DefaultListModel<Car> carListe = new DefaultListModel<>();


    public CarRental (String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(main);
        this.pack();

        //
        carList.setModel(carListe);

        //Buttons
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.removeAll();
                cardLayout.add(addUserPage);
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
