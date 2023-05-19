import java.util.Random;
import java.util.Scanner;

/**
 * This program will be a Java Console Application called LuckySevens.
 * The program first asks the user how many dollars they have to bet.
 * The program then rolls the dice repeatedly until all the money is gone.
 * Hint: Use a loop construct to keep playing until the money is gone.
 * The program keeps track of how many rolls were taken before the money ran out.
 * The program keeps track of the maximum amount of money held by the player.
 * The program keeps track of how many rolls were taken at the point when the user held the most money.
 * Hint: For steps 4, 5, and 6, declare some variables.
 */
public class LuckySevens {
    public static void main(String[] args) {

        Random myRand = new Random();
        Scanner mySc = new Scanner(System.in);
        System.out.println("How many dollars do you have to bet?");

        int moneyTotal = mySc.nextInt();

        int moneyBet = moneyTotal;
        int count = 0, countMax = 0, randNum1, randNum2, moneyMax = moneyTotal;

        // If the sum of the two dice is equal to 7, the player wins $4; otherwise, the player loses $1.
        do {
            randNum1 = myRand.nextInt(6)+1;
            randNum2 = myRand.nextInt(6)+1;

            if (randNum1 + randNum2 == 7) {
                moneyBet += 4;
            }
            else {
                moneyBet -= 1;
            }

            if (moneyBet > moneyMax) {
                countMax = count;
                moneyMax = moneyBet; // The program keeps track of the maximum amount of money held by the player
            }
            count++;
        }
        while(moneyBet > 0);

        System.out.println("The maximum money you had was " + moneyMax + " and in step " + countMax);
        System.out.println("It took " + count + " rolls until you ran out of money!");
    }
}
