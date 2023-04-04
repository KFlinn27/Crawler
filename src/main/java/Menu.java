import java.util.Scanner;

public class Menu {
    Scanner userInput = new Scanner(System.in);

    public void clrscr(){
        for(int i = 0; i < 50; i++){
            System.out.println();
        }
    }

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

    public void userLostMessage() {
        System.out.println("You have lost! Better lucky next time!");
    }

    public void welcomeMsg() {
        System.out.println("Welcome to Crawler! Version 1.0, created by Kyle Flinner.");
        System.out.println("You will control a character marked by your initials you input, you will need" +
                "\nto navigate enemies and reach the square marked \"XX\". If you see a position " +
                "\nmarked \"SP\", this is a speed buff. It will increase your movement by 1 if you consume it. ");
        System.out.println("Good luck!");
    }

    public String getPlayer() {

        System.out.print("Please enter your desired two letter initials(ie KF, IJ):");
        String playerName = userInput.nextLine();
        while(playerName.length() != 2){
            System.out.print("Please enter a two character initial:");
            playerName = userInput.nextLine();
        }
        return playerName;
    }

    public int promptForInt(String msg) {
        System.out.print(msg);
        while(true){
            try{
                System.out.println();
                System.out.print("Enter number here:");
                int x = Integer.parseInt(userInput.nextLine());
                System.out.println();
                return x;
            } catch (NumberFormatException e){
                System.out.println();
                System.err.println("You did not enter a number, try again!");
                System.out.println();
            }
        }
    }
}
