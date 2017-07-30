package drawer.console;

import drawer.canvas.CanvasDrawer;
import drawer.canvas.Canvas;
import drawer.coordinates.Coordinates;


import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class ConsoleOperations {
    public enum Operation {
        CREATE("C"){
            @Override
            public void perform(StringTokenizer tokenizer, Canvas canvas) {
                int width = retrieveFrom(tokenizer);
                int height = retrieveFrom(tokenizer);
                canvas.setDimension(width, height);
            }
        },
        LINE("L"){
            @Override
            public void perform(StringTokenizer tokenizer, Canvas canvas) {
                Coordinates startingCoordinates = new Coordinates(retrieveFrom(tokenizer), retrieveFrom(tokenizer));
                Coordinates endingCoordinates = new Coordinates(retrieveFrom(tokenizer), retrieveFrom(tokenizer));
                validateCoordinates(canvas, startingCoordinates, endingCoordinates);
                CanvasDrawer.drawLine(canvas, startingCoordinates, endingCoordinates);
            }
        },
        RECTANGLE("R"){
            @Override
            public void perform(StringTokenizer tokenizer, Canvas canvas) {
                Coordinates upperLeftCorner = new Coordinates(retrieveFrom(tokenizer), retrieveFrom(tokenizer));
                Coordinates lowerRightCorner = new Coordinates(retrieveFrom(tokenizer), retrieveFrom(tokenizer));
                validateCoordinates(canvas, upperLeftCorner, lowerRightCorner);
                CanvasDrawer.drawRectangle(canvas, upperLeftCorner, lowerRightCorner);
            }
        },
        PAINT("B") {
            @Override
            public void perform(StringTokenizer tokenizer, Canvas canvas) {
                Coordinates startingPoint = new Coordinates(retrieveFrom(tokenizer), retrieveFrom(tokenizer));
                validateCoordinates(canvas, startingPoint);
                char colour = tokenizer.nextToken().charAt(0);
                validateColour(colour);
                CanvasDrawer.paintArea(canvas, startingPoint, colour);
            }
        },
        QUIT("Q") {
            @Override
            public void perform(StringTokenizer tokenizer, Canvas canvas) {
                System.exit(0);
            }
        };

        private final String command;

        Operation(String command) {
            this.command = command;
        }

        public boolean matchesCommand(String command) {
            return this.command.equals(command);
        }

        public abstract void perform(StringTokenizer tokenizer, Canvas canvas);
    }

    private static int retrieveFrom(StringTokenizer tokenizer) {
        return parseInt(tokenizer.nextToken());
    }

    private static void validateCoordinates(Canvas canvas, Coordinates... arrayOfCoordinates) {
        for (Coordinates coordinates : arrayOfCoordinates){
            if (canvas.isOutOfCanvasRange(coordinates)){
                throw new IllegalArgumentException("Specified coordinates out of an active canvas range!");
            }
        }
    }

    private static void validateColour(char colour) {
        if (colour == 'x'){
            throw new IllegalArgumentException("x is not a valid colour");
        }
    }
}
