public class Coordinate {
    private int xPosition;
    private int yPosition;

    public Coordinate(int xPosition, int yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getxPosition() {
        return Math.max(xPosition, 1);
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return Math.max(yPosition, 1);
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public boolean furtherFromTarget(Coordinate toCompare){
        int xDiff = this.xPosition - toCompare.xPosition;
        int yDiff = this.yPosition - toCompare.yPosition;
        return (xDiff + yDiff) > 0;
    }

    @Override
    public boolean equals(Object o){
        if(o == this) {
            return true;
        }
        else if (!(o instanceof Coordinate)){
            return false;
        }
        Coordinate toCheck = (Coordinate) o;
        return toCheck.getyPosition() == this.yPosition && toCheck.getxPosition() == this.xPosition;
    }

    @Override
    public int hashCode()
    {
        int result = 17;
        result = 31 * result + xPosition;
        result = 31 * result + yPosition;
        return result;
    }

    public void reduceYPosition(int i) {

        yPosition -= i;
    }

    public void increaseYPosition(int i) {
        yPosition += i;
    }

    public void reduceXPosition(int i) {
        xPosition -= i;
    }

    public void increaseXPosition(int i) {
        xPosition += i;
    }
}
