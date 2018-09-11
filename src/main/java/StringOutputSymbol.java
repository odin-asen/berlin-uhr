public enum StringOutputSymbol {
    YELLOW("Y"),
    RED("R"),
    OFF("O");

    private String outputValue;

    StringOutputSymbol(String outputValue) {
        this.outputValue = outputValue;
    }

    public String toString() {
        return outputValue;
    }
}
