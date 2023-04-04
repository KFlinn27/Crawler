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


        menu.welcomeMsg();
        player = new Player(menu.getPlayer());
        board = generateBoard();
        if (board != null) {
            playGame();
        }
    }

    private void playGame() {
        printBoard();
        while (!board.boardCompleted()) {
            int movesRemaining = player.getSpeed();
            while (movesRemaining > 0) {
                interactWithPosition();
                String movement = menu.getMovement();
                if (movement.equalsIgnoreCase("done")) break;
                /*TODO if a player consume a speed boon they need the additional speed added to the move pool*/
                int moves = menu.getMoves(movesRemaining);
                board.movePlayer(movement, moves);
                movesRemaining -= moves;
                if (moves > 0) {
                    menu.clrscr();
                    printBoard();
                }
                if (board.boardCompleted()) {
                    break;
                }
            }
            board.moveEnemy();
            menu.clrscr();
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

    private Board generateBoard() {
        int boardSize = -1;
        while (boardSize < 0 || boardSize > 2) {
            boardSize = menu.promptForInt("Please choose your board size!" +
                    "\n[0] Exit Program" +
                    "\n[1] Medium Board" +
                    "\n[2] Large Board");
            if (boardSize < 0 || boardSize > 2) {
                System.out.println("You did not select a valid option.");
            }
            if (boardSize == 0) {
                return null;
            }
        }
        int difficulty = -1;
        while (difficulty < 0 || difficulty > 3) {
            difficulty = menu.promptForInt("Please choose your difficulty!" +
                    "\n[0] Exit Program" +
                    "\n[1] Easy" +
                    "\n[2] Medium" +
                    "\n[3] Hard");
            if (difficulty < 0 || difficulty > 3) {
                System.out.println("You did not select a valid option.");
            }
            if (difficulty == 0) {
                return null;
            }
        }
        int height = boardSize == 1 ? 10 : 20;
        int width = height;
        Board toGenerate = new Board(height, width, player, height > 10 ? difficulty * 2 + 1 : difficulty + 1);
        return toGenerate;
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
