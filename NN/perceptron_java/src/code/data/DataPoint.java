package code.data;

import code.draw.Drawing;

import java.awt.*;

public class DataPoint extends point implements Drawing  {

    DataPoint(double x, double y, int item_class) {
        super(x,y,item_class);
    }

    @Override
    public void draw(Graphics2D g) {
        if (getItem_class() > 0)
            ((Graphics2D) g).setColor(new Color(150, 100, 255));
        else
            ((Graphics2D) g).setColor(new Color(150, 200, 0));
        g.drawOval(get_int_X(), get_int_Y(), 2, 2);
    }
}