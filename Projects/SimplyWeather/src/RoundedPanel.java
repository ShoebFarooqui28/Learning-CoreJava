import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private final int arcWidth;
    private final int arcHeight;

    // Constructor to set the corner arc dimensions
    public RoundedPanel(int arcWidth, int arcHeight) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setOpaque(false); // Make sure the panel is transparent to see the rounded corners
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw a rounded rectangle with the specified arc width and height
        g2d.setColor(getBackground()); // Use the background color of the panel
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rounded Panel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new FlowLayout());

        // Create a rounded panel with arc dimensions of 30x30 pixels
        RoundedPanel roundedPanel = new RoundedPanel(30, 30);
        roundedPanel.setBackground(Color.CYAN); // Set the background color

        // Add the rounded panel to the frame
        frame.add(roundedPanel);

        frame.setVisible(true);
    }
}
