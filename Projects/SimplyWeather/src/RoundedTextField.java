import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JTextField;



public class RoundedTextField extends JTextField{
    public int cornerRadius;

    public RoundedTextField(int cornerRadius){
        this.cornerRadius = cornerRadius;
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder()); // Remove default border
    }  

    @Override
    public void paintComponent(Graphics g){
        
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();


        //Drawing the background with rounded corners
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);

        //Drawing the Text
        super.paintComponent(g);
        g2d.dispose();

    }
    @Override
    public Insets getInsets(){
        return new Insets(5, 15, 5, 5);
    }
}
