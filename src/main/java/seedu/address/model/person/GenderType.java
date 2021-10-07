package seedu.address.model.person;

public enum GenderType {
    MALE("M"), FEMALE("F");

    public final String symbol;

    GenderType(String symbol) {
        this.symbol = symbol;
    }
}
