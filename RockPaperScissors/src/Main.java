import java.util.Random;
import java.util.Scanner;

/**
 * Each player chooses Rock, Paper, or Scissors.
 * If both players choose the same thing, the round is a tie.
 * Otherwise:
 * Paper wraps Rock to win
 * Scissors cut Paper to win
 * Rock breaks Scissors to win
 */

public class Main {

    public static final String[] options = {"Rock", "Paper", "Scissors"};

    public static final String ROCK = "Rock";
    public static final String PAPER = "Paper";
    public static final String SCISSORS = "Scissors";


    public static int main(String[] args) {
        String[] names = {"user", "computer"};
        final int MAX_ROUNDS = 10;
        int count=0, pcWin=0, userWin=0, tie=0;

        System.out.println("How many rounds do you want to play?");
        Scanner mySc = new Scanner(System.in);
        int numRounds = mySc.nextInt();
        if (numRounds < 1 || numRounds > 10) {
            System.out.println("Invalid input. Number is not in the range!");
            return -1;
        }

        do {
            String userSelect = userChoice();
            String pcSelect = computerChoice();

            System.out.println("user's choice=" + userSelect + "\ncomputer's choice=" + pcSelect);

            int gameResult = startGame(userSelect, pcSelect);
            switch (gameResult) {
                case 0:
                    userWin++;
                    break;
                case 1:
                    pcWin++;
                    break;
                case 2:
                    tie++;
                    gameResult = chooseWinner();
                    break;
                default:
                    System.out.println("Invalid value!");
            }
            System.out.println("The winner is the " + names[gameResult]);
            count++;
        }
        while (count < MAX_ROUNDS && count < numRounds);

        System.out.println("In total you played " + count + " rounds.");
        System.out.println("The user won " + userWin + " times");
        System.out.println("The pc won " + pcWin + " times");
        System.out.println("And there were " + tie + " ties!");

        return 1;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public static String userChoice () {
        System.out.println("Please choose one of the options: Rock, Paper, or Scissors");
        Scanner mySc = new Scanner(System.in);
        String str = mySc.next();
        if (isNumeric(str))
            return options[Integer.parseInt(str)];
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String computerChoice () {
        System.out.println("Computer is choosing one of the options: Rock, Paper, or Scissors");
        Random myRnd = new Random();
        int pcChoice = myRnd.nextInt(3);
        return options[pcChoice];
    }

    public static int chooseWinner () {
        Random myRnd = new Random();
        return myRnd.nextInt(2);
    }
    public static int startGame (String userChoice, String pcChoice) {
        int userWin = 0, pcWin = 1, tie = 2;

        if (userChoice.equals(pcChoice)) {
            System.out.println("Both chose the same thing. The round is a tie!");
            return tie;
        }
        else if (userChoice.equals(ROCK)) {
            return pcChoice.equals(PAPER) ? pcWin : userWin;
        }
        else if (userChoice.equals(PAPER)) {
            return pcChoice.equals(SCISSORS) ? pcWin : userWin;
        }
        else if (userChoice.equals(SCISSORS)) {
            return pcChoice.equals(ROCK) ? pcWin : userWin;
        }
        return -1;
    }
}