import javax.swing.*;

public class PlainJButton extends JButton {

    public PlainJButton (String text){
        super(text);
        setBorder(null);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
    }

}
