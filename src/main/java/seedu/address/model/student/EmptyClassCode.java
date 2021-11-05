package seedu.address.model.student;

public class EmptyClassCode extends ClassCode {

    public static final String EMPTY_CLASSCODE = "G00";

    /**
     * Constructs an {@code EmptyClassCode}.
     */
    public EmptyClassCode() {
        super(EMPTY_CLASSCODE);
    }

    public static boolean isDefaultClassCode(String test) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || other instanceof EmptyClassCode
                || ((ClassCode) other).value.equals(this.value);
    }
}
