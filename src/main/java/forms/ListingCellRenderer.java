package forms;

import modules.Car;

import javax.swing.*;
import java.awt.*;

// This class creates a more readable list for the GUI.
// The formula was gathered from https://stackoverflow.com/a/8197454 and
// with the help of https://www.codejava.net/java-se/swing/jlist-custom-renderer-example
// We made some modifications to the original code for our implementation.
public class ListingCellRenderer extends JLabel implements ListCellRenderer<Car> {
    public static final String HTML_1 = "<html><body style='width: ";
    public static final String HTML_2 = "px'>";
    public static final String HTML_3 = "</html>";
    private int width;

    public ListingCellRenderer(int width) {
        this.width = width;
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Car> list, Car car, int index, boolean isSelected, boolean cellHasFocus) {
        String availability;
        if(car.getListing().isAvailable()) availability = "Available for renting";
        else availability = "Unavailable for renting";

        String text = HTML_1 + String.valueOf(width) + HTML_2 +
                "<br> Registration Number: " + car.getRegistrationNumber() +
                "<br> Car model: " + car.getModel() +
                "<br> Owned by: " + car.getOwner() +
                "<br> Rentable Period: From " + car.getListing().getStartDate() + " To " + car.getListing().getEndDate() +
                "<br> Description: " + car.getListing().getDescription() +
                "<br>" + availability +
                HTML_3;
        ImageIcon imageIcon = new ImageIcon("src/NOIMAGE.png");
        setIcon(imageIcon);
        setText(text);

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
