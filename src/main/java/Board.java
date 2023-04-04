import java.util.*;

public class Board {

    private String[][] field;
    private Player player;
    private Coordinate playerCoordinate;
    private Coordinate completionCoordinate;
    private Map<Coordinate, Boon> boons;
    private List<Enemy> enemies;

    public Board(int height, int width, Player player, int enemiesToSpawn) {
        //Creates rectangular board where top left is position [0][0] and bottom right is [height][width]
        height = height * 2 + 1;
        width = width * 2 + 1;
        field = new String[height][width];

        this.player = player;
        //Puts player in bottom right corner of map generated.
        playerCoordinate = new Coordinate(field.length - 2, field[0].length - 2);

        enemies = new ArrayList<>();
        for(int i = 1; i<enemiesToSpawn+1;i++){
            Enemy enemy = new Enemy("E" + i, generateEnemyStart(height, width));
            enemies.add(enemy);
        }

        //Puts completion mark in top left of map.
        completionCoordinate = new Coordinate(1, 1);

        boons = new HashMap<>();
        boons.put((generateEnemyStart(height, width)), new Boon("speed"));

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
                } else if (x % 2 == 0) {
                    board = board.concat("|");
                } else {
                    Coordinate spot = new Coordinate(x, y);
                    if (boons.containsKey(spot)) {
                        field[y][x] = "SP";
                        board = board.concat("SP");
                    } else if(enemiesHasCoordinate(spot)){
                        field[y][x] = nameOfEnemyAtCoord(spot);
                        board = board.concat(nameOfEnemyAtCoord(spot));
                    }else {
                        field[y][x] = "  ";
                        board = board.concat("  ");
                    }
                }
            }
            board = board.concat("\n");
        }
        return board;
    }

    /*Randomize enemy start currently hard coded for a 10x10 board. Places enemies towards middle rather than crowding
    player.*/

    public Coordinate generateEnemyStart(int height, int width){
        int lowerBoundX = height - (height - 6);
        int upperBoundX = height - 6;
        int lowerBoundY = width - (width - 6);
        int upperBoundY = width - 6;

        int enemyXCoordinate = (int) Math.floor(Math.random() * (upperBoundX - lowerBoundX + 1) + lowerBoundX);
        int enemyYCoordinate = (int) Math.floor(Math.random() * (upperBoundY - lowerBoundY + 1) + lowerBoundY);
        if(enemyYCoordinate % 2 == 0){
            enemyYCoordinate++;
        }
        if(enemyXCoordinate % 2 == 0){
            enemyXCoordinate++;
        }
        Coordinate generated = new Coordinate(enemyXCoordinate, enemyYCoordinate);
        if(enemiesHasCoordinate(generated)){
            return generateEnemyStart(height, width);
        }
        return generated;
    }

    /*Checks player position versus position of enemies or the completion marker.*/

    public boolean boardCompleted() {
        return completionCoordinate.equals(playerCoordinate) || enemiesHasCoordinate(playerCoordinate);
    }

    /*Looks through enemy list to see if an enemy is on a coordinate.*/

    public boolean enemiesHasCoordinate(Coordinate toCheck){
        for(Enemy enemy : enemies){
            if(enemy.getPosition().equals(toCheck)){
                return true;
            }
        }
        return false;
    }

    public String nameOfEnemyAtCoord(Coordinate toCheck){
        for(Enemy enemy : enemies){
            if(enemy.getPosition().equals(toCheck)){
                return enemy.getName();
            }
        }
        return "  ";
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


    /*
    Move enemies 1 by 1, determining movement by distance of the player from the target related to the enemies distance.
    If player is further, try to increase x and y axis to approach player, if closer reduce x and y axis.
     */
    public void moveEnemy(){
        //TODO, low priority, possible to sort list by distance from player? would allow closest enemy to move first and not
        // be blocked by a lower index going first
        for(Enemy enemy: enemies){
            int xPos = enemy.getPosition().getxPosition();
            int yPos = enemy.getPosition().getyPosition();
            if (playerCoordinate.furtherFromTarget(enemy.getPosition())) {
                //TODO, Low priority, Improve this algorithm, recursive? Conditionals seem redundant and can be improved
                //TODO, Med priority, helper method for determining x or y axis distance being greater, not very readable at moment
                if (playerCoordinate.getyPosition() - yPos > playerCoordinate.getxPosition() - xPos && coordinateClear(xPos, yPos + 2)) {
                    enemy.getPosition().increaseYPosition(enemy.getSpeed() * 2);
                } else if(coordinateClear(xPos + 2, yPos)){
                    enemy.getPosition().increaseXPosition(enemy.getSpeed() * 2);
                } else if(coordinateClear(xPos, yPos +2)){
                    enemy.getPosition().increaseYPosition(enemy.getSpeed() * 2);
                }
            } else {
                if (yPos - playerCoordinate.getyPosition() > xPos - playerCoordinate.getxPosition() && coordinateClear(xPos, yPos - 2)) {
                    enemy.getPosition().reduceYPosition(enemy.getSpeed() * 2);
                } else if(coordinateClear(xPos - 2,  yPos)){
                    enemy.getPosition().reduceXPosition(enemy.getSpeed() * 2);
                } else if(coordinateClear(xPos, yPos -2)){
                    enemy.getPosition().reduceYPosition(enemy.getSpeed() * 2);
                }
            }
        }
    }

    public boolean coordinateClear(int x, int y){
        Coordinate toCheck = new Coordinate(x, y);
        return !boons.containsKey(toCheck) && !enemiesHasCoordinate(toCheck);
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
