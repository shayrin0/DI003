import java.util.Random;
import java.util.Scanner;
public class LuckySevens2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random randomDice = new Random();

        //Here we want to keep track of each dice roll
        int dice1;
        int dice2;
        int userMoney;
        int maxMoney;

        // Request the input
        System.out.println("How much money you wanna bet kid?");
        userMoney = input.nextInt();
        maxMoney = userMoney; // maximum amount held by the gambler
        int rollsAtMax = 0; // when the maximum is achieved
        int rolls = 0; // number of rolls to reach depletion
        // we use a while loop to keep playing the game until the player runs out of money.
        while (userMoney > 0){

//  we generate two random numbers representing the dice rolls
            dice1 = randomDice.nextInt(6) + 1;
            dice2 = randomDice.nextInt(6) + 1;
            // Here we want to add it then it will adjust the player's userMoney
            int sum = dice1 + dice2;
            rolls++;

            // If the sum of the two dice is equal to 7, the player wins $4
            if(sum == 7){
                userMoney += 4;
            } else {
                userMoney -= 1;
            }

            if (userMoney> maxMoney) {
                maxMoney = userMoney;
                rollsAtMax = rolls;
            }
        }
        System.out.println("You are out of money after " + rolls + " rolls.");
        System.out.println("The maximum amount of money you held was $" + maxMoney);
        System.out.println("It happened after " + rollsAtMax + " rolls.");

    }
}