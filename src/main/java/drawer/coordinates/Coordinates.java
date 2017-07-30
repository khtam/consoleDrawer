package drawer.coordinates;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public CoordinatesDisplacement findDisplacementTo(Coordinates endingCoordinates) {
        int xDisplacement = endingCoordinates.getX() - this.x;
        int yDisplacement = endingCoordinates.getY() - this.y;

        if (xDisplacement != 0 && yDisplacement != 0)
            throw new UnsupportedOperationException("slants are not supported...yet ");

        return new CoordinatesDisplacement(xDisplacement, yDisplacement);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
