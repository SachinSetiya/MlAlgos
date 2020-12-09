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

    public ArrayList<DataPoint> getRandomData() {
        cal_num_line();
        ArrayList<DataPoint> data = new ArrayList<DataPoint>(this.points);
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
        double range, y, point_range = spread_from_line - closeness_to_line;
        for (int i = 0; i < points; i++) {
            range = Math.random() * point_range * 2;
            if (range > point_range) {
                range = range / 2 + closeness_to_line;
            } else {
                range = -range / 2 - closeness_to_line;
            }
            //y= mx+c (+ range for randomness)
            y = slope * this.num_line.get(i) + c;
            data.add(new DataPoint(num_line.get(i) + range, y, range > 0 ? 1 : -1));
        }

        return data;
    }

    public ArrayList<DataPoint> normalize_against_axis(ArrayList<DataPoint> input, double x, double y) {
        for (DataPoint ptr : input) {
            ptr.normalize_against_axis_point(x,y);
        }
        //    System.out.println(input);
        return input;
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


    public ArrayList<DataPoint> get_line(double slope, double intercept) {
        ArrayList<DataPoint> pt = new ArrayList<DataPoint>();
        pt.add(new DataPoint(range_negative + 10, slope * (range_negative + 10) + intercept, 0));
        pt.add(new DataPoint(range_positive - 10, slope * (range_positive - 10) + intercept, 0));
        return pt;
    }
}
