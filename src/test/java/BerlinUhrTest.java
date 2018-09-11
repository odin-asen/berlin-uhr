import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class BerlinUhrTest {

    private static final String NEWLINE = StringOutput.NEWLINE;

    private Calendar currentTimeMock;

    @Before
    public void setUp() {
        currentTimeMock = Mockito.mock(Calendar.class);
    }

    @Test
    public void getTimeDeliversCorrectSeconds() {
        assertSecondsTo59ForEverySecondValue(0, "O");
        assertSecondsTo59ForEverySecondValue(1, "Y");
    }

    private void assertSecondsTo59ForEverySecondValue(int initialSeconds, String expectedValue) {
        for (int seconds = initialSeconds; seconds < 60; seconds = seconds + 2) {
            Mockito.when(currentTimeMock.get(Calendar.SECOND)).thenReturn(seconds);
            BerlinUhr uhr = new BerlinUhr(currentTimeMock);
            String[] rows = uhr.getTime().split(NEWLINE);
            String firstRow = rows[0];

            assertEquals(expectedValue, firstRow);
        }
    }

    @Test
    public void getTimeDeliversCorrectHours() {
        List<String> expectedOutputs = Arrays.asList("OOOO", "ROOO", "RROO", "RRRO", "RRRR");

        for (int hours = 0; hours < 24; hours++) {
            Mockito.when(currentTimeMock.get(Calendar.HOUR_OF_DAY)).thenReturn(hours);

            BerlinUhr uhr = new BerlinUhr(currentTimeMock);
            String[] rows = uhr.getTime().split(NEWLINE);

            String upperHoursRow = rows[1];
            String lowerHoursRow = rows[2];

            int expectedFiveHoursIndex = hours / 5;
            int expectedSingleHoursIndex = hours % 5;

            assertEquals(expectedOutputs.get(expectedFiveHoursIndex), upperHoursRow);
            assertEquals(expectedOutputs.get(expectedSingleHoursIndex), lowerHoursRow);
        }
    }

    @Test
    public void getTimeDeliversCorrectMinutes() {
        List<String> expectedFiveMinutesOutputs =
                Arrays.asList("OOOOOOOOOOO", "YOOOOOOOOOO", "YYOOOOOOOOO", "YYROOOOOOOO",
                              "YYRYOOOOOOO", "YYRYYOOOOOO", "YYRYYROOOOO", "YYRYYRYOOOO",
                              "YYRYYRYYOOO", "YYRYYRYYROO", "YYRYYRYYRYO", "YYRYYRYYRYY");
        List<String> expectedSingleMinutesOutputs =
                Arrays.asList("OOOO", "YOOO", "YYOO", "YYYO", "YYYY");
        for (int minutes = 0; minutes < 60; minutes++) {
            Mockito.when(currentTimeMock.get(Calendar.MINUTE)).thenReturn(minutes);

            BerlinUhr uhr = new BerlinUhr(currentTimeMock);

            String[] rows = uhr.getTime().split(NEWLINE);
            String upperMinuteRow = rows[3];
            String lowerMinuteRow = rows[4];

            int expectedFiveMinutesIndex = minutes / 5;
            int expectedSingleMinutesIndex = minutes % 5;

            String assertMessage = "Wrong output for minutes=" + minutes;
            assertEquals(
                    assertMessage, expectedFiveMinutesOutputs.get(expectedFiveMinutesIndex),
                    upperMinuteRow);
            assertEquals(assertMessage,
                         expectedSingleMinutesOutputs.get(expectedSingleMinutesIndex),
                         lowerMinuteRow);
        }
    }
}
