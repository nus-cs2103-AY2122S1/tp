package safeforhall.model.person;

public class LastCollectionDate {

    public static final String MESSAGE_CONSTRAINTS = "TODO";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String date;

    public LastCollectionDate(String date) {
        this.date = date;
    }

    public static boolean isValidCollectionDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getDate() {
        return date;
    }
}
