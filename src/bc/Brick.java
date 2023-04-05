package bc;
import mvc.Model;

public class Brick extends Model {
    private double height, width, length;

    public Brick(double height, double width, double length) {
        super();
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public Brick() {
        this(70, 70, 140);
    }

    // getters:
    public double getHeight() { return height; }
    public double getLength() { return length; }
    public double getWidth()  { return width; }

    // setters:
    public void setHeight(double height) {
        this.height = height;
        System.out.println("changing height to : " + height);
        changed(); // notify views of the change
    }


    public void setLength(double length) {
        this.length = length;
        changed(); // notify views of the change
    }

    public void setWidth(double width) {
        this.width = width;
        changed(); // notify views of the change
    }

}
