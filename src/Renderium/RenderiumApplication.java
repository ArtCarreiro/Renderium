package Renderium;

import Models.StandardModels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class RenderiumApplication {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Renderium");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        JSlider headingSlider = new JSlider(0, 360, 180);
        pane.add(headingSlider, BorderLayout.SOUTH);

        JSlider pitchSlider = new JSlider(SwingConstants.VERTICAL, -90, 90, 0);
        pane.add(pitchSlider, BorderLayout.EAST);

        List<Triangle> tris = new ArrayList<>();
        tris.add(new Triangle(new Vertex(100, 100, 0),
                new Vertex(-100, -100, 0),
                new Vertex(-100, 100, 0),
                Color.WHITE));
        tris.add(new Triangle(new Vertex(100, 100, 0),
                new Vertex(-100, -100, 0),
                new Vertex(100, -100, 0),
                Color.RED));
        tris.add(new Triangle(new Vertex(-100, 100, 0),
                new Vertex(100, -100, 0),
                new Vertex(100, 100, 0),
                Color.GREEN));
        tris.add(new Triangle(new Vertex(-100, 100, 0),
                new Vertex(100, -100, 0),
                new Vertex(-100, -100, 0),
                Color.BLUE));

        JPanel renderPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, getWidth(), getHeight());

                g2.translate(getWidth() / 2, getHeight() / 2);

                for (Triangle t : tris) {
                    Path2D path = new Path2D.Double();
                    path.moveTo(t.v1.x, t.v1.y);
                    path.lineTo(t.v2.x, t.v2.y);
                    path.lineTo(t.v3.x, t.v3.y);
                    path.closePath();

                    g2.setColor(t.color);
                    g2.fill(path);   // desenha preenchido
                    g2.setColor(Color.BLACK);
                    g2.draw(path);   // borda preta
                }
            }
        };
        pane.add(renderPanel, BorderLayout.CENTER);

        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
