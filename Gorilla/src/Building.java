import java.awt.*;
public class Building {
    private int height;
    private int width;
    private Color colour;

    private void setHeight(int height) {
        this.height = height;
    }

    private void setWidth(int width) {
        this.width = width;
    }

    private void setColour(Color colour) {
        this.colour = colour;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public Color getColour() {
        return this.colour;
    }

    public Building (int height, int width, Color colour) {
        setHeight(height);
        setWidth(width);
        setColour(colour);
    }
}