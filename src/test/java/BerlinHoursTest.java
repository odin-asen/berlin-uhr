import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BerlinHoursTest {
    @Test
    public void testGetUpperLineOutput() {
        List<String> expectedOutputs = Arrays.asList("OOOO", "ROOO", "RROO", "RRRO", "RRRR");

        int hoursPerRedSymbol = 5;
        for (int hour = 0; hour < 24; hour++) {
            int outputIndex = hour / hoursPerRedSymbol;
            assertHourOutput(
                    hour, expectedOutputs.get(outputIndex), new BerlinHours(hour).getUpperLine());
        }
    }

    private void assertHourOutput(int hour, String expectedOutput, String actualOutput) {
        final String assertMessage = "Expected " + expectedOutput + " for hour value " + hour;
        assertEquals(assertMessage, expectedOutput, actualOutput);
    }

    @Test
    public void testGetLowerLineOutput() {
        List<String> expectedOutputs = Arrays.asList("OOOO", "ROOO", "RROO", "RRRO", "RRRR");

        int numberOfSymbolCombinations = expectedOutputs.size();
        for (int hour = 0; hour < 24; hour++) {
            int outputIndex = hour % numberOfSymbolCombinations;
            assertHourOutput(
                    hour, expectedOutputs.get(outputIndex), new BerlinHours(hour).getLowerLine());
        }
    }

    @Test
    public void testGetOutput() {
        List<String> expectedOutputs = Arrays.asList("OOOO", "ROOO", "RROO", "RRRO", "RRRR");

        for (int hour = 0; hour < 24; hour++) {
            int outputIndexUpperLines = hour / expectedOutputs.size();
            int outputIndexLowerLines = hour % expectedOutputs.size();
            String expectedOutput =
                    expectedOutputs.get(outputIndexUpperLines) + "\n" + expectedOutputs.get(
                            outputIndexLowerLines);

            assertHourOutput(hour, expectedOutput, new BerlinHours(hour).getOutput());
        }
    }

    @Test
    public void constructorWithHourSmallerThanLowerBoundThrowsException() {
        for (int invalidHour = -1; invalidHour > -100; invalidHour--) {
            try {
                new BerlinHours(invalidHour);
                fail("Should not reach this line for hour: " + invalidHour);
            } catch (IllegalArgumentException ignored) {

            }
        }
    }

    @Test
    public void constructorWithHourBiggerThanUpperBoundThrowsException() {
        int upperBound = 23;
        for (int invalidHour = upperBound + 1; invalidHour < 100; invalidHour++) {
            try {
                new BerlinHours(invalidHour);
                fail("Should not reach this line for hour: " + invalidHour);
            } catch (IllegalArgumentException ignored) {

            }
        }
    }
}
