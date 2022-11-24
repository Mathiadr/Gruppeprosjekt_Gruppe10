import forms.CarRental;
import modules.Car;
import modules.CarRepository;
import modules.Listing;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GUITest {

    void initialize_CarRental () {
    }

    @Test
    public void application_is_running() {
        CarRental carRental1 = new CarRental("TestFrame");
        carRental1.setVisible(true);
        assertTrue(carRental1.isShowing());
    }

    @Test
    public void application_closes_with_Exit_button() {
        CarRental carRental1 = new CarRental("TestFrame");
        carRental1.setVisible(true);

        assertFalse(carRental1.isShowing());
    }

    @Test
    public void check_if_button_pressed_work_with_SwitchPage() {
        CarRental carRental1 = new CarRental("TestFrame");
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
        CarRental carRental1 = new CarRental("TestFrame");
        carRental1.setVisible(true);
        JButton jButton = new JButton();

        ActionEvent actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "createCar");
        for (ActionListener action : jButton.getActionListeners()) {
            action.actionPerformed(actionEvent);
        }


    }



}
