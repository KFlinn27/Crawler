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
}
