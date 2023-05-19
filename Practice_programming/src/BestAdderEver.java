//    Write a program that stores three different numbers in different variables.
//    Add those variables together and store that in a new variable.
//    Display the result as a single math statement, like 1 + 2 + 3 = 6.
public class BestAdderEver {
    public static void main (String[] args) {
        int num1=4, num2=12, num3=7;
        int sum=0;

        // Add the variables and store the results in sum
        sum = num1 + num2 + num3;

        // Display the results
        System.out.println(num1 + "+" + num2 + "+" + num3 + "=" + sum);
    }
}