import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Map extends JPanel{

    private ArrayList<Building> city;
    private Image icon1;
    private Image icon3;
    public boolean inApp = false;
    private Image icon2;
    public int g1r, g2r, g1h, g2h;
    public Map() {
        city = new ArrayList<>();
        createBuildings();
        getrandoms();
        getrandom();
        icon1 = new ImageIcon("Pictures/Gorilla.png").getImage();
        icon2 = new ImageIcon("Pictures/Gorilla2.png").getImage();
        icon3 = new ImageIcon("Pictures/Gorilla3.gif").getImage();
        inApp = true;
    }

    public void setCity(ArrayList<Building> city) {
        this.city = city;
    }
    public void getrandom(){
        g2r = (int)(Math.random() * 300);
        if(g2r <= 75 ){
            if(g2r < 25){
                g2r += 25;
            }
            g2h = city.get(8).getHeight();
        }
        else if(g2r <= 225 ){
            if(g2r < 100){
                g2r += 25;
            }
            g2h = city.get(7).getHeight();
        }
        else{
            if(g2r < 250){
                g2r += 25;
            }
            g2h = city.get(6).getHeight();
        }
    }
    public void getrandoms(){
        g1r = (int)(Math.random() * 340);

        if(g1r <= 105 ){
            if(g1r < 25){
                g1r += 25;
            }
            g1h = city.get(0).getHeight();
        }
        else if(g1r <= 225 ){
            if(g1r < 130){
                g1r += 25;
            }
            g1h = city.get(1).getHeight();
        }
        else{
            if(g1r < 250){
                g1r += 25;
            }
            g1h = city.get(2).getHeight();
        }
    }

    public ArrayList<Building> getCity() {
        return this.city;
    }
    public void createBuildings(){
        city.add(new Building(480, 105, Color.green));
        city.add(new Building(350, 120,Color.red));
        city.add(new Building(240,150,Color.orange));
        city.add(new Building(370, 95, Color.green));
        city.add(new Building(325, 130, Color.red));
        city.add(new Building(420, 200, Color.orange));
        city.add(new Building(340,75,Color.green));
        city.add(new Building(400,150,Color.red));
        city.add(new Building(375, 75, Color.orange));
    }

    public void drawCity(Graphics h){
        Graphics2D g = (Graphics2D) h;
        int sum = 0;
        for(int i = 0; i < city.size(); i++){
            g.setColor(city.get(i).getColour());
            g.fillRect(0 + sum, 800 - city.get(i).getHeight(), city.get(i).getWidth(), city.get(i).getHeight());
            sum += city.get(i).getWidth();
        }
        g.setColor(Color.blue);
        sum = 0;
        for(int i = 0; i < city.size(); i++){
            int wdth = 10;
            while(wdth < city.get(i).getWidth()){
                int kur = 20;
                while(kur < city.get(i).getHeight()){
                    g.fillRect(wdth + sum, 800 - city.get(i).getHeight() + kur, 10, 15);
                    kur += 25;
                }
                wdth += 24;
            }
            sum += city.get(i).getWidth();
        }
        g.drawImage(icon1, g1r, 800 - g1h - 25,25, 25 ,this);
        g.drawImage(icon2,1100 - g2r, 800 - g2h - 25, 25, 25, this);
    }
}
