import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private String[][] field;
    private Player player;
    private Coordinate playerCoordinate;
    private Coordinate completionCoordinate;
    private Coordinate enemyCoordinate;
    private Map<Coordinate, Boon> boons;
    private Enemy enemy;

    public Board(int height, int width, Player player, Enemy enemy) {
        //Creates rectangular board where top left is position [0][0] and bottom right is [height][width]
        field = new String[height][width];

        this.player = player;
        //Puts player in bottom right corner of map generated.
        playerCoordinate = new Coordinate(width - 2, height - 2);


        this.enemy = enemy;

        enemyCoordinate = generateEnemyStart();

        //Puts completion mark in top left of map.
        completionCoordinate = new Coordinate(1, 1);

        boons = new HashMap<>();
        boons.put(new Coordinate(3, 3), new Boon("speed"));
    }


    public Coordinate getEnemyCoordinate() {
        return enemyCoordinate;
    }

    ///TODO assign each array value that way printing can be easier and make levels

    public String toString() {
        String board = "";
        String horizontalLine = horizontalBreak(field[0].length + field[0].length / 2);
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (y % 2 == 0) {
                    board = board.concat(horizontalLine);
                    x = field[y].length;
                } else if (y == completionCoordinate.getyPosition() && x == completionCoordinate.getxPosition()) {
                    field[y][x] = "xx";
                    board = board.concat("XX");
                } else if (y == playerCoordinate.getyPosition() && x == playerCoordinate.getxPosition()) {
                    board = board.concat(player.getName());
                    field[y][x] = player.getName();
                } else if (y == enemyCoordinate.getyPosition() && x == enemyCoordinate.getxPosition()) {
                    board = board.concat(enemy.getName());
                    field[y][x] = enemy.getName();
                } else if (x % 2 == 0) {
                    board = board.concat("|");
                } else {
                    Coordinate spot = new Coordinate(x, y);
                    boolean hasBoon = boons.containsKey(spot);
                    if (boons.containsKey(spot)) {
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
        return board;
    }

    public Coordinate generateEnemyStart(){
        int lowerBoundX = 3;
        int upperBoundX = 8;
        int lowerBoundY = 3;
        int upperBoundY = 8;

        int enemyXCoordinate = (int) Math.floor(Math.random() * (upperBoundX - lowerBoundX + 1) + lowerBoundX);
        int enemyYCoordinate = (int) Math.floor(Math.random() * (upperBoundY - lowerBoundY + 1) + lowerBoundY);
        if(enemyYCoordinate % 2 == 0){
            enemyYCoordinate++;
        }
        if(enemyXCoordinate % 2 == 0){
            enemyXCoordinate++;
        }
        return new Coordinate(enemyXCoordinate, enemyYCoordinate);
    }

    public boolean boardCompleted() {
        return completionCoordinate.equals(playerCoordinate) || enemyCoordinate.equals(playerCoordinate);
    }

    private String horizontalBreak(int width) {
        String horizon = "";
        while (width > 0) {
            horizon = horizon.concat("-");
            width--;
        }
        return horizon;
    }

    //TODO use boolean to notify player of invalid movement
    public boolean movePlayer(String movement, int speed) {
        if (movement.equalsIgnoreCase("w")) {
            return movePlayerUp(speed);
        } else if (movement.equalsIgnoreCase("s")) {
            return movePlayerDown(speed);
        } else if (movement.equalsIgnoreCase("a")) {
            return movePlayerLeft(speed);
        } else {
            return movePlayerRight(speed);
        }
    }

    public void moveEnemy(){
        if(playerCoordinate.furtherFromTarget(enemyCoordinate)){
            if(playerCoordinate.getyPosition() - enemyCoordinate.getyPosition() > playerCoordinate.getxPosition() - enemyCoordinate.getxPosition()){
                enemyCoordinate.increaseYPosition(enemy.getSpeed()*2);
            }
            else {
                enemyCoordinate.increaseXPosition(enemy.getSpeed()*2);
            }
        } else {
            if(enemyCoordinate.getyPosition() - playerCoordinate.getyPosition() > enemyCoordinate.getxPosition() - playerCoordinate.getxPosition()){
                enemyCoordinate.reduceYPosition(enemy.getSpeed()*2);
            }
            else {
                enemyCoordinate.reduceXPosition(enemy.getSpeed()*2);
            }
        }
    }

    //TODO build helper methods in player to reduce coordinate by speed to simplify these problems
    public boolean movePlayerUp(int speed) {
        int possibleMoves = playerCoordinate.getyPosition() / 2;
        if (possibleMoves > 0) {
            playerCoordinate.reduceYPosition(((possibleMoves >= speed) ? speed * 2 : possibleMoves * 2));
            return true;
        }
        return false;
    }

    public boolean movePlayerDown(int speed) {
        int possibleMoves = (field.length - playerCoordinate.getyPosition()) / 2;
        if (possibleMoves > 0) {
            playerCoordinate.increaseYPosition((possibleMoves >= speed) ? speed * 2 : possibleMoves * 2);
            return true;
        }
        return false;
    }

    public boolean movePlayerLeft(int speed) {
        int possibleMoves = playerCoordinate.getxPosition() / 2;
        if (possibleMoves > 0) {
            playerCoordinate.reduceXPosition((possibleMoves >= speed) ? speed * 2 : possibleMoves * 2);
            return true;
        }
        return false;
    }

    public boolean movePlayerRight(int speed) {
        int possibleMoves = (field[0].length - playerCoordinate.getxPosition()) / 2;
        if (possibleMoves > 0) {
            playerCoordinate.increaseXPosition((possibleMoves >= speed) ? speed * 2 : possibleMoves * 2);
            return true;
        }
        return false;
    }

    public boolean playerOnBoon() {
        if (boons.containsKey(playerCoordinate)) {
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

    public Boon getBoon() {
        return boons.get(playerCoordinate);
    }

    public void removeBoon() {
        boons.remove(playerCoordinate);
    }

    public boolean gameResult() {
        return playerCoordinate.equals(completionCoordinate);
    }
}
