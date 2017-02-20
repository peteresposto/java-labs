package ca.uwo.eng.se2205b;

/**
 * Problem #1: FizzBuzz
 */
public class FizzBuzz {

    /**
     * Driver method for FizzBuzz
     * @param args
     */
    public static void main(String[] args) {
        //Loop from 1-100
        for (int i = 1; i <= 100; i++)
        {
            //Each time, start by printing the current index
            System.out.printf(i + " ");
            //If the index is divisible by 5*3, output "FizzBuzz"
            if ( i % (5*3) == 0) {
                System.out.println("FizzBuzz");
            }
            //If it's only divisible by 5, output "Buzz"
            else if ( i % 5 == 0) {
                System.out.println("Buzz");
            }
            //If it's only divisible by 3, output "Fizz"
            else if (i % 3 == 0) {
                System.out.println("Fizz");
            }
            //If it meets neither of these conditions, skip to the next line
            else
                System.out.println(" ");
        }
    }
}
