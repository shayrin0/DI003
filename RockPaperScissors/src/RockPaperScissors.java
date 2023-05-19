import java.util.Random;
import java.util.Scanner;

/**
 * Title: Rock, Paper, or Scissors Game
 * Name: Shirin Panahi Moghadam
 * Date: 2023-05-17
 * Instructions:
 *              Each player chooses Rock, Paper, or Scissors.
 *              If both players choose the same thing, the round is a tie.
 *              Otherwise:
 *                  Paper wraps Rock to win
 *                  Scissors cut Paper to win
 *                  Rock breaks Scissors to win
 */

public class RockPaperScissors {

    private static final String[] OPTIONS = {"Rock", "Paper", "Scissors"};
    private static final String ROCK = "Rock", PAPER = "Paper", SCISSORS = "Scissors";
    private static final int USER_WIN = 0, PC_WIN = 1, TIE = 2;

    private static Scanner mySc = new Scanner(System.in);
    private static Random myRnd = new Random();


    public static void main(String[] args) {

        String[] names = {"user", "computer"};
        final int MAX_ROUNDS = 10, MIN_ROUNDS = 1;
        Boolean playAgain = true;
        int numRounds = MIN_ROUNDS;

        while (playAgain) {
            int count = 0, pcWin = 0, userWin = 0, tie = 0;

            /* 1. The program first asks the user how many rounds he/she wants to play
                  Maximum number of rounds = 10, minimum number of rounds = 1.
                  If the user asks for something outside this range, the program prints an error message and quits.
                  If the number of rounds is in range, the program plays that number of rounds. */
            System.out.println("oO0Oo...|...oO0Oo...|...oO0Oo...|...oO0Oo...|...oO0Oo");
            System.out.println("How many rounds do you want to play?");
            String strRounds = mySc.next();
            numRounds = isNumeric(strRounds);
            if (numRounds != -1) {
                if (numRounds < MIN_ROUNDS || numRounds > MAX_ROUNDS) {
                    System.out.println("Invalid input. Number is not in the range!");
                    System.exit(1);
                }
            }
            else {
                System.out.println("Invalid input. The value is not an integer!");
                System.exit(1);
            }

            System.out.println("|-----------------------------------------------------|");
            System.out.println("Let the game begin!");
            do {
                /* 2. For each round of Rock, Paper, Scissors, the program does the following:
                      The computer asks the user for his/her choice (Rock, Paper, or Scissors).
                      After the computer asks for the user’s input, the computer randomly chooses Rock, Paper, or Scissors
                      and displays the result of the round (tie, user win, or computer win). */
                String userSelect = userChoice();
                while (userSelect.equals("NULL"))
                    userSelect = userChoice();
                String pcSelect = computerChoice();

                /* 3. The program must keep track of how many rounds are ties, user wins, or computer wins. */
                count++;
                System.out.println("|-----------------------------------------------------|");

                System.out.println("The results of Round " + count + " are as follows:");
                System.out.println("user's choice=" + userSelect + "\ncomputer's choice=" + pcSelect);

                int gameResult = startGame(userSelect, pcSelect);
                switch (gameResult) {
                    case USER_WIN:
                        userWin++;
                        break;
                    case PC_WIN:
                        pcWin++;
                        break;
                    case TIE:
                        tie++;
                        gameResult = chooseWinner();
                        if (gameResult == USER_WIN)
                            userWin++;
                        else if (gameResult == PC_WIN)
                            pcWin++;
                        break;
                    default:
                        System.out.println("Invalid value!");
                        continue;
                }
                System.out.println("----> The winner of this round is the " + names[gameResult] + "<----");
                System.out.println("|-----------------------------------------------------|");
            }
            while (count < MAX_ROUNDS && count < numRounds);

            /* 4. At the end of the last round, the program must print out the number of ties, user wins, and computer wins
                  and declare the overall winner based on who won more rounds. */
            System.out.println("**^**...&...**^**...&...**^**...&...**^**...&...**^**");
            System.out.println("In total you played " + count + " rounds.");
            System.out.println("The user won " + userWin + " times");
            System.out.println("The pc won " + pcWin + " times");
            System.out.println("And there were " + tie + " ties!");
            System.out.println("**^**...&...**^**...&...**^**...&...**^**...&...**^**");

            if (userIsWinner(userWin, pcWin) == userWin)
                System.out.println("----> Yayyy!!! The user is the final winner! Congrats! <----");
            else if (userIsWinner(userWin, pcWin) == pcWin)
                System.out.println("----> Oops!!! The pc won. Dear user, please don't let this happen again... <----");
            else
                System.out.println("----> Hmmm... The user and pc tied for the first place! <----");

            /* 5. After all rounds have been played and the winner declared, the program must ask the user if he/she wants to play again.
                  If the user says No, the program prints out a message like, “Thanks for playing!” and then exits.
                  If the user says Yes, the program starts over, asking the user how many rounds he/she would like to play. */
            playAgain = playAgainQuestion();
        }

    }

    public static int isNumeric(String strNum) {
        int d;

        if (strNum == null) {
            return -1;
        }
        try {
            d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return -1;
        }
        return d;
    }

    public static String userChoice () {
        System.out.println("Please choose one of the options: 1.Rock, 2.Paper, or 3.Scissors");
        String str = mySc.next();
        if (isNumeric(str) != -1)
        {
            int choice = Integer.parseInt(str);
            switch (choice) {
                case 1:
                case 2:
                case 3:
                    return OPTIONS[choice-1];
                default:
                    System.out.println("Invalid value!");
                    return "NULL";
            }
        }

        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        if (str.equals(ROCK) || str.equals(PAPER) || str.equals(SCISSORS))
            return str;
        else {
            System.out.println("Invalid value!");
            return "NULL";
        }
    }

    public static String computerChoice () {
        System.out.println("Computer is choosing one of the options: 1.Rock, 2.Paper, or 3.Scissors");
        int pcChoice = myRnd.nextInt(3);
        return OPTIONS[pcChoice];
    }

    public static int chooseWinner () {
        return myRnd.nextInt(2);
    }

    public static int startGame (String userChoice, String pcChoice) {

        if (userChoice.equals(pcChoice)) {
            System.out.println("Both chose the same thing. The round is a tie!\nLet's choose the winner by chance.");
            return TIE;
        }
        else if (userChoice.equals(ROCK)) {
            return pcChoice.equals(PAPER) ? PC_WIN : USER_WIN;
        }
        else if (userChoice.equals(PAPER)) {
            return pcChoice.equals(SCISSORS) ? PC_WIN : USER_WIN;
        }
        else if (userChoice.equals(SCISSORS)) {
            return pcChoice.equals(ROCK) ? PC_WIN : USER_WIN;
        }
        return -1;
    }

    public static Boolean playAgainQuestion () {
        System.out.println("Do you want to play again (Yes / No) ?");
        String str = mySc.next();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        return str.equals("Yes");
    }

    public static int userIsWinner (int userWin, int pcWin) {
        if (userWin == pcWin)
            return TIE;
        return Math.max(userWin, pcWin);
    }
}
