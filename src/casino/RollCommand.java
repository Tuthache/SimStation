package casino;

import mvc.*;

public class RollCommand extends Command {

    public RollCommand(Model model) { super(model); }

    @Override
    public void execute() {
        Casino casino = (Casino)model;
        casino.roll();
    }

}