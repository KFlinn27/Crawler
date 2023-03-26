import java.util.Objects;

public class Crawler {
    private final Menu menu;
    private Board board;
    private Player player;
    private Enemy enemy;


    public static void main(String[] args) {


        Crawler program = new Crawler();

        program.run();

    }

    private Crawler() {
        menu = new Menu();
    }

    private void run() {

        int height = menu.getHeight();
        int width = menu.getWidth();
        player = new Player("KF");
        player.setSpeed(2);
        board = new Board(height * 2 + 1, width * 2 + 1, player);
        printBoard();
        while (!board.boardCompleted()) {
            int movesRemaining = player.getSpeed();
            while (movesRemaining > 0) {
                interactWithPosition();
                String movement = menu.getMovement();
                if (movement.equalsIgnoreCase("done")) break;
                int moves = menu.getMoves(player.getSpeed());
                board.movePlayer(movement, moves);
                movesRemaining -= moves;
                printBoard();
                if(board.boardCompleted()){
                    break;
                }
            }
            board.moveEnemy();
            printBoard();
        }
        gameWon(board.gameResult());
    }

    private void interactWithPosition() {
        if (board.playerOnBoon()) {
            Boon boon = board.getBoon();
            if (menu.consumeBoon(boon)) {
                if (boon.getName().equals("speed")) {
                    player.increaseSpeed();
                    board.removeBoon();
                }
            }
        }
    }

    private void gameWon(boolean gameResult) {
        if (gameResult) {
            menu.userWonMessage();
        } else {
            menu.userLostMessage();
        }
    }


    private void printBoard() {
        menu.printBoard(board.toString());
    }
}
