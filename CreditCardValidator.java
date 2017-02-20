package ca.uwo.eng.se2205b;

/**
 * Problem #3: Validates a Credit Card
 */

import java.util.Scanner;
public class CreditCardValidator {

    private enum creditCard
    {
        VISA("Visa"), MASTERCARD("Master Card"), AMERICANEXPRESS("American Express"), DISCOVERY("Discovery");
        private String name;

        private creditCard(String cardName) {
            name = cardName;
        }

        private String getName() {
            if (name != null)
                return name;
            else
                return "Invalid";
        }
    }

    private static class UserInputDriver {
        public static void main (String args[]) {
            while(true) {
                Scanner scan = new Scanner(System.in);
                System.out.printf("Enter a number to validate: ");
                creditCard tempCard = validate(scan.nextLong());
                if (tempCard != null)
                    System.out.println(tempCard);
                else
                    System.out.println("Invalid!");
            }
        }
    }

    private static class RunThroughDriver {
        public static void main(String args[])
        {
            long nums [] = {54321L, 4388576018402626L, 4111111111111111L, 5500000000000004L, 340000000000009L};

            //Loop through the numbers and if
            for (int i = 0; i < 5; i++)
            {
                creditCard tempCard = validate(nums[i]);
                if (!(tempCard == null))
                    System.out.println(tempCard);
                else
                    System.out.println("Invalid!");
            }
        }
    }

    /**
     * Compute if the number is a valid Credit Card Number.
     * @param number Credit Card number to validate.
     * @return Non-{@code null} enum of the type of credit card.
     */

    private static creditCard validate(long number) {
        //long cardNumber = number;

        //Create a string from the card number
        String cardNumberString = Long.toString(number);

        //The following code turns the string into an int array
        int [] cardNumberArray = new int[cardNumberString.length()];
        //Iterate for every digit of the number
        for (int i = 0; i < cardNumberString.length(); i++) {
            //Turn the cha
            cardNumberArray[i] = cardNumberString.charAt(i) - '0';
        }

        //Make a copy of the first two digits for use later
        long [] cardNumberArrayVerif = new long[cardNumberString.length()];
        for (int i = 0; i < 2; i++){
            cardNumberArrayVerif[i] = cardNumberArray[i];
        }

        long sum = doubleAndSumNumbers(cardNumberArray, cardNumberString.length());

        if ((sum % 10 == 0) && (cardNumberArrayVerif[0] == 4))
            return creditCard.VISA;
        else if ((sum % 10 == 0) && (cardNumberArrayVerif[0] == 5))
            return creditCard.MASTERCARD;
        else if ((sum % 10 == 0) && (cardNumberArrayVerif[0] == 3) && (cardNumberArrayVerif[1] == 4))
            return creditCard.AMERICANEXPRESS;
        else if ((sum % 10 == 0) && (cardNumberArrayVerif[0] == 6))
            return creditCard.DISCOVERY;
        else
            return null;
    }

    /**
     *
     * @param cardNumberArray The array of credit card digits
     * @param length The length of the array of credit card digits
     * @return The sum of all of the digits in the array of credit card digits after they have been modified
     */
    private static long doubleAndSumNumbers(int cardNumberArray[], int length)
    {
        long sum = 0;
        boolean evenOrOdd = false;

        //Start at the rightmost digit of the card number array
        for (int i = length - 1; i >= 0; i--) {
            if (evenOrOdd) {
                //If the digit in an even location, considering the rightmost digit of the array index '1', double the digit
                cardNumberArray[i] = (cardNumberArray[i] * 2);
                //If the doubled number is now a double digit number, add the digits
                if (cardNumberArray[i] > 9) {
                    int num2 = cardNumberArray[i] % 10;
                    cardNumberArray[i] = 1 + num2;
                }
            }
            //Change the state of even or odd to the opposite of what it currently is
            evenOrOdd = !evenOrOdd;
        }
        return sumOddDigits(cardNumberArray, length) + sumEvenDigits(cardNumberArray, length);
    }

    /**
     *
     * @param array The array of credit card digits
     * @param length The length of the array of credit card digits
     * @return The sum of all the digits in 'odd' places in the array, starting at the rightmost digit
     */
    private static int sumOddDigits(int array[], int length)
    {
        int sum = 0;
        for (int i = length - 1; i >= 0; i = i - 2)
            sum += array[i];
        return sum;
    }

    /**
     *
     * @param array The array of credit card digits
     * @param length The length of the array of credit card digits
     * @return The sum of all the digits in 'even' places in the array, starting at the rightmost digit
     */
    private static int sumEvenDigits(int array[], int length)
    {
        int sum = 0;
        for (int i = length - 2; i >= 0; i = i - 2)
            sum += array[i];
        return sum;
    }
}
