public class Enemy {

    private String name;
    private int speed;

    public Enemy(String name){
        this.name = name;
        this.speed = 1;
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
