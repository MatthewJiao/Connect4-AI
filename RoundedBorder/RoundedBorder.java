



//import items needed to create circle class
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;


public class RoundedBorder extends JLabel {

    Color shapeColor = Color.WHITE; // white is initial color

    public RoundedBorder () {
        setOpaque(false); // Opaque is false
    }

    // getDiameter makes sure circles fit
    private int getDiameter() {
        int diameter = Math.min(getWidth(), getHeight());  // the max size the circles can be
        return diameter;
    }


    // automatically called when the program starts
    public void paint(Graphics g) { // cannot be private

        // makes circles the right size
        int diameter = getDiameter()-10;
        int radius = diameter / 2;

        // (re)setting the color
        g.setColor(shapeColor);
        g.fillOval(getWidth() / 2 - radius, getHeight() / 2 - radius, diameter, diameter); // x,y, dimention, dimension

// makes border of circles black
        g.setColor(Color.BLACK);
        g.drawOval(getWidth() / 2 - radius, getHeight() / 2 - radius, diameter, diameter); // x,y, dimention, dimension

    }

    public void setColor(Color col) {
        shapeColor = col;
    }

}


 




