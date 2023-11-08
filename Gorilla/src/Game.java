import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import javax.swing.*;
public class Game extends JPanel implements KeyListener{
    Player player1;
    Player player2;
    double angle, velocity, ang, vel;
    int count;
    long elapsedTime ;
    private JPanel me;
    private BufferedImage b = new BufferedImage(1100,800,BufferedImage.TYPE_INT_ARGB);
    public boolean flying = false;
    Map map;
   Graphics g;
   long fps;
    public Game(){
        map = new Map();
        me = this;
        addKeyListener( this);
        setPlayer1();
        setPlayer2();
        setOtherCors();
        setFocusable(true);
        elapsedTime = System.nanoTime();
        this.angle = 45;
        this.count = 0;
        this.vel = 250;
        this.ang = 45;
        this.velocity = 250;
        g = b.createGraphics();
        paintComponent(g);
        thread.start();
    }

    public void keyTyped(KeyEvent e) {

    }
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W && !flying && angle < 90 && count % 2 == 0){
            angle += 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_S && !flying && angle > 0 && count % 2 == 0){
            angle -= 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_D && !flying && velocity < 1500 && count % 2 == 0){
            velocity += 10;
        }
        else if(e.getKeyCode() == KeyEvent.VK_A && !flying && vel > 0 && count % 2 == 0){
            velocity -= 10;
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP && !flying && ang < 90 && count % 2 == 1){
            ang += 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN && !flying && ang > 0 && count % 2 == 1){
            ang -= 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT && !flying && vel < 1500 && count % 2 == 1){
            vel += 10;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT && !flying && velocity > 0 && count % 2 == 1){
            vel -= 10;
        }
        else if(e.getKeyCode()  == KeyEvent.VK_SPACE && !flying){
            if(count % 2 == 0){
                getPlayer1().attack(angle, velocity , b);
            }
            else{
                getPlayer2().attack(ang, vel, b);
            }
            flying = true;
            count++;
        }
    }


    public void keyReleased(KeyEvent e) {

    }
    public void setPlayer1() {
        this.player1 = new Player(0, 1, map.g1r, 800 - map.g1h - 25);
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public void setPlayer2() {
        this.player2 = new Player(0, 2, 1100 - map.g2r, 800 - map.g2h - 25);
    }
    public void setOtherCors(){
        player1.setOthers(1100 - map.g2r, 800 - map.g2h - 25);
        player2.setOthers(map.g1r, 800 - map.g1h - 25);
    }
    public Player getPlayer2() {
        return this.player2;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        map.drawCity(g);
        if(!getPlayer1().sol.isEmpty()){
            getPlayer1().sol.get(0).drawBan(g);
        }
        else if(!getPlayer2().sol.isEmpty()){
            getPlayer2().sol.get(0).drawBan(g);
        }
        else if(!flying && count % 2 == 0){
            g.setColor(Color.cyan);
            g.drawLine(map.g1r + 25, 800 - map.g1h , (int) (map.g1r + 25 + Math.cos(Math.toRadians(angle)) * velocity / 10), (int)((800 - map.g1h) - Math.sin(Math.toRadians(angle)) * velocity / 10));
        }
        else if(!flying && count % 2 == 1){
            g.setColor(Color.cyan);
            g.drawLine(1100 - map.g2r,800 - map.g2h , (int)((1100 - map.g2r) + Math.cos(Math.toRadians(180 - ang)) * vel / 10), (int)((800 -map.g2h) - Math.sin(Math.toRadians(180 - ang)) * vel / 10) );
        }

        g.setColor(Color.MAGENTA);
        g.drawString("Score: " + player1.getScore(), 100, 100);
        g.drawString("Score: " + player2.getScore(), 1000, 100);
        g.setColor(Color.red);

    }
    private Thread thread = new Thread(new Runnable() {
        public void run() {
            try {
                double t = 0.01;
                while(map.inApp) {
                    if(!flying){
                    }
                    else{
                        if(!getPlayer1().sol.isEmpty() && getPlayer1().sol.get(0).hasLanded){
                            flying = false;
                            int k = getPlayer1().getScore() + 10;
                            getPlayer1().setScore(k);
                            getPlayer1().sol.remove(0);
                            map.getrandom();
                            player2.setX(1100 - map.g2r);
                            player2.setY(800 - map.g2h - 25);
                            player1.setOthers(1100 - map.g2r, 800 - map.g2h - 25);


                        }
                        else if(!getPlayer2().sol.isEmpty() && getPlayer2().sol.get(0).hasLanded){
                            flying = false;
                            int k = getPlayer2().getScore() + 10;
                            getPlayer2().setScore(k);
                            getPlayer2().sol.remove(0);
                            map.getrandoms();
                            player1.setX(map.g1r);
                            player1.setY(800 - map.g1h - 25);
                            player2.setOthers(map.g1r, 800 - map.g1h - 25);

                        }
                        else if(!getPlayer1().sol.isEmpty() && getPlayer1().sol.get(0).hasfinished){
                            flying = false;
                            getPlayer1().sol.remove(0);

                        }
                        else if(!getPlayer2().sol.isEmpty() && getPlayer2().sol.get(0).hasfinished){
                            flying = false;
                            getPlayer2().sol.remove(0);

                        }
                        else if(count % 2 == 1){
                            getPlayer1().sol.get(0).moveBanana(t);
                            getPlayer1().sol.get(0).checkCollision();

                        }
                        else{
                            getPlayer2().sol.get(0).moveBanana2(t);
                            getPlayer2().sol.get(0).checkCollision();
                        }
                    }
                    fps = 1000000000 / (System.nanoTime() - elapsedTime);
                    elapsedTime = System.nanoTime();
                    thread.sleep((long) (t * 1000));
                    me.repaint();
                }
            }
            catch(InterruptedException ex) {
            }
        }
    });
}
