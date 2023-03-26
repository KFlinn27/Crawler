public class Enemy {

    private String name;
    private int speed;
    private Coordinate position;

    public Enemy(String name, Coordinate position){
        this.name = name;
        this.speed = 1;
        this.position = position;
    }


    public Coordinate getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
