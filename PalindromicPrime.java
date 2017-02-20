package ca.uwo.eng.se2205b;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Problem #2: Finds prime numbers and checks if they are palindromes.
 */

public class PalindromicPrime{

    boolean [] primeArray = new boolean[100001];
    int [] palindromeArray = new int[100];
    int numOfPalindromes;

    PalindromicPrime() {
        numOfPalindromes = 0;
        generatePalindromes();
    }

    /**
     * Creates an iterator that returns prime palindrome numbers.
     *
     * @return Non-{@code null} iterator to get palindrome prime numbers.
     */

    private Iterator<Integer> palindromeIterator() {
        return new PalindromeIterator();
    }

    public static void main(String args[])
    {
        PalindromicPrime generator = new PalindromicPrime();

        Iterator<Integer> primeIter = generator.palindromeIterator();

        int lineIndex = 0;
        while (primeIter.hasNext()) {
            if ((lineIndex > 0) && (lineIndex % 10 == 0))
                System.out.println(" ");
            System.out.printf(primeIter.next() + " ");
            lineIndex++;
        }
    }

    private void generatePalindromes()
    {
        //We want to generate 100 palindromic prime numbers so loop while the palindromes counter is less than 100 (includes 0)
        while (numOfPalindromes < 100) {
            //Starting at 2 and looping to 100000,
            for (int i = 2; i <= 100000; i++) {
                //Once we find an array index that is false,
                if (!primeArray[i]) {
                    //We go through the rest of the array and change every array index that is a multiple of the one in question to true to denote
                    //that they are not a prime number
                    for (int j = i * 2; j <= 100000; j += i) {
                        primeArray[j] = true;
                    }
                    //Next, we call the isPalindrome function
                    if (isPalindrome(i)) {
                        //If we have found that this number is a prime palindrome we copy the number into our palindromeArray
                        //and increment our palindrome counter
                        palindromeArray[numOfPalindromes] = i;
                        numOfPalindromes++;
                        if (numOfPalindromes == 100) {
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     *
     * @param num The number being checked to see if it is a palindrome
     * @return Whether or not the number is a palindrome
     */
    private boolean isPalindrome(int num)
    {
        //Find the length of the number
        String numString = Long.toString(num);
        int length = numString.length();

        //Next, convert the number to an integer array
        //Create an integer array for the card number
        int [] numArray = new int[length];
        for (int i = 0; i < length; i++) {
            numArray[i] = numString.charAt(i) - '0';
        }

        //This starts by comparing the leftmost and rightmost digits and moves inwards
        for (int i = 0; i < (length/2); i++)
        {
            //Compare the current array index to the same array index coming from the right side inward
            if (numArray[i] != numArray[(length - 1) - i])
                return false;
        }
        return true;
    }

    private class PalindromeIterator implements Iterator<Integer> {

        private int palindromeIndex;

        PalindromeIterator() {
            palindromeIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return (palindromeIndex < 100);
        }

        @Override
        public Integer next() {
            try {
                palindromeIndex++;
                return palindromeArray[palindromeIndex - 1];
            } catch(NoSuchElementException e) {
                System.out.println("Error");
            }
            return 0;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Cannot do this!");
        }
    }
}