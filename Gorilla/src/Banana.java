import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Banana {

    private double angle;
    private double velocity;
    private double startx;
    private double time;
    private BufferedImage b;
    public int othery;
    public int otherx;
    private double starty;
    public boolean hasfinished;
    public boolean hasLanded;
    public Banana (double angle, double velocity, int x, int y, BufferedImage a, int otherx, int othery) {
        setAngle(angle);
        setVelocity(velocity);
        this.startx = (double)x;
        this.starty = (double)y;
        this.hasfinished = false;
        this.hasLanded = false;
        this.b = a;
        this.time = 0;
        this.otherx = otherx;
        this.othery = othery;
    }
    private void setAngle(double angle) {
        this.angle = angle;
    }

    private void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    private double getAngle() {
        return this.angle;
    }

    private double getVelocity() {
        return this.velocity;
    }
    public void checkCollision(){
        if(startx > 1100 || startx < 0){
            hasfinished = true;
        }
        else{
            if(startx >= otherx && startx < otherx + 25 && starty >= othery && starty < othery + 25){
                hasLanded = true;
        }
            else if (startx <= 105 && starty > 320) {
                hasfinished = true;
        }
            else if (startx > 105 && startx <= 225 && starty > 450) {
                hasfinished = true;
        }
            else if (startx > 225 && startx <= 375 && starty > 560) {
                hasfinished = true;
        }
            else if (startx > 375 && startx <= 470 && starty > 430) {
                hasfinished = true;
        }
            else if (startx > 470 && startx <= 600 && starty > 475) {
                hasfinished = true;
        }
            else if (startx > 600 && startx <= 800 && starty > 380) {
                hasfinished = true;
        }
            else if (startx > 800 && startx <= 875 && starty > 460) {
                hasfinished = true;
        }
            else if (startx > 875 && startx <= 1025 && starty > 400) {
                hasfinished = true;
        }
            else if (startx > 1025 && startx <= 1100 && starty > 425) {
                hasfinished = true;
        }
            else{
        }
        }
    }
    public void moveBanana(double h){
        double t = h;
        startx += velocity * Math.cos(Math.toRadians(angle)) * t ;
        starty -= velocity* Math.sin(Math.toRadians(angle)) * t - 4.905 * time * time;
        time += t;
    }
    public void moveBanana2(double h){
        double t = h;
        startx += velocity   * Math.cos(Math.toRadians(180 - angle)) * t;
        starty -= velocity  * Math.sin(Math.toRadians(180 - angle)) * t - 4.905 * time * time;
        time += t;

    }
    public void drawBan(Graphics g){
        if(starty >= 0){
        g.setColor(Color.magenta);
        g.fillRect((int) Math.round(startx),(int) Math.round(starty), 5, 5);

        }
    }
}
