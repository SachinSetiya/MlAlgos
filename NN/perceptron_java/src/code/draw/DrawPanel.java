package code.draw;

import code.compute.Perceptron;
import code.data.LinePoint;
import code.data.LinearSeparableData;
import code.data.DataPoint;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawPanel {

    private JFrame frame;
    Panel panel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DrawPanel window = new DrawPanel();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public DrawPanel() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        panel= getPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(54, 35, 800, 600);
        frame.getContentPane().add(panel);
        panel.repaint();
    }

    private class Panel extends JPanel {
        private ArrayList<DataPoint> points;
        private LinePoint line_point;

        Panel(ArrayList<DataPoint> points, LinePoint line_points) {
            super();
            this.points = points;
            this.line_point = line_points;
        }

        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2= (Graphics2D) g;
         //   System.out.println("ss");
            //Axis thickness
            g2.setStroke(new BasicStroke(3));
            //y axis
            g.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
            //x axis
            g.drawLine(0, getHeight() / 2, getWidth(), this.getHeight() / 2);
       //     System.out.println(this.getWidth());
            for (DataPoint point : points) {
                point.draw(g2);
            }
            line_point.draw(g2);

        //    System.out.println(line_point);
        }
    }

    private Panel getPanel()
    {
        //Get the LinearSeparableData data and plot
        LinearSeparableData data = new LinearSeparableData(3000, 2);
        data.range_negative = -400;
        data.range_positive = 400;
        data.c_range_negative = -200;
        data.c_range_positive = 1;
        ArrayList<DataPoint> points = data.getRandomData();
        Perceptron perceptron= new Perceptron(points);
        perceptron.init();
        perceptron.fit(null);
        panel = new Panel(data.normalize_against_axis(points, 400, 300),
                new LinePoint(perceptron.weights, 400,-400, 400,300));
        return  panel;
    }
}
