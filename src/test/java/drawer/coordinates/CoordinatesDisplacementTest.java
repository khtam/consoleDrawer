package drawer.coordinates;

import drawer.coordinates.Coordinates;
import drawer.coordinates.CoordinatesDisplacement;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinatesDisplacementTest {
    @Test
    public void shouldReturnTrueForHorizontalDisplacementOnly() throws Exception {
        Coordinates startingCoordinates = new Coordinates(10, 12);
        Coordinates endingCoordinates = new Coordinates(2, 12);

        CoordinatesDisplacement coordinatesDisplacement = startingCoordinates.findDisplacementTo(endingCoordinates);

        assertEquals(true, coordinatesDisplacement.isHorizontalOnly());
    }

    @Test
    public void shouldReturnTrueForVerticalDisplacementOnly() throws Exception {
        Coordinates startingCoordinates = new Coordinates(10, 12);
        Coordinates endingCoordinates = new Coordinates(10, 20);

        CoordinatesDisplacement coordinatesDisplacement = startingCoordinates.findDisplacementTo(endingCoordinates);

        assertEquals(true, coordinatesDisplacement.isVerticalOnly());
    }
}