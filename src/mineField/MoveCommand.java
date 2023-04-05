package mineField;

import mvc.*;

public class MoveCommand extends Command {
    Heading heading; // TODO find a way to use the heading here?
    public MoveCommand(Model model, Heading heading) {
        super(model);
        this.heading = heading;
    }

    public void execute() throws Exception {
        if (!(model instanceof Minefield)) {
            throw new Exception("Model must instantiate Minefield");
        }
        Minefield field = (Minefield)model;

        switch (heading) {
            case NORTH -> field.movePlayer(0, -1);
            case NORTHEAST -> field.movePlayer(1, -1);
            case EAST -> field.movePlayer(1, 0);
            case SOUTHEAST -> field.movePlayer(1, 1);
            case SOUTH -> field.movePlayer(0, 1);
            case SOUTHWEST -> field.movePlayer(-1, 1);
            case WEST -> field.movePlayer(-1, 0);
            case NORTHWEST -> field.movePlayer(-1, -1);
        }

    }
}
