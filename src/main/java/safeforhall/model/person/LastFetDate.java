package safeforhall.model.person;

public class LastFetDate {

    public static final String MESSAGE_CONSTRAINTS = "TODO";

    public final String date;

    public LastFetDate(String date) {
        this.date = date;
    }

    public static boolean isValidFetDate(String date) {
        return true;
    }

    public String getDate() {
        return date;
    }
}
