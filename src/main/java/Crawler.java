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
        board = new Board(height*2+1, width*2+1, player);
        printBoard();
        while(true) {
            String movement = menu.getMovement();
            if(movement.equalsIgnoreCase("done")) break;
            board.movePlayer(movement);
            printBoard();
        }
    }


    private void printBoard(){
        menu.printBoard(board.toString());
    }
}
