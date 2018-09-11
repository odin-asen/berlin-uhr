import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class ITBerlinUhrTest {

    @Test
    public void testOutputForDifferentTimes() {
        Map<String, BerlinUhr> expectedOutputsToBerlinUhrMap =
                createMapWithExpectedBerlinTimesToBerlinUhrObjects();

        for (Map.Entry<String, BerlinUhr> entry : expectedOutputsToBerlinUhrMap.entrySet()) {
            assertEquals(entry.getKey(), entry.getValue().getTime());
        }
    }

    private Map<String, BerlinUhr> createMapWithExpectedBerlinTimesToBerlinUhrObjects() {
        Map<String, BerlinUhr> expectedOutputMap = new HashMap<>();
        expectedOutputMap.put(
                assembleBerlinTimeFragments("O", "OOOO", "OOOO", "OOOOOOOOOOO", "OOOO"),
                new BerlinUhr(createNewTime(0, 0, 0)));
        expectedOutputMap.put(
                assembleBerlinTimeFragments("O", "ROOO", "RRRO", "YYRYYRYYOOO", "YYYY"),
                new BerlinUhr(createNewTime(8, 44, 30)));
        expectedOutputMap.put(
                assembleBerlinTimeFragments("O", "RROO", "OOOO", "YYOOOOOOOOO", "OOOO"),
                new BerlinUhr(createNewTime(10, 10, 10)));
        expectedOutputMap.put(
                assembleBerlinTimeFragments("Y", "RROO", "RRRO", "YYROOOOOOOO", "YYOO"),
                new BerlinUhr(createNewTime(13, 17, 1)));
        expectedOutputMap.put(
                assembleBerlinTimeFragments("Y", "RRRO", "RROO", "YYROOOOOOOO", "YOOO"),
                new BerlinUhr(createNewTime(17, 16, 7)));
        expectedOutputMap.put(
                assembleBerlinTimeFragments("Y", "RRRR", "RRRO", "YYRYYRYYRYY", "YYYY"),
                new BerlinUhr(createNewTime(23, 59, 59)));
        return expectedOutputMap;
    }

    private String assembleBerlinTimeFragments(String... timeFragment) {
        int berlinTimeCharacterCount = 28;
        StringBuilder builder = new StringBuilder(berlinTimeCharacterCount);

        boolean isFirst = true;
        for (String fragment : timeFragment) {
            if (!isFirst) {
                builder.append(StringOutput.NEWLINE);
            }
            builder.append(fragment);

            isFirst = false;
        }

        return builder.toString();
    }

    private Calendar createNewTime(int hours, int minutes, int seconds) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, seconds);

        return calendar;
    }
}
