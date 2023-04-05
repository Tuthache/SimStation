package bc;

import mvc.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class BrickPanel extends AppPanel {

    private JTextField heightField, lengthField, widthField;

    public BrickPanel(AppFactory factory) {
        super(factory);
        Brick brick = (Brick) model;
        brick.addPropertyChangeListener(this);
        controlPanel.setLayout(new GridLayout(3, 2));

        heightField = new JTextField("" + brick.getHeight(), 10);
        heightField.setActionCommand("SetHeight");
        heightField.addActionListener(this);
        JPanel p = new JPanel();
        p.add(new JLabel("Height"));
        controlPanel.add(p);
        p = new JPanel();
        p.add(heightField);
        controlPanel.add(p);

        widthField = new JTextField("" + brick.getWidth(), 10);
        widthField.setActionCommand("SetWidth");
        widthField.addActionListener(this);
        p = new JPanel();
        p.add(new JLabel("Width"));
        controlPanel.add(p);
        p = new JPanel();
        p.add(widthField);
        controlPanel.add(p);

        lengthField = new JTextField("" + brick.getLength(), 10);
        lengthField.setActionCommand("SetLength");
        lengthField.addActionListener(this);
        p = new JPanel();
        p.add(new JLabel("Length"));
        controlPanel.add(p);
        p = new JPanel();
        p.add(lengthField);
        controlPanel.add(p);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
        Brick brick = (Brick) model;
        heightField.setText("" + brick.getHeight());
        widthField.setText("" + brick.getWidth());
        lengthField.setText("" + brick.getLength());
    }

    public static void main(String[] args) {
        AppFactory factory = new BrickFactory();
        AppPanel panel = new BrickPanel(factory);
        panel.display();
    }


}
