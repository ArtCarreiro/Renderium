package Renderium;

import Models.StandardModels.Triangle;
import Models.StandardModels.Vertex;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Renderer {

    private final List<Triangle> triangles;

    public Renderer(List<Triangle> triangles) {
        this.triangles = triangles;
    }

    public void render(Graphics g, int width, int height, Matrix3 transform) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        double[] zBuffer = new double[width * height];
        for (int i = 0; i < zBuffer.length; i++) zBuffer[i] = Double.POSITIVE_INFINITY;

        for (Triangle t : triangles) {

            Vertex v1 = transform.transform(t.v1);
            Vertex v2 = transform.transform(t.v2);
            Vertex v3 = transform.transform(t.v3);

//          Vertex edge1 = v2.subtract(v1);
//          Vertex edge2 = v3.subtract(v1);
//          Vertex normal = edge1.cross(edge2);
//          if (normal.z > 0) continue;

            v1.x += width / 2; v1.y += height / 2;
            v2.x += width / 2; v2.y += height / 2;
            v3.x += width / 2; v3.y += height / 2;

            int minX = (int) Math.max(0, Math.ceil(Math.min(v1.x, Math.min(v2.x, v3.x))));
            int maxX = (int) Math.min(width - 1, Math.floor(Math.max(v1.x, Math.max(v2.x, v3.x))));
            int minY = (int) Math.max(0, Math.ceil(Math.min(v1.y, Math.min(v2.y, v3.y))));
            int maxY = (int) Math.min(height - 1, Math.floor(Math.max(v1.y, Math.max(v2.y, v3.y))));

            double area = (v1.y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - v1.x);

            for (int y = minY; y <= maxY; y++) {
                for (int x = minX; x <= maxX; x++) {
                    double b1 = ((y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - x)) / area;
                    double b2 = ((y - v1.y) * (v3.x - v1.x) + (v3.y - v1.y) * (v1.x - x)) / area;
                    double b3 = ((y - v2.y) * (v1.x - v2.x) + (v1.y - v2.y) * (v2.x - x)) / area;

                    if (b1 >= 0 && b2 >= 0 && b3 >= 0) {
                        double depth = b1 * v1.z + b2 * v2.z + b3 * v3.z;
                        int zIndex = y * width + x;
                        if (zBuffer[zIndex] > depth) {
                            img.setRGB(x, y, t.color.getRGB());
                            zBuffer[zIndex] = depth;
                        }
                    }
                }
            }
        }
        g.drawImage(img, 0, 0, null);
    }
}
