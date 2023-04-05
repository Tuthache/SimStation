package bc;

import mvc.*;

import java.awt.*;
import javax.swing.*;


public class BrickFactory implements AppFactory {
    @Override
    public Model makeModel() {
        return new Brick();
    }

    @Override
    public View makeView(Model model) {
        View bv = new View(model);
        bv.setLayout(new GridLayout(3, 1));

        bv.add(new SideView(model));

        bv.add(new TopView(model));

        bv.add(new FrontView(model));
        /*
        bv.setLayout(new BorderLayout());
        bv.add(new SideView(model), BorderLayout.WEST);
        bv.add(new FrontView(model), BorderLayout.EAST);
        bv.add(new TopView(model), BorderLayout.SOUTH);

         */
        return bv;
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"SetHeight", "SetWidth", "SetLength"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        Command cmmd = null;
        if (type == "SetHeight") {
            cmmd = new SetHeight(model);
            if (source instanceof JTextField) {
                ((SetHeight)cmmd).newValue = Double.parseDouble(((JTextField)source).getText());
            }
        }
        else if (type == "SetWidth") {
            cmmd = new SetWidth(model);
            if (source instanceof JTextField) {
                ((SetWidth)cmmd).newValue = Double.parseDouble(((JTextField)source).getText());
            }
        }
        else if (type == "SetLength") {
            cmmd = new SetLength(model);
            if (source instanceof JTextField) {
                ((SetLength)cmmd).newValue = Double.parseDouble(((JTextField)source).getText());
            }
        }
        return cmmd;
    }

    @Override
    public String getTitle() {
        return "Brick CAD";
    }

    @Override
    public String[] getHelp() {
        return new String[] {
                "SetHeight: Changes height",
                "SetHeight: Changes width",
                "SetLength: Changes length",
        };
    }

    @Override
    public String about() {
        return "Brick CAD version 1.0. Copyright 2020 by Cyberdellic Designs";
    }
}
