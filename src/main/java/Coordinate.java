public class Coordinate {
    private int xPosition;
    private int yPosition;

    public Coordinate(int xPosition, int yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
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
}
