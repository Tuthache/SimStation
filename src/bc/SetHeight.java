package bc;
import mvc.*;


public class SetHeight extends Command {
    public Double newValue;
    public SetHeight(Model brick) {
        super(brick);
        newValue = null;
    }

    public void execute() {
        Brick brick = (Brick)model;
        if (newValue == null) {
            String resp = Utilities.ask("New Value?");
            newValue = Double.parseDouble(resp);
        }
        brick.setHeight(newValue);
    }
}