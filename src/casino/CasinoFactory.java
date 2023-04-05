package casino;
import mvc.*;

public class CasinoFactory implements AppFactory {

    @Override
    public Model makeModel() {
        return new Casino();
    }

    @Override
    public String[] getEditCommands() {
        return new String[] {"Roll"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object src) {
        if (type == "Roll") return new RollCommand(model);
        return null;
    }

    @Override
    public View makeView(Model model) {
        return new CasinoView(model);
    }

    @Override
    public String getTitle() {
        return "Casino Royale";
    }

    @Override
    public String[] getHelp() {
        String[] cmmds = new String[1];
        cmmds[0] = "Select \"Roll\" to roll the dice";
        cmmds[1] = "Seven wins";
        cmmds[2] = "Three loses";
        return cmmds;
    }

    @Override
    public String about() {
        return "Casino version 1.0. Copyright 2020 by Cyberdellic Designs";
    }

}