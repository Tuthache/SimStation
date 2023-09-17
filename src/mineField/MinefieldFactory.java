package mineField;

import mvc.*;

public class MinefieldFactory implements AppFactory {

    @Override
    public Model makeModel() {
        return new Minefield();
    }

    @Override
    public View makeView(Model model) {
        return new MinefieldView((Minefield) model);
    }

    @Override
    public String getTitle() {
        return "Mine Field";
    }

    @Override
    public String[] getHelp() {
        return new String[] {"Click NW to move northwest.", "Click N to move north.", "Click NE to move northeast.",
                             "Click W to move west.", "Click E to move east.", "Click SW to move southwest.",
                             "Click S to move south.", "Click SE to move southeast."};
    }

    @Override
    public String about() {
        return "Mine Field Simulator version 1.0. Copyright 2023 by Kyle James, Edison Fuh, Steven Bui";
    }

    @Override
    public String[] getEditCommands() {
        return new String[] {"NW", "N", "NE", "W", "E", "SW", "S", "SE"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        switch (type) {
            case "NW" -> {
                return new MoveCommand(model, Heading.NORTHWEST);
            }
            case "N" -> {
                return new MoveCommand(model, Heading.NORTH);
            }
            case "NE" -> {
                return new MoveCommand(model, Heading.NORTHEAST);
            }
            case "W" -> {
                return new MoveCommand(model, Heading.WEST);
            }
            case "E" -> {
                return new MoveCommand(model, Heading.EAST);
            }
            case "SW" -> {
                return new MoveCommand(model, Heading.SOUTHWEST);
            }
            case "S" -> {
                return new MoveCommand(model, Heading.SOUTH);
            }
            case "SE" -> {
                return new MoveCommand(model, Heading.SOUTHEAST);
            }
            default -> {
                return null;
            }
        }
    }
}
