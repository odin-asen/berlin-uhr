import org.junit.Test;

import static org.junit.Assert.*;

public class BerlinSecondsTest {
    private static final String OUTPUT_EVEN = StringOutputSymbol.OFF.toString();
    private static final String OUTPUT_ODD = StringOutputSymbol.YELLOW.toString();

    @Test
    public void testGetOutputForEvenSecond() {
        int startIteration = 0;
        assertOutputSecondsUpTo59ForEachSecondInteger(OUTPUT_EVEN, startIteration);
    }

    private void assertOutputSecondsUpTo59ForEachSecondInteger(String expectedOutput, int startIteration) {
        for (int i = startIteration; i < 60; i=i+2) {
            BerlinSeconds second = new BerlinSeconds(i);
            assertEquals(expectedOutput, second.getOutput());
        }
    }

    @Test
    public void testGetOutputForUnevenSecond() {
        int startIteration = 1;
        assertOutputSecondsUpTo59ForEachSecondInteger(OUTPUT_ODD, startIteration);

    }

    @Test
    public void testSecondSmallerZeroThrowException() {
        for (int i = -1; i > -100; i--) {
            try {
                new BerlinSeconds(i);
                fail("Should never reach this line: seconds=" + i);
            } catch(IllegalArgumentException ignored) {

            }
        }
    }

    @Test
    public void testSecondsBiggerThan59ThrowException() {
        for (int i = 60; i < 160; i++) {
            try {
                new BerlinSeconds(i);
                fail("Should never reach this line: seconds=" + i);
            } catch(IllegalArgumentException ignored) {

            }
        }
    }
}
