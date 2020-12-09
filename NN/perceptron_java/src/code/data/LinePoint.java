package code.data;

import code.draw.Drawing;

import java.awt.*;
import java.util.ArrayList;

public class LinePoint implements Drawing {

    point firstpoint, secondPoint;

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(150, 0, 0));
        g.drawLine(firstpoint.get_int_X(), firstpoint.get_int_Y(), secondPoint.get_int_X(),
                secondPoint.get_int_Y());
    }

    public LinePoint(double[] weight_vector, double range_positive,double range_negative, double normal_x, double normal_y) {
        firstpoint =  point.get_line_points(weight_vector, range_positive).normalize_against_axis_point(normal_x,normal_y);
        secondPoint = point.get_line_points(weight_vector, range_negative).normalize_against_axis_point(normal_x,normal_y);
    }

    public LinePoint(ArrayList<DataPoint> list) {
        this.firstpoint = list.get(0);
        this.secondPoint = list.get(1);
    }

}
