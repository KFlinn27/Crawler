public class Board {

    private String[][] field;
    private Player player;
    private int[] playerPosition;

    public Board(int height, int width, Player player){
        field = new String[height][width];
        this.player = player;
        playerPosition = new int[]{height - 2, width - 2};
    }

    ///TODO assign each array value that way printing can be easier and make levels
    public String toString (){
        String board = "";
        String horizontalLine = horizontalBreak(field[0].length + field[0].length/2);
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if(i % 2 == 0) {
                    board = board.concat(horizontalLine);
                    j = field[i].length;
                } else if (i == playerPosition[0] && j == playerPosition[1]) {
                    board = board.concat(player.getName());
                } else if(j % 2 == 0){
                    board = board.concat("|");
                }
                else {
                    board = board.concat("  ");
                }
            }
            board = board.concat("\n");
        }
        return board;
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
    public boolean movePlayer(String movement){
        if(movement.equalsIgnoreCase("w")){
            return movePlayerUp();
        } else if (movement.equalsIgnoreCase("s")) {
            return movePlayerDown();
        } else if(movement.equalsIgnoreCase("a")){
            return movePlayerLeft();
        } else{
            return movePlayerRight();
        }
    }

    public boolean movePlayerUp() {
        int newPos = playerPosition[0] - 2;
        if(newPos > 0){
            playerPosition[0] = newPos;
            return true;
        }
        return false;
    }

    public boolean movePlayerDown() {
        int newPos = playerPosition[0] + 2;
        if(newPos < field.length){
            playerPosition[0] = newPos;
            return true;
        }
        return false;
    }

    public boolean movePlayerLeft() {
        int newPos = playerPosition[1] - 2;
        if(newPos > 0 ){
            playerPosition[1] = newPos;
            return true;
        }
        return false;
    }

    public boolean movePlayerRight() {
        int newPos = playerPosition[1] + 2;
        if(newPos < field[0].length){
            playerPosition[1] = newPos;
            return true;
        }
        return false;
    }
}
