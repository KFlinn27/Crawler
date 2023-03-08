public class Player {
    private final int DEFAULT_SPEED = 1;

    private String name;
    private int speed;

    public Player(String name){
        this.name = name;
        this.speed = DEFAULT_SPEED;
    }

    public String getName() {
        return this.name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int i) {
        this.speed = i;
    }
}
