public class Player {
    private final int DEFAULT_SPEED = 2;

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

    public void consumeBoon(Boon boon){
        if(boon.getName().equalsIgnoreCase("speed")){
            speed++;
        }
    }

    public void increaseSpeed(){
        this.speed++;
    }
}
