public class BerlinSeconds {

    private int seconds;

    public BerlinSeconds(int seconds) {
        validate(seconds);

        this.seconds = seconds;
    }

    private void validate(int seconds) {
        if (seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException();
        }
    }

    public String getOutput() {
        if (seconds % 2 == 0) {
            return StringOutputSymbol.OFF.toString();
        } else {
            return StringOutputSymbol.YELLOW.toString();
        }
    }
}
