package bc;
import mvc.*;


public class SetLength extends Command {
    public Double newValue;
    public SetLength(Model brick) {
        super(brick);
        newValue = null;
    }

    public void execute() {
        Brick brick = (Brick)model;
        if (newValue == null) {
            String resp = Utilities.ask("New Value?");
            newValue = Double.parseDouble(resp);
        }
        brick.setLength(newValue);
    }
}