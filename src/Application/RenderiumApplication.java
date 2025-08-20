package Application;

import Models.StandardModels.*;
import Renderium.Matrix3;
import Renderium.Renderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RenderiumApplication {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Renderium");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JSlider headingSlider = new JSlider(0, 360, 180);
        JSlider pitchSlider = new JSlider(SwingConstants.VERTICAL, -90, 90, 0);


        List<Triangle> tris = new ArrayList<>();
        // face traseira
        tris.add(new Triangle(new Vertex(-100,-100,-100), new Vertex(100,-100,-100), new Vertex(100,100,-100), Color.RED));
        tris.add(new Triangle(new Vertex(-100,-100,-100), new Vertex(100,100,-100), new Vertex(-100,100,-100), Color.RED));
        // face frontal
        tris.add(new Triangle(new Vertex(-100,-100,100), new Vertex(100,-100,100), new Vertex(100,100,100), Color.GREEN));
        tris.add(new Triangle(new Vertex(-100,-100,100), new Vertex(100,100,100), new Vertex(-100,100,100), Color.GREEN));
        // face esquerda
        tris.add(new Triangle(new Vertex(-100,-100,-100), new Vertex(-100,100,-100), new Vertex(-100,100,100), Color.BLUE));
        tris.add(new Triangle(new Vertex(-100,-100,-100), new Vertex(-100,100,100), new Vertex(-100,-100,100), Color.BLUE));
        // face direita
        tris.add(new Triangle(new Vertex(100,-100,-100), new Vertex(100,100,-100), new Vertex(100,100,100), Color.YELLOW));
        tris.add(new Triangle(new Vertex(100,-100,-100), new Vertex(100,100,100), new Vertex(100,-100,100), Color.YELLOW));
        // face inferior
        tris.add(new Triangle(new Vertex(-100,-100,-100), new Vertex(100,-100,-100), new Vertex(100,-100,100), Color.CYAN));
        tris.add(new Triangle(new Vertex(-100,-100,-100), new Vertex(100,-100,100), new Vertex(-100,-100,100), Color.CYAN));
        // face superior
        tris.add(new Triangle(new Vertex(-100,100,-100), new Vertex(100,100,-100), new Vertex(100,100,100), Color.MAGENTA));
        tris.add(new Triangle(new Vertex(-100,100,-100), new Vertex(100,100,100), new Vertex(-100,100,100), Color.MAGENTA));

        Renderer renderer = new Renderer(tris);

        JPanel renderPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                double heading = Math.toRadians(headingSlider.getValue());
                double pitch = Math.toRadians(pitchSlider.getValue());

                Matrix3 transform = new Matrix3(new double[]{
                        Math.cos(heading),0,-Math.sin(heading),
                        0,1,0,
                        Math.sin(heading),0,Math.cos(heading)
                }).multiply(new Matrix3(new double[]{
                        1,0,0,
                        0,Math.cos(pitch),Math.sin(pitch),
                        0,-Math.sin(pitch),Math.cos(pitch)
                }));

                renderer.render(g, getWidth(), getHeight(), transform);
            }
        };

        frame.setLayout(new BorderLayout());
        frame.add(renderPanel, BorderLayout.CENTER);
        frame.add(headingSlider, BorderLayout.SOUTH);
        frame.add(pitchSlider, BorderLayout.EAST);
        Timer timer = new Timer(16, e -> renderPanel.repaint());
        timer.start();

        frame.setVisible(true);
    }
}
