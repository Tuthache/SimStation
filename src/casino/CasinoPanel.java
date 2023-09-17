package casino;

import mvc.*;
import javax.swing.*;
import java.awt.*;

import java.beans.PropertyChangeEvent;

public class CasinoPanel extends AppPanel {

    private JLabel wins, losses, roll;
    public CasinoPanel(AppFactory factory) {
        super(factory);
        Casino casino = (Casino)model;

        this.setLayout(new GridLayout(1, 2));
        //JPanel buttonPanel = new JPanel();

        controlPanel.setLayout(new GridLayout(4, 1));

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(1, 2));
        JPanel p = new JPanel();
        p.add(new JLabel("Total"));
        fieldPanel.add(p);

        p = new JPanel();
        roll = new JLabel("" + (casino.getDie1() + casino.getDie2()));
        p.add(roll);
        fieldPanel.add(p);
        controlPanel.add(fieldPanel);

        fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(1, 2));
        p = new JPanel();
        p.add(new JLabel("Wins"));
        fieldPanel.add(p);

        p = new JPanel();
        wins = new JLabel("" + casino.getWins());
        p.add(wins);
        fieldPanel.add(p);
        controlPanel.add(fieldPanel);

        fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(1, 2));
        p = new JPanel();
        p.add(new JLabel("Losses"));
        fieldPanel.add(p);

        p = new JPanel();
        losses = new JLabel("" + casino.getLosses());
        p.add(losses);
        fieldPanel.add(p);
        controlPanel.add(fieldPanel);

        JButton rollButton = new JButton("Roll");
        rollButton.addActionListener(this);
        p = new JPanel();
        p.add(rollButton);
        controlPanel.add(p);
        CasinoView view = new CasinoView(model);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt); // does nothing
        Casino casino = (Casino)model;
        roll.setText("" + (casino.getDie1() + casino.getDie2()));
        wins.setText("" + casino.getWins());
        losses.setText("" + casino.getLosses());
    }

    public static void main(String[] args) {
        AppPanel panel = new CasinoPanel(new CasinoFactory());
        panel.display();
    }

}