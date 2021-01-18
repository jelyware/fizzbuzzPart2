package com.fizzbuzz;

/**
 * FizzBuzz (Step 2) API
 * @author Jessica lynch
 * Purpose: Use to execute Step 2 FizzBuzz and print string output to std out.
 * Example output:
 *    1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz
 *
 */
public class FizzBuzz
{
    /**
     * Runs FizzBuzz (Step 2)
     * @param min start of range of contiguous numbers
     * @param max end of range of contiguous numbers
     */
    public void runFizzBuzz(int min, int max)
    {
        StringBuilder sb = new StringBuilder();
        for(int n = min; n < max+1; n++)
        {
            String numStr = Integer.toString(n);
            if (numStr.contains("3"))
            {
                sb.append("lucky ");
            }
            else if (n % 15 == 0)
            {
                sb.append("fizzbuzz ");
            }
            else if (n % 3 == 0)
            {
                sb.append("fizz ");
            }
            else if (n % 5 == 0)
            {
                sb.append("buzz ");
            }
            else
            {
                sb.append(Integer.toString(n));
                sb.append(" ");
            }
        }
        System.out.println(sb.toString().strip());
    }
}
