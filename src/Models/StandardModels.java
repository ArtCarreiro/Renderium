package Models;

import java.awt.*;

public class StandardModels {

    public static class Vertex {
        public double x;
        public double y;
        public double z;

        public Vertex(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static class Triangle {
        public Vertex v1;
        public Vertex v2;
        public Vertex v3;
        public Color color;

        public Triangle(Vertex v1, Vertex v2, Vertex v3, Color color) {
            this.v1 = v1;
            this.v2 = v2;
            this.v3 = v3;
            this.color = color;
        }
    }
}
