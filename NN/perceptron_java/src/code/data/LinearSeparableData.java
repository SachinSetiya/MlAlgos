package code.data;


import java.util.ArrayList;

public class LinearSeparableData {
    private int points;
    private int dimension;
    private ArrayList<Double> num_line;
    public double range_positive;
    public double range_negative;
    public double c_range_positive;
    public double c_range_negative;
    public double closeness_to_line;
    public double spread_from_line; // how much far point can spread from the line

    public LinearSeparableData(int points, int dimension) {
        this.dimension = dimension;
        this.points = points;
        this.range_negative = -10.0;
        this.range_positive = 10.0;
        this.c_range_negative = -4;
        this.c_range_positive = 4;
        this.closeness_to_line = 5;
        this.spread_from_line = 250;
    }

    public ArrayList<point> getRandomData() {
        cal_num_line();
        ArrayList<point> data = new ArrayList<point>(this.points);
        //TODO for now 2D only

        //y = mx + c

        /*
         * Select any angle from 0 to 180, and then calculate slope
         */
        double f = Math.random() / Math.nextDown(1.0);
        double theta = 90 * f;
        double slope = Math.tan(Math.toRadians(theta));

        //c get a range from c_range_negative to c_range_positive
        double c = f * (c_range_positive - c_range_negative) + c_range_negative;
        System.out.println("slope= " + slope + " Angle= " + theta + "intercept = " + c);
        double range, y, point_range= spread_from_line - closeness_to_line;
        for (int i = 0; i < points; i++) {
            range = Math.random() * point_range*2;
            if (range > point_range) {
                range = range / 2 + closeness_to_line;
            }
            else {
                range = -range / 2 - closeness_to_line;
            }
            System.out.println(range);
            //y= mx+c (+ range for randomness)
            y = slope * this.num_line.get(i) + c;
            data.add(new point(num_line.get(i)+range, y, range>0?1:0));
        }

        return data;
    }

    public ArrayList<point> normalize_against_axis(ArrayList<point> input, double x, double y) {
        for (point ptr : input) {
            ptr.x += x;
            // Dont create this /\ pattern
            if (ptr.y > y)
                ptr.y = y - ptr.y;
            else
                ptr.y = mode(ptr.y - y);
        }
        //    System.out.println(input);
        return input;
    }

    public double mode(double num) {
        if (num > 0)
            return num;
        return -num;
    }

    private ArrayList<Double> cal_num_line() {
        double diff = (range_positive - range_negative) / points, current = range_negative;
        ArrayList<Double> list = new ArrayList<Double>(points);
        for (int i = 0; i < points; i++) {
            list.add(current);
            current += diff;
        }
        this.num_line = list;
        //     System.out.println(list);
        return num_line;
    }


    public static void main(String[] args) {
        LinearSeparableData data = new LinearSeparableData(10, 0);
        System.out.println(data.getRandomData());
    }

    public class point {
        double x, y;

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
            this.item_class= item_class;
        }


        @Override
        public String toString() {
            return "X: " + x + " Y: " + y + "\n";
        }
    }
}
