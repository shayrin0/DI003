import java.util.Scanner;

//Take your code and fix the "Best Adder Ever" from the previous section,
//but instead of using hard-coded values,
//ask the user to input the numbers that they want to add together,
//store the input in variables,
//and display the results.
public class BiggerBetterAdder {
    public static void main (String[] args) {
        int num1, num2, num3;
        int sum=0;

        Scanner myScan = new Scanner(System.in);

        /*
            // method 1
            String stringNum = myScanner.nextLine();
            num1 = Integer.parseInt(stringNum);
        */

        System.out.println("Please enter three numbers!");
        System.out.println("Enter the 1st number:");
        // method 2
        num1 = myScan.nextInt();

        System.out.println("Enter the 2nd number:");
        num2 = myScan.nextInt();

        System.out.println("Enter the 3rd number:");
        num3 = myScan.nextInt();

        // Add the variables and store the results in sum
        sum = num1 + num2 + num3;

        // Display the results
        System.out.println(num1 + "+" + num2 + "+" + num3 + "=" + sum);
    }
}
