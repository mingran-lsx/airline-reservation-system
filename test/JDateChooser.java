package test;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

import static test.test.buildDatePanel;

public class JDateChooser {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JDateChooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setLayout(new FlowLayout());
        frame.add(buildDatePanel("Date", new Date()));
    }

    public void setDate(Date value) {
    }
}
