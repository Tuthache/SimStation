package mvc;

public abstract class Command {
    public Model model;

    public Command(Model model) {
        this.model = model;
    }

    public abstract void execute() throws Exception;
}
