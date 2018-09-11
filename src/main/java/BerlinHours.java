public class BerlinHours {

    private int hours;

    public BerlinHours(int hours) {
        validate(hours);

        this.hours = hours;
    }

    private void validate(int hours) throws IllegalArgumentException {
        if (hours < 0 || hours > 23) {
            throw new IllegalArgumentException(hours + " is out of bounds [0, 23]");
        }
    }

    public String getOutput() {
        return getUpperLine() + StringOutput.NEWLINE + getLowerLine();
    }

    public String getUpperLine() {
        return buildUpperHourSymbols() + buildUpperEmptyHours();
    }

    private String buildUpperHourSymbols() {
        return concatSameSymbolMultipleTimes(
                calculateUpperHourSymbolCount(), StringOutputSymbol.RED);
    }

    private int calculateUpperHourSymbolCount() {
        return hours / 5;
    }

    private String concatSameSymbolMultipleTimes(int numberOfConcatenations,
                                                 StringOutputSymbol symbol) {
        StringBuilder stringBuilder = new StringBuilder(4);
        for (int i = 0; i < numberOfConcatenations; i++) {
            stringBuilder.append(symbol);
        }
        return stringBuilder.toString();
    }

    private String buildUpperEmptyHours() {
        return concatSameSymbolMultipleTimes(calculateUpperEmptyHours(), StringOutputSymbol.OFF);
    }

    private int calculateUpperEmptyHours() {
        return 4 - calculateUpperHourSymbolCount();
    }

    public String getLowerLine() {
        return buildLowerHourSymbols() + buildLowerEmptyHours();
    }

    private String buildLowerHourSymbols() {
        return concatSameSymbolMultipleTimes(calculateLowerHourSymbolCount(), StringOutputSymbol.RED);
    }

    private int calculateLowerHourSymbolCount() {
        return hours % 5;
    }

    private String buildLowerEmptyHours() {
        return concatSameSymbolMultipleTimes(calculateLowerEmptyHours(), StringOutputSymbol.OFF);
    }

    private int calculateLowerEmptyHours() {
        return 4 - calculateLowerHourSymbolCount();
    }
}
