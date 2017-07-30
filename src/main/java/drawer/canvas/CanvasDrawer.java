package drawer.canvas;


import drawer.coordinates.Coordinates;
import drawer.coordinates.CoordinatesDisplacement;

public class CanvasDrawer {

    public static void drawLine(Canvas canvas, Coordinates startingCoordinates, Coordinates endingCoordinates) {
        CoordinatesDisplacement displacement = startingCoordinates.findDisplacementTo(endingCoordinates);

        if (displacement.isVerticalOnly()) {
            drawVerticalLine(canvas, startingCoordinates, endingCoordinates, displacement);
        }

        if (displacement.isHorizontalOnly()) {
            drawHorizontalLine(canvas, startingCoordinates, endingCoordinates, displacement);
        }
    }

    public static void drawRectangle(Canvas canvas, Coordinates upperLeftCorner, Coordinates lowerRightCorner) {
        Coordinates upperRightCorner = new Coordinates(lowerRightCorner.getX(), upperLeftCorner.getY());
        Coordinates lowerLeftCorner = new Coordinates(upperLeftCorner.getX(), lowerRightCorner.getY());

        drawLine(canvas, upperLeftCorner, upperRightCorner);
        drawLine(canvas, upperRightCorner, lowerRightCorner);
        drawLine(canvas, lowerRightCorner, lowerLeftCorner);
        drawLine(canvas, lowerLeftCorner, upperLeftCorner);
    }

    public static void paintArea(Canvas canvas, Coordinates currentPoint, char colour) {
        if (canvas.isOutOfCanvasRange(currentPoint)) {
            return;
        }
        if (pointIsOnEdge(canvas, currentPoint) || pointOccupiedByChar(canvas, currentPoint, colour)){
            return;
        } else{
            canvas.strokeAt(currentPoint, colour);

            Coordinates eastPoint = new Coordinates(currentPoint.getX() - 1, currentPoint.getY());
            paintArea(canvas, eastPoint, colour);

            Coordinates southPoint = new Coordinates(currentPoint.getX(), currentPoint.getY() + 1);
            paintArea(canvas, southPoint, colour);

            Coordinates westPoint = new Coordinates(currentPoint.getX() + 1, currentPoint.getY());
            paintArea(canvas, westPoint, colour);

            Coordinates northPoint = new Coordinates(currentPoint.getX(), currentPoint.getY() - 1);
            paintArea(canvas, northPoint, colour);
        }
    }

    private static boolean pointIsOnEdge(Canvas canvas, Coordinates currentPoint) {
        return canvas.canvasNotEmptyAt(currentPoint) && pointOccupiedByChar(canvas, currentPoint, 'x');
    }

    private static boolean pointOccupiedByChar(Canvas canvas, Coordinates currentPoint, char targetChar) {
        return canvas.characterAt(currentPoint).orElse('\u0000') == targetChar;
    }

    private static void drawHorizontalLine(Canvas canvas, Coordinates startingCoordinates, Coordinates endingCoordinates, CoordinatesDisplacement displacement) {
        if (displacement.getX() > 0) {
            drawToTheRight(canvas, startingCoordinates, endingCoordinates);
        } else {
            drawToTheLeft(canvas, startingCoordinates, endingCoordinates);
        }
    }

    private static void drawToTheLeft(Canvas canvas, Coordinates startingCoordinates, Coordinates endingCoordinates) {
        for (int x = startingCoordinates.getX(); x >= endingCoordinates.getX(); x--) {
            canvas.strokeAt(new Coordinates(x, startingCoordinates.getY()), 'x');
        }
    }

    private static void drawToTheRight(Canvas canvas, Coordinates startingCoordinates, Coordinates endingCoordinates) {
        for (int x = startingCoordinates.getX(); x <= endingCoordinates.getX(); x++) {
            canvas.strokeAt(new Coordinates(x, startingCoordinates.getY()), 'x');
        }
    }

    private static void drawVerticalLine(Canvas canvas, Coordinates startingCoordinates, Coordinates endingCoordinates, CoordinatesDisplacement displacement) {
        if (displacement.getY() > 0) {
            drawDownwards(canvas, startingCoordinates, endingCoordinates);
        } else {
            drawUpwards(canvas, startingCoordinates, endingCoordinates);
        }
    }

    private static void drawUpwards(Canvas canvas, Coordinates startingCoordinates, Coordinates endingCoordinates) {
        for (int y = startingCoordinates.getY(); y >= endingCoordinates.getY(); y--) {
            canvas.strokeAt(new Coordinates(startingCoordinates.getX(), y), 'x');
        }
    }

    private static void drawDownwards(Canvas canvas, Coordinates startingCoordinates, Coordinates endingCoordinates) {
        for (int y = startingCoordinates.getY(); y <= endingCoordinates.getY(); y++) {
            canvas.strokeAt(new Coordinates(startingCoordinates.getX(), y), 'x');
        }
    }
}
