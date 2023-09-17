package bc;
import mvc.*;


public class SetWidth extends Command {
    public Double newValue;
    public SetWidth(Model brick) {
        super(brick);
        newValue = null;
    }

    public void execute() {
        Brick brick = (Brick)model;
        if (newValue == null) {
            String resp = Utilities.ask("New Value?");
            newValue = Double.parseDouble(resp);
        }
        brick.setWidth(newValue);
    }
}