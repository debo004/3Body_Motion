import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class z2 extends JPanel implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NUM_BODIES = 3;
    private static final double G = 6.67430e-11; // Gravitational constant

    private Body[] bodies;

    public z2() {
        // Initialize the celestial bodies with initial conditions
        bodies = new Body[NUM_BODIES];
        bodies[0] = new Body(400, 300, 0, 0, 1e13); // Body 1
        bodies[1] = new Body(300, 300, 0, 2, 1e11); // Body 2
        bodies[2] = new Body(500, 300, 0, -2, 1e11); // Body 3

        Timer timer = new Timer(1, this); // Update every 1 ms
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        // Update the positions and velocities of the bodies
        for (int i = 0; i < NUM_BODIES; i++) {
            for (int j = 0; j < NUM_BODIES; j++) {
                if (i != j) {
                    bodies[i].calculateForce(bodies[j]);
                }
            }
            bodies[i].update();
        }

        repaint(); // Redraw the simulation
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the grid plane
        g.setColor(Color.LIGHT_GRAY);
        int gridSize = 7; // Size of each grid cell
        for (int x = 0; x < getWidth(); x += gridSize) {
            for (int y = 0; y < getHeight(); y += gridSize) {
                g.drawRect(x, y, gridSize, gridSize);
            }
        }

        // Draw the bodies
        for (Body body : bodies) {
            body.draw(g);
        }
    }

    private class Body {
        double x, y; // Position
        double vx, vy; // Velocity
        double mass;
        Color color;

        public Body(double x, double y, double vx, double vy, double mass) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.mass = mass;
            this.color = Color.BLACK;
        }

        public void calculateForce(Body other) {
            double dx = other.x - x;
            double dy = other.y - y;
            double r = Math.sqrt(dx * dx + dy * dy);
            double f = (G * mass * other.mass) / (r * r);
            double angle = Math.atan2(dy, dx);
            double fx = f * Math.cos(angle);
            double fy = f * Math.sin(angle);

            vx += fx / mass;
            vy += fy / mass;
        }

        public void update() {
            x += vx;
            y += vy;
        }

        public void draw(Graphics g) {
            g.setColor(color); // Set the particle's color
            int radius = 6; // Increase the radius
            g.fillOval((int) (x - radius), (int) (y - radius), 2 * radius, 2 * radius);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Three-Body Simulation");
        z2 simulation = new z2();
        frame.add(simulation);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
