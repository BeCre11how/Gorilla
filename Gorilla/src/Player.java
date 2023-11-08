import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player{
    private int playerNumber;
    private int score;
    public int x, y;
    public int otherx, othery;
    public List<Banana> sol;
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setOthers(int x, int y){
        this.otherx = x;
        this.othery = y;
    }
    public void setY(int y){
        this.y = y;
    }
    public Player(int score, int playerNumber, int x, int y) {
        setScore(score);
        setPlayerNumber(playerNumber);
        this.x = x;
        this.y = y;
        sol = new ArrayList<>();
    }
    public void attack(double angle, double velocity, BufferedImage b) {
        if(playerNumber == 1){
            sol.add(new Banana( angle, velocity, x + 25, y + 20, b , otherx, othery));

        }
        else{
       sol.add(new Banana(angle, velocity, x , y + 20, b, otherx, othery));}

    }
}