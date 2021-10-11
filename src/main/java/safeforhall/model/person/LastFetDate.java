package safeforhall.model.person;

public class LastFetDate {

    public static final String MESSAGE_CONSTRAINTS = "TODO";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String date;

    public LastFetDate(String date) {
        this.date = date;
    }

    public static boolean isValidFetDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getDate() {
        return date;
    }
}
