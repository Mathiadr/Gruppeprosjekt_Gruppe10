import forms.CarRental;
import modules.Car;
import modules.CarRepository;
import modules.Listing;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GUITest {

    CarRental carRental1 = new CarRental("TestFrame");
    JButton jButton = new JButton();

    @Test
    public void application_is_running() {
        carRental1.setVisible(true);
        assertTrue(carRental1.isShowing());
    }

    @Test
    public void application_closes_with_Exit_button() {
        carRental1.setVisible(true);
        carRental1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carRental1.dispose();
            }
        });

        jButton.doClick();

        assertFalse(carRental1.isShowing());
    }

    @Test
    public void check_if_button_pressed_work_with_SwitchPage() {
        carRental1.setVisible(true);
        JButton jButton = new JButton();
        JPanel jPanel = new JPanel();

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carRental1.switchPage(jPanel);
            }
        });

        jButton.doClick();
        assertTrue(jPanel.isShowing());
    }

    @Test
    public void button_pressed_creates_and_adds_car(){
      jButton.addActionListener(carRental1.createCar.getAction());


    }



}
