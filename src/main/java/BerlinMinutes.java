public class BerlinMinutes {

    private static final int SINGLE_MINUTES_SYMBOL_COUNT = 4;
    private static final int SINGLE_MINUTES_COMBINATIONS = 5;
    private static final int UPPER_MINUTES_VALUE = 5;
    private static final int FIVE_MINUTES_SYMBOL_COUNT = 11;

    private int minutes;

    public BerlinMinutes(int minutes) {
        this.minutes = minutes;
        validateMinutes();
    }

    private void validateMinutes() {
        if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("minutest must be a value from 0 to 59");
        }
    }

    public String getOutput() {
        return getUpperLine() + StringOutput.NEWLINE + getLowerLine();
    }

    public String getUpperLine() {
        return buildUpperMinutesSymbols() + buildUpperEmptyMinutes();
    }

    private String buildUpperMinutesSymbols() {
        StringBuilder outputBuilder = new StringBuilder(FIVE_MINUTES_SYMBOL_COUNT);

        for (int i = 1; i <= calculateUpperMinutesSymbolCount(); i++) {
            if (integerIsMultipleValueOfThree(i)) {
                outputBuilder.append(StringOutputSymbol.RED);
            } else {
                outputBuilder.append(StringOutputSymbol.YELLOW);
            }
        }

        return outputBuilder.toString();
    }

    private boolean integerIsMultipleValueOfThree(int integerValue) {
        return integerValue % 3 == 0;
    }

    private int calculateUpperMinutesSymbolCount() {
        return minutes / UPPER_MINUTES_VALUE;
    }

    private String concatSameSymbolMultipleTimes(int numberOfConcatenations,
                                                 StringOutputSymbol string) {
        StringBuilder stringBuilder = new StringBuilder(SINGLE_MINUTES_SYMBOL_COUNT);
        for (int i = 0; i < numberOfConcatenations; i++) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    private String buildUpperEmptyMinutes() {
        return concatSameSymbolMultipleTimes(calculateUpperEmptyHours(), StringOutputSymbol.OFF);
    }

    private int calculateUpperEmptyHours() {
        return FIVE_MINUTES_SYMBOL_COUNT - calculateUpperMinutesSymbolCount();
    }

    public String getLowerLine() {
        return buildLowerMinutesSymbols() + buildLowerEmptyMinutes();
    }

    private String buildLowerMinutesSymbols() {
        return concatSameSymbolMultipleTimes(
                calculateLowerMinutesSymbolCount(), StringOutputSymbol.YELLOW);
    }

    private int calculateLowerMinutesSymbolCount() {
        return minutes % SINGLE_MINUTES_COMBINATIONS;
    }

    private String buildLowerEmptyMinutes() {
        return concatSameSymbolMultipleTimes(
                calculateLowerEmptyMinutesSymbolCount(), StringOutputSymbol.OFF);
    }

    private int calculateLowerEmptyMinutesSymbolCount() {
        return 4 - calculateLowerMinutesSymbolCount();
    }
}
