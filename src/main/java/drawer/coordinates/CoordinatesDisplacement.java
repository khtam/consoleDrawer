package drawer.coordinates;

public class CoordinatesDisplacement {
    private int x;
    private int y;

    public CoordinatesDisplacement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Boolean isHorizontalOnly() {
        return y == 0;
    }

    public Boolean isVerticalOnly() {
        return x == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoordinatesDisplacement that = (CoordinatesDisplacement) o;

        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
