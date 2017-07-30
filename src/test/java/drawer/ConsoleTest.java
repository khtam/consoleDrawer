package drawer;

import drawer.console.Console;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class ConsoleTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldCreateCanvasOnCommand() {
        Console console = new Console();

        console.read("C 20 6");

        assertEquals(
                "----------------------\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------\n", console.draw());
    }

    @Test
    public void shouldDrawLineOnCommand() throws Exception {
        Console console = new Console();
        console.read("C 20 4");

        console.read("L 1 2 6 2");
        console.read("L 6 3 6 4");

        assertEquals("----------------------\n" +
                "|                    |\n" +
                "|xxxxxx              |\n" +
                "|     x              |\n" +
                "|     x              |\n" +
                "----------------------\n", console.draw());
    }

    @Test
    public void shouldDrawPointOnCommand() throws Exception {
        Console console = new Console();
        console.read("C 20 4");

        console.read("L 10 2 10 2");

        assertEquals("----------------------\n" +
                "|                    |\n" +
                "|         x          |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n", console.draw());
    }

    @Test
    public void shouldDrawRectangleOnCommand() throws Exception {
        Console console = new Console();
        console.read("C 20 4");

        console.read("L 1 2 6 2");
        console.read("L 6 3 6 4");

        console.read("R 14 1 18 3");

        assertEquals("----------------------\n" +
                "|             xxxxx  |\n" +
                "|xxxxxx       x   x  |\n" +
                "|     x       xxxxx  |\n" +
                "|     x              |\n" +
                "----------------------\n", console.draw());
    }

    @Test
    public void shouldFillAreaWithColour() throws Exception {
        Console console = new Console();
        console.read("C 20 4");

        console.read("L 1 2 6 2");
        console.read("L 6 3 6 4");

        console.read("R 14 1 18 3");

        console.read("B 10 3 o");

        assertEquals("----------------------\n" +
                "|oooooooooooooxxxxxoo|\n" +
                "|xxxxxxooooooox   xoo|\n" +
                "|     xoooooooxxxxxoo|\n" +
                "|     xoooooooooooooo|\n" +
                "----------------------\n", console.draw());
    }

    @Test
    public void shouldFillEnclosedAreaOnCommand() throws Exception {
        Console console = new Console();
        console.read("C 20 4");

        console.read("L 1 2 6 2");
        console.read("L 6 3 6 4");

        console.read("R 14 1 18 3");

        console.read("B 15 2 o");

        assertEquals("----------------------\n" +
                "|             xxxxx  |\n" +
                "|xxxxxx       xooox  |\n" +
                "|     x       xxxxx  |\n" +
                "|     x              |\n" +
                "----------------------\n", console.draw());
    }

    @Test
    public void shouldFillEnclosedAreaWithBoundaryOnCommand() throws Exception {
        Console console = new Console();
        console.read("C 20 4");

        console.read("L 1 2 6 2");
        console.read("L 6 3 6 4");

        console.read("R 14 1 18 3");

        console.read("B 2 3 a");

        assertEquals("----------------------\n" +
                "|             xxxxx  |\n" +
                "|xxxxxx       x   x  |\n" +
                "|aaaaax       xxxxx  |\n" +
                "|aaaaax              |\n" +
                "----------------------\n", console.draw());
    }

    @Test
    public void shouldFillAreaNotEncolsedOnCommand() throws Exception {
        Console console = new Console();
        console.read("C 20 4");

        console.read("L 1 2 6 2");
        console.read("L 6 3 6 3");

        console.read("R 14 1 18 3");

        console.read("B 2 3 a");

        assertEquals("----------------------\n" +
                "|aaaaaaaaaaaaaxxxxxaa|\n" +
                "|xxxxxxaaaaaaax   xaa|\n" +
                "|aaaaaxaaaaaaaxxxxxaa|\n" +
                "|aaaaaaaaaaaaaaaaaaaa|\n" +
                "----------------------\n", console.draw());
    }

    @Test
    public void shouldThrowExceptionForOutOfRangeCommand() throws Exception {
        Console console = new Console();
        console.read("C 20 4");

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Specified coordinates out of an active canvas range!");
        console.read("L 1 2 21 2");
    }

    @Test
    public void shouldthrowExceptionForUnknownCommand() throws Exception {
        Console console = new Console();
        String command = "ABC";

        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("This Operation is not supported: " + command);
        console.read(command);
    }

    @Test
    public void shouldThrowExceptionIfReservedColourIsUsed() throws Exception {
        Console console = new Console();
        console.read("C 20 4");

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("x is not a valid colour");
        console.read("B 2 3 x");
    }
}