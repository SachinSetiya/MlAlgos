package code.compute;

import code.data.DataPoint;
import code.data.LinearSeparableData;

import javax.swing.*;
import java.util.ArrayList;

public class Perceptron {
    private ArrayList<DataPoint> data;
    public double[] weights;

    public Perceptron(ArrayList<DataPoint> data) {
        this.data = data;
    }

    public void init() {
        weights = new double[3];
        weights[0] = Math.random();
        weights[1] = Math.random();
        weights[2] = Math.random(); //bias
    }

    /*
       line=     x+y+1 = 0  , [x y 1]
       weights=  w1, w2, w3,  W Transpose= [w1 w2 w3]

     */
    public void fit(JPanel panel) {
        int i = 100, j=1;
        while (i > 0) {
            for (DataPoint point : data) {
                if (dot_product(weights, point.get_array()) * point.getItem_class() < 0) {
                    oper(weights, scaler_product(point.get_array(), .01), (point.getItem_class() > 0) ? true : false);
                }
                if (j % 100 == 0)
                    System.out.println(weights[0] + "   " + weights[1] + "   " + weights[2]);
                j++;
            }
            i--;
        }

    }

    public static double dot_product(double[] arr1, double[] arr2) {
        assert (arr1.length == arr2.length);
        double sum = 0;
        for (int i = 0; i < arr1.length; i++) {
            sum += arr1[i] * arr2[i];
        }
        return sum;
    }

    public static void oper(double arr1[], double arr2[], boolean sum) {
        assert (arr1.length == arr2.length);
        for (int i = 0; i < arr1.length; i++) {
            if (sum)
                arr1[i] = arr1[i] + arr2[i];
            else
                arr1[i] = arr1[i] - arr2[i];
        }
    }
    public static double[] scaler_product(double[] arr1, double scale) {
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = arr1[i] * scale;
        }
        return  arr1;
    }
}
