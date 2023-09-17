package simstation;

import mvc.*;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

public class SimulationPanel extends AppPanel {
    // TODO add variables and methods to SimulationPanel

    public SimulationPanel(SimulationFactory factory) {
        super(factory);
        String[] strings = new String[]{"Start", "Suspend", "Resume", "Stop", "Stats"};
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        for (String s : strings) {
            JPanel panel = new JPanel();
            JButton button = new JButton(s);
            panel.add(button);
            button.addActionListener(this);
            controlPanel.add(panel);
        }
    }

    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
    }

    // no main because we don't run the basic framework
}
