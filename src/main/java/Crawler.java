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
        Player player = new Player("KF");
        player.setSpeed(2);
        board = new Board(height*2+1, width*2+1, player);
        printBoard();
        while(!board.boardCompleted()) {
            String movement = menu.getMovement();
            if(movement.equalsIgnoreCase("done")) break;
            board.movePlayer(movement, player.getSpeed());
            printBoard();
        }
        gameWon(board.boardCompleted());
    }

    private void gameWon(boolean boardCompleted) {
        menu.userWonMessage();
    }


    private void printBoard(){
        menu.printBoard(board.toString());
    }
}
