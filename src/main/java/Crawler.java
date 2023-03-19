import java.util.Objects;

public class Crawler {
    private final Menu menu;
    private Board board;
    private Player player;


    public static void main(String[] args) {


        Crawler program = new Crawler();

        program.run();

    }

    private Crawler(){
        menu = new Menu();
    }

    private void run(){

        int height = menu.getHeight();
        int width = menu.getWidth();
        player = new Player("KF");
        player.setSpeed(2);
        board = new Board(height*2+1, width*2+1, player);
        printBoard();
        board.keyCheck();
        while(!board.boardCompleted()) {
            interactWithPosition();
            String movement = menu.getMovement();
            if(movement.equalsIgnoreCase("done")) break;
            int moves = menu.getMoves(player.getSpeed());
            board.movePlayer(movement, moves);

            printBoard();
        }
        gameWon(board.boardCompleted());
    }

    private void interactWithPosition() {
        if(board.playerOnBoon()){
            Boon boon = board.getBoon();
            if(menu.consumeBoon(boon)){
                if(boon.getName().equals("speed")){
                    player.increaseSpeed();
                    board.removeBoon();
                }
            }
        }
    }

    private void gameWon(boolean boardCompleted) {
        menu.userWonMessage();
    }


    private void printBoard(){
        menu.printBoard(board.toString());
    }
}
