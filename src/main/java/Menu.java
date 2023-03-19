import java.util.Scanner;

public class Menu {
    Scanner userInput = new Scanner(System.in);

    public int getHeight() {
        System.out.println("How tall should the board be?");
        return Integer.parseInt(userInput.nextLine());
    }
    public int getWidth() {
        System.out.println("How wide should the board be?");
        return Integer.parseInt(userInput.nextLine());
    }

    public void printBoard(String toString) {
        System.out.println(toString);
    }

    public String userFinished() {
        System.out.println("Are you finished?(y/n)");
        return userInput.nextLine();
    }

    public String getMovement() {
        System.out.println("Enter your movement, W=UP, S=DOWN, A=LEFT, D=RIGHT, done=exit:");
        return userInput.nextLine();
    }

    public void userWonMessage() {
        System.out.println("Congrats, you've navigated the board successfully!");
    }

    public boolean consumeBoon(Boon boon) {
        System.out.println("You've landed on a " + boon.getName() + " boon! Would you like to consume(Y/N)?");
        return userInput.nextLine().equalsIgnoreCase("y");
    }

    public int getMoves(int speed) {
        System.out.println("How many spaces would you like to move? Enter a number between 1 and " + speed + ":");
        int moves = Integer.parseInt(userInput.nextLine());
        return moves;
    }
}
