package code.draw;

import code.data.LinearSeparableData;

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

        //Get the LinearSeparableData data and plot
        LinearSeparableData data= new LinearSeparableData(3000, 2);
        data.range_negative= -400;
        data.range_positive= 400;
        data.c_range_negative= -100;
        data.c_range_positive= 100;
        panel = new Panel(data.normalize_against_axis(data.getRandomData(), 400, 300 ), 0, 0);
        panel.setBackground(Color.WHITE);
        panel.setBounds(54, 35, 800, 600);
        frame.getContentPane().add(panel);
        panel.repaint();
    }

    private class Panel extends JPanel {
        private ArrayList<LinearSeparableData.point> points;
        private double slope, intercept;

        Panel(ArrayList<LinearSeparableData.point> points, double slope, double intercept) {
            super();
            this.points = points;
            this.slope = slope;
            this.intercept = intercept;
        }

        public void paint(Graphics g) {
            super.paint(g);
            System.out.println("ss");
            //Axis thickness
            ((Graphics2D) g).setStroke(new BasicStroke(3));
            //y axis
            g.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
            //x axis
            g.drawLine(0, getHeight() / 2, getWidth(), this.getHeight() / 2);
            System.out.println(this.getWidth());
            for (LinearSeparableData.point point:points)
            {
                if (point.getItem_class() == 1)
                  ((Graphics2D) g).setColor(new Color(150,100,255));
                else
                    ((Graphics2D) g).setColor(new Color(150,200,0));
                g.drawOval(point.get_int_X(),point.get_int_Y(),2,2);
            }
//            g.drawLine(100,200,100,200);
        }
    }
}
