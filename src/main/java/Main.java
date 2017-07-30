import drawer.console.Console;

public class Main {

    public static void main(String[] args) {
        Console consoleDrawer = new Console();
        String command;
        do {
            System.out.print("enter command: ");
            java.io.Console console = System.console();
            command = console.readLine();
            consoleDrawer.read(command);
            System.out.println(consoleDrawer.draw());
        } while (true);
    }
}
