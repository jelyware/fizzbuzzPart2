package com.fizzbuzz;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Unit test suite for FizzBuzz.
 */
public class FizzBuzzTest
{
    private class Counts
    {
      public int luckyCount;
      public int fizzbuzzCount;
      public int fizzCount;
      public int buzzCount;
      public int numberCount;
    }

    /**
     * Base test function to execute FizzBuzz and store std out into buffer
     * @param min start of range of contiguous numbers
     * @param max end of range of contiguous numbers
     * @return string containing std out from FizzBuzz execution
     */
    public String getRunFizzBuzzResponseFromStdOut(int min, int max)
    {
        ByteArrayOutputStream stdOutBuffer = new ByteArrayOutputStream();
        PrintStream stdOut = System.out;
        FizzBuzz fb = new FizzBuzz();

        System.setOut(new PrintStream(stdOutBuffer));   // Point to buffer
        fb.runFizzBuzz(min, max);
        String data = stdOutBuffer.toString().replaceAll("\r", "").strip();
        System.setOut(stdOut);                          // Point to std out
        return (data);
    }

    /**
     * Helper function to get expected string counts
     * @param min start of range of contiguous numbers
     * @param max end of range of contiguous numbers
     * @return object containing each string count
     */
    public Counts getExpectedStringCounts(int min, int max)
    {
        Counts counts = new Counts();

        for(int n = min; n < max+1; n++)
        {
            String numStr = Integer.toString(n);
            if (numStr.contains("3"))
            {
                counts.luckyCount++;
            }
            else if (n % 15 == 0)
            {
                counts.fizzbuzzCount++;
            }
            else if (n % 3 == 0)
            {
                counts.fizzCount++;
            }
            else if (n % 5 == 0)
            {
                counts.buzzCount++;
            }
            else
            {
                counts.numberCount++;
            }
        }
        return counts;
    }

    /**
     * Helper function to get actual string counts
     * @param outputArr array containing output strings from FizzBuzz execution
     * @return object containing each string count
     */
    public Counts getActualStringCounts(String [] outputArr)
    {
        Counts counts = new Counts();

        for(String i : outputArr)
        {
            if (i.equals("lucky"))
            {
                counts.luckyCount++;
            }
            else if (i.equals("fizzbuzz"))
            {
                counts.fizzbuzzCount++;
            }
            else if (i.equals("fizz"))
            {
                counts.fizzCount++;
            }
            else if (i.equals("buzz"))
            {
                counts.buzzCount++;
            }
            else
            {
                counts.numberCount++;
            }
        }
        return counts;
    }

    @Test
    public void test0_OutputFrom0To0()
    {
        System.out.println("TEST #0: Get Expected Output From 0 to 0");
        int minNum = 0;
        int maxNum = 0;
        String response = getRunFizzBuzzResponseFromStdOut(minNum, maxNum);
        String expected = "fizzbuzz";
        assertEquals(response, expected);
    }

    @Test
    public void test1_OutputFrom21To21()
    {
        System.out.println("TEST #1: Get Expected Output From 53 to 53");
        int minNum = 53;
        int maxNum = 53;
        String response = getRunFizzBuzzResponseFromStdOut(minNum, maxNum);
        String expected = "lucky";
        assertEquals(response, expected);
    }

    @Test
    public void test2_OutputFrom22To22()
    {
        System.out.println("TEST #2: Get Expected Output From 33 to 33");
        int minNum = 33;
        int maxNum = 33;
        String response = getRunFizzBuzzResponseFromStdOut(minNum, maxNum);
        String expected = "lucky";
        assertEquals(response, expected);
    }

    @Test
    public void test3_OutputFrom0To20()
    {
        System.out.println("TEST #3: Get Expected Output From 0 to 20");
        int minNum = 0;
        int maxNum = 20;
        String response = getRunFizzBuzzResponseFromStdOut(minNum, maxNum);
        String expected = "fizzbuzz 1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz";
        assertEquals(response, expected);
    }

