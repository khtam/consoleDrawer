package drawer.canvas;



import drawer.coordinates.Coordinates;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Canvas {

    private int width;
    private int height;
    private Map<Coordinates, Character> canvas = new HashMap<>();

    public void setDimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public String print() {
        String printOut = "";
        if (canvasIsCreated()) {
            printOut += printHorizontalMargin();
            printOut = printCanvasContent(printOut);
            printOut += printHorizontalMargin();
        }
        return printOut;
    }

    public boolean isOutOfCanvasRange(Coordinates currentPoint) {
        return currentPoint.getX() < 1 || currentPoint.getX() > width || currentPoint.getY() < 1 || currentPoint.getY() > height;
    }

    public void strokeAt(Coordinates coordinates, char content) {
        this.canvas.put(coordinates, content);
    }

    public Optional<Character> characterAt(Coordinates currentPoint) {
        return Optional.ofNullable(this.canvas.get(currentPoint));
    }

    public boolean canvasNotEmptyAt(Coordinates coordinates) {
        return canvas.get(coordinates) != null;
    }

    private String printCanvasContent(String printOut) {
        for (int y = 1; y <= height; y++) {
            printOut += "|";
            for (int x = 1; x <= width; x++) {
                Coordinates coordinates = new Coordinates(x, y);
                if (canvasNotEmptyAt(coordinates)) {
                    printOut += canvas.get(coordinates);
                } else {
                    printOut += ' ';
                }
            }
            printOut += "|\n";
        }
        return printOut;
    }

    private boolean canvasIsCreated() {
        return width > 0 || height > 0;
    }

    private String printHorizontalMargin() {
        String marginLine = "";
        for (int i = 0; i < width + 2; i++) {
            marginLine += "-";
        }
        return marginLine + "\n";
    }
}
