import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BerlinMinutesTest {

    @Test
    public void getOutput() throws Exception {
        List<String> possibleUpperPatterns =
                Arrays.asList("OOOOOOOOOOO", "YOOOOOOOOOO", "YYOOOOOOOOO", "YYROOOOOOOO",
                              "YYRYOOOOOOO", "YYRYYOOOOOO", "YYRYYROOOOO", "YYRYYRYOOOO",
                              "YYRYYRYYOOO", "YYRYYRYYROO", "YYRYYRYYRYO", "YYRYYRYYRYY");
        List<String> possibleLowerPatterns = Arrays.asList("OOOO", "YOOO", "YYOO", "YYYO", "YYYY");

        for (int minutes = 0; minutes < 24; minutes++) {
            int outputIndexUpperLine = minutes / 5;
            int outputIndexLowerLine = minutes % possibleLowerPatterns.size();
            String expectedOutput =
                    possibleUpperPatterns.get(
                            outputIndexUpperLine) + "\n" + possibleLowerPatterns.get(
                            outputIndexLowerLine);

            final String actualValue = new BerlinMinutes(minutes).getOutput();
            final String assertMessage =
                    "Expected " + expectedOutput + " for minute value " + minutes + ". Got: " +
                    actualValue;
            assertEquals(assertMessage, expectedOutput, actualValue);
        }
    }

    @Test
    public void testGetUpperLine() throws Exception {
        List<String> possiblePatterns =
                Arrays.asList("OOOOOOOOOOO", "YOOOOOOOOOO", "YYOOOOOOOOO", "YYROOOOOOOO",
                              "YYRYOOOOOOO", "YYRYYOOOOOO", "YYRYYROOOOO", "YYRYYRYOOOO",
                              "YYRYYRYYOOO", "YYRYYRYYROO", "YYRYYRYYRYO", "YYRYYRYYRYY");

        for (int minutes = 0; minutes < 60; minutes++) {
            int patternIndex = minutes / 5;
            String expectedOutput = possiblePatterns.get(patternIndex);
            final String assertMessage =
                    "Expected " + expectedOutput + " for minute value " + minutes;
            assertEquals(assertMessage, expectedOutput, new BerlinMinutes(minutes).getUpperLine());
        }
    }

    @Test
    public void testGetLowerLine() throws Exception {
        List<String> possiblePatterns = Arrays.asList("OOOO", "YOOO", "YYOO", "YYYO", "YYYY");

        for (int minutes = 0; minutes < 60; minutes++) {
            int patternIndex = minutes % possiblePatterns.size();
            String expectedOutput = possiblePatterns.get(patternIndex);
            final String assertMessage =
                    "Expected " + expectedOutput + " for minute value " + minutes;
            assertEquals(assertMessage, expectedOutput, new BerlinMinutes(minutes).getLowerLine());
        }
    }

    @Test
    public void testMinutesSmallerZeroThrowException() {
        for (int i = -1; i > -100; i--) {
            try {
                new BerlinMinutes(i);
                fail("Should never reach this line: minutes=" + i);
            } catch(IllegalArgumentException ignored) {

            }
        }
    }

    @Test
    public void testMinutesBiggerThan59ThrowException() {
        for (int i = 60; i < 160; i++) {
            try {
                new BerlinMinutes(i);
                fail("Should never reach this line: minutes=" + i);
            } catch(IllegalArgumentException ignored) {

            }
        }
    }
}