    @Test
    public void test4_OutputFrom45To60()
    {
        System.out.println("TEST #4: Get Expected Output From 29 to 49");
        int minNum = 29;
        int maxNum = 49;
        String response = getRunFizzBuzzResponseFromStdOut(minNum, maxNum);
        String expected = "29 lucky lucky lucky lucky lucky lucky lucky lucky lucky lucky buzz 41 fizz lucky 44 fizzbuzz 46 47 fizz 49";
        assertEquals(response, expected);
    }

    @Test
    public void test5_FizzbuzzCountInOutputFrom0To30()
    {
        System.out.println("TEST #5: Get Expected 'fizzbuzz' Count in Output From 0 to 30");
        int minNum = 0;
        int maxNum = 30; // A large multiple of both 3 and 5 and therefore 15
        Counts expectedCounts = getExpectedStringCounts(minNum, maxNum);
        String response = getRunFizzBuzzResponseFromStdOut(minNum, maxNum);
        String [] respArr = response.split(" ");
        Counts actualCounts = getActualStringCounts(respArr);
        System.out.println("  [DEBUG] Expected: " + expectedCounts.luckyCount + "==> Received: " + actualCounts.luckyCount);
        System.out.println("  [DEBUG] Expected: " + expectedCounts.fizzbuzzCount + "==> Received: " + actualCounts.fizzbuzzCount);
        System.out.println("  [DEBUG] Expected: " + expectedCounts.fizzCount + "==> Received: " + actualCounts.fizzCount);
        System.out.println("  [DEBUG] Expected: " + expectedCounts.buzzCount + "==> Received: " + actualCounts.buzzCount);
        System.out.println("  [DEBUG] Expected: " + expectedCounts.numberCount + "==> Received: " + actualCounts.numberCount);
        assertEquals(expectedCounts.luckyCount, actualCounts.luckyCount);
        assertEquals(expectedCounts.fizzbuzzCount, actualCounts.fizzbuzzCount);
        assertEquals(expectedCounts.fizzCount, actualCounts.fizzCount);
        assertEquals(expectedCounts.buzzCount, actualCounts.buzzCount);
        assertEquals(expectedCounts.numberCount, actualCounts.numberCount);
    }

    @Test
    public void test8_validateStringCountsInOutputFrom0To999990()
    {
        System.out.println("TEST #8: Get Expected Buzz Count in Output From 0 to 999990");
        int minNum = 0;
        int maxNum = 999990; // A large multiple of both 3 and 5 and therefore 15
        Counts expectedCounts = getExpectedStringCounts(minNum, maxNum);
        String response = getRunFizzBuzzResponseFromStdOut(minNum, maxNum);
        String [] respArr = response.split(" ");
        Counts actualCounts = getActualStringCounts(respArr);
        assertEquals(expectedCounts.luckyCount, actualCounts.luckyCount);
        assertEquals(expectedCounts.fizzbuzzCount, actualCounts.fizzbuzzCount);
        assertEquals(expectedCounts.fizzCount, actualCounts.fizzCount);
        assertEquals(expectedCounts.buzzCount, actualCounts.buzzCount);
        assertEquals(expectedCounts.numberCount, actualCounts.numberCount);
    }

    @Test
    public void test11_StartAndEndOutputFrom0To999990()
    {
        System.out.println("TEST #11: Get Expected Start and End Output From 0 to 999990");
        int minNum = 0;
        int maxNum = 999990; // A large multiple of both 3 and 5 and therefore 15
        String response = getRunFizzBuzzResponseFromStdOut(minNum, maxNum);
        String [] respArr = response.split(" ");
        int arrLen = respArr.length;
        String startOfStringReceived = respArr[0];
        String endOfStringReceived = respArr[arrLen-1];
        String startOfStringExpected = "fizzbuzz";
        String endOfStringExpected = "fizzbuzz";
        System.out.println("  [DEBUG] Start of string value received from runFizzBuzz(): " + startOfStringReceived + ".");
        assertEquals(startOfStringReceived, startOfStringExpected);
        System.out.println("  [DEBUG] End of string value received from runFizzBuzz(): " + endOfStringReceived + ".\n");
        assertEquals(endOfStringReceived, endOfStringExpected);
    }

}
