package gui.test;

import forms.CarRental;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.awt.event.*;

import static org.junit.jupiter.api.Assertions.*;
public class GUITest {

    /*
    * Due to an exception occuring with Gradle/Intellij somehow interacting with Swing,
    * the GUI tests are disabled as the tests interchangeably either pass or fail to initialize, making them unstable.
    * We've looked into the issue and see that it's very recent, having only been fixed week(s) ago.
    * This requires migrating Gradle and Intellij to the newest version, but as we're not sure how to do this
    * within the limited time left we decided that we disable the tests completely.
    * We leave them here to document our test, as this bug is outside our control and not due to our own code.
    * Feel free to remove the comment and try to run it.
    */



    static CarRental carRental = new CarRental("TestFrame");

    @BeforeAll
    static void initGUI(){
        CarRental carRental = new CarRental("TestFrame");
        carRental.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @BeforeEach
    void resetVisibility(){
        carRental.setVisible(true);
    }


    @Test
    public void application_is_running() {
        assertTrue(carRental.isShowing());
    }


    @Test
    public void application_closes_with_Exit_button() {
        JButton jButton = new JButton();
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carRental.dispose();
            }
        });

        jButton.doClick();
        assertFalse(carRental.isShowing());
    }


    @Test
    public void check_if_button_pressed_work_with_SwitchPage() {
        JButton jButton = new JButton();
        JPanel jPanel = new JPanel();

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carRental.switchPage(jPanel);
            }
        });
        jButton.doClick();
        assertTrue(jPanel.isShowing());
    }


    @Test
    public void button_pressed_creates_and_adds_car(){
        JButton jButton = new JButton();
        ActionEvent actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "createCar");
        for (ActionListener action : jButton.getActionListeners()) {
            action.actionPerformed(actionEvent);
        }
    }

    @AfterAll
    static void closeGUI(){
        carRental.dispose();
    }




}