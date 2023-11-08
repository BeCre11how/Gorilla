import javax.swing.*;

class View extends JFrame {

    public void build(){
        setSize(1100,800);
        setContentPane(new Game());
        setVisible(true);
    }
}
