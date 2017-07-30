package drawer.console;

import drawer.canvas.Canvas;
import drawer.console.ConsoleOperations.Operation;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Console {
    private Canvas canvas;

    public Console() {
        this.canvas = new Canvas();
    }

    public void read(String stringCommand) {
        StringTokenizer tokenizer = new StringTokenizer(stringCommand);

        try {
            String command = tokenizer.nextToken();

            for (Operation operation : Operation.values()){
                if (operation.matchesCommand(command)){
                    operation.perform(tokenizer, canvas);
                    return;
                }
            }
            throw new UnsupportedOperationException("This Operation is not supported: " + command);
        } catch (NoSuchElementException e){
            System.out.println("Missing parameter in command!");
        }
    }

    public String draw() {
        return canvas.print();
    }
}
