import java.util.Calendar;

public class BerlinUhr {

    private Calendar currentTime;

    public BerlinUhr(Calendar currentTime) {
        this.currentTime = currentTime;
    }

    public String getTime() {
        return getCurrentSecondsOutput() + StringOutput.NEWLINE + getCurrentHoursOutput() + StringOutput.NEWLINE + getCurrentMinutesOutput();
    }

    private String getCurrentSecondsOutput() {
        return new BerlinSeconds(currentTime.get(Calendar.SECOND)).getOutput();
    }

    private String getCurrentHoursOutput() {
        return new BerlinHours(currentTime.get(Calendar.HOUR_OF_DAY)).getOutput();
    }

    private String getCurrentMinutesOutput() {
        return new BerlinMinutes(currentTime.get(Calendar.MINUTE)).getOutput();
    }
}
