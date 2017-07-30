package drawer.coordinates;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class CoordinatesTest {
    @Test
    public void shouldReturnVerticalPositiveDisplacement() throws Exception {
        Coordinates startingCoordinates = new Coordinates(1, 5);
        Coordinates endingCoordinates = new Coordinates(5, 5);
        CoordinatesDisplacement expectedDisplacement = new CoordinatesDisplacement(4,0);

        assertEquals(expectedDisplacement, startingCoordinates.findDisplacementTo(endingCoordinates));
    }

    @Test
    public void shouldReturnVerticalNegativeDisplacement() throws Exception {
        Coordinates startingCoordinates = new Coordinates(10, 5);
        Coordinates endingCoordinates = new Coordinates(5, 5);
        CoordinatesDisplacement expectedDisplacement = new CoordinatesDisplacement(-5,0);

        assertEquals(expectedDisplacement, startingCoordinates.findDisplacementTo(endingCoordinates));
    }

    @Test
    public void shouldReturnHorizontalPositiveDisplacement() throws Exception {
        Coordinates startingCoordinates = new Coordinates(10, 3);
        Coordinates endingCoordinates = new Coordinates(10, 12);
        CoordinatesDisplacement expectedDisplacement = new CoordinatesDisplacement(0,9);

        assertEquals(expectedDisplacement, startingCoordinates.findDisplacementTo(endingCoordinates));
    }

    @Test
    public void shouldReturnHorizontalNegativeDisplacement() throws Exception {
        Coordinates startingCoordinates = new Coordinates(10, 21);
        Coordinates endingCoordinates = new Coordinates(10, 4);
        CoordinatesDisplacement expectedDisplacement = new CoordinatesDisplacement(0,-17);

        assertEquals(expectedDisplacement, startingCoordinates.findDisplacementTo(endingCoordinates));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionForUnsupportedDisplacement() throws Exception {
        Coordinates startingCoordinates = new Coordinates(10, 21);
        Coordinates endingCoordinates = new Coordinates(2, 4);

        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("slants are not supported...yet");

        startingCoordinates.findDisplacementTo(endingCoordinates);
    }
}