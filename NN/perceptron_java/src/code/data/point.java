package code.data;

import java.awt.*;

public class point {
    protected double x, y;
    protected double constant; //1

    public int getItem_class() {
        return item_class;
    }

    int item_class;

    public int get_int_X() {
        return (int) x;
    }

    public int get_int_Y() {
        return (int) y;
    }

    point(double x, double y, int item_class) {
        this.x = x;
        this.y = y;
        constant = 1;
        this.item_class = item_class;
    }

    public double[] get_array() {
        double weight[] = new double[3];
        weight[0] = x;
        weight[1] = y;
        weight[2] = constant;
        return weight;

    }

    public static  point get_line_points(double[]weight, double x)
    {
        // [w1x + w2y + w3 = 0]
        // y= mx+c
        //m = -w1/w2
        //c = -w3/w2
        return new point(x, -(weight[0] * x + weight[2])/weight[1], 0);
    }

    @Override
    public String toString() {
        return "X: " + x + " Y: " + y + "\n";
    }

    public point normalize_against_axis_point( double x, double y) {
        this.x += x;
        // Dont create this /\ pattern
        if (this.y > y)
            this.y = y - this.y;
        else
            this.y = mode(this.y - y);
        return this;
    }
    public double mode(double num) {
        if (num > 0)
            return num;
        return -num;
    }

}
