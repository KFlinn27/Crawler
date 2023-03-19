import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private String[][] field;
    private Player player;
    private Coordinate playerCoordinate;
    private Coordinate completionCoordinate;
    private Map<Coordinate, Boon> boons;

    public Board(int height, int width, Player player){
        //Creates rectangular board where top left is position [0][0] and bottom right is [height][width]
        field = new String[height][width];

        this.player = player;
        //Puts player in bottom right corner of map generated.
        playerCoordinate = new Coordinate(width - 2, height - 2);


        //Puts completion mark in top left of map.
        completionCoordinate = new Coordinate(1, 1);

        boons = new HashMap<>();
        boons.put(new Coordinate(3,3), new Boon("speed"));
    }

    public void keyCheck(){
        Coordinate toCheck = new Coordinate(3, 3);
        boolean debug = boons.get(toCheck) != null;
        boolean contains = boons.containsKey(toCheck);
        for(Map.Entry<Coordinate, Boon> current : boons.entrySet()){
            boolean foreached = toCheck == current.getKey();
            boolean forhed = toCheck.equals(current.getKey());
            int placeholder = 5;
        }
    }

    ///TODO assign each array value that way printing can be easier and make levels

    public String toString (){
        String board = "";
        String horizontalLine = horizontalBreak(field[0].length + field[0].length/2);
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if(y % 2 == 0) {
                    board = board.concat(horizontalLine);
                    x = field[y].length;
                } else if (y == completionCoordinate.getyPosition() && x == completionCoordinate.getxPosition()) {
                    field[y][x] = "xx";
                    board = board.concat("XX");
                } else if (y == playerCoordinate.getyPosition() && x == playerCoordinate.getxPosition()) {
                    board = board.concat(player.getName());
                    field[y][x] = player.getName();
                } else if(x % 2 == 0){
                    board = board.concat("|");
                }
                else {
                    Coordinate spot = new Coordinate(x, y);
                    boolean hasBoon = boons.containsKey(spot);
                    if(boons.containsKey(spot)){
                        field[y][x] = "SP";
                        board = board.concat("SP");
                    } else {
                        field[y][x] = "  ";
                        board = board.concat("  ");
                    }
                }
            }
            board = board.concat("\n");
        }
//        for(Map.Entry<Coordinate, Boon> current: boons.entrySet()){
//            if(current.getValue().getName().equalsIgnoreCase("speed") && current.getKey() != playerCoordinate){
//                field[current.getKey().getyPosition()][current.getKey().getxPosition()] = "XX";
//            }
//        }
        return board;
    }

    public boolean boardCompleted(){
        return completionCoordinate.equals(playerCoordinate);
    }

    private String horizontalBreak(int width){
        String horizon = "";
        while(width > 0){
            horizon = horizon.concat("-");
            width--;
        }
        return horizon;
    }

    //TODO use boolean to notify player of invalid movement
    public boolean movePlayer(String movement, int speed){
        if(movement.equalsIgnoreCase("w")){
            return movePlayerUp(speed);
        } else if (movement.equalsIgnoreCase("s")) {
            return movePlayerDown(speed);
        } else if(movement.equalsIgnoreCase("a")){
            return movePlayerLeft(speed);
        } else{
            return movePlayerRight(speed);
        }
    }

    //TODO build helper methods in player to reduce coordinate by speed to simplify these problems
    public boolean movePlayerUp(int speed) {
        int possibleMoves = playerCoordinate.getyPosition() / 2;
        if(possibleMoves > 0){
            playerCoordinate.reduceYPosition(((possibleMoves >= speed) ?  speed*2 : possibleMoves*2));
            return true;
        }
        return false;
    }

    public boolean movePlayerDown(int speed) {
        int possibleMoves = (field.length - playerCoordinate.getyPosition()) / 2;
        if(possibleMoves > 0){
            playerCoordinate.increaseYPosition((possibleMoves >= speed) ?  speed*2 : possibleMoves*2);
            return true;
        }
        return false;
    }

    public boolean movePlayerLeft(int speed) {
        int possibleMoves = playerCoordinate.getxPosition() / 2;
        if(possibleMoves > 0){
            playerCoordinate.reduceXPosition((possibleMoves >= speed) ?  speed*2 : possibleMoves*2);
            return true;
        }
        return false;
    }

    public boolean movePlayerRight(int speed) {
        int possibleMoves = (field[0].length - playerCoordinate.getxPosition()) / 2;
        if(possibleMoves > 0){
            playerCoordinate.increaseXPosition((possibleMoves >= speed) ?  speed*2 : possibleMoves*2);
            return true;
        }
        return false;
    }

    public boolean playerOnBoon(){
        if(boons.containsKey(playerCoordinate)){
            return true;
        }
        return false;
    }

    public String[][] getField() {
        return field;
    }

    public Coordinate getPlayerCoordinate() {
        return playerCoordinate;
    }

    public Boon getBoon(){
        return boons.get(playerCoordinate);
    }

    public void removeBoon() {
        boons.remove(playerCoordinate);
    }
}
