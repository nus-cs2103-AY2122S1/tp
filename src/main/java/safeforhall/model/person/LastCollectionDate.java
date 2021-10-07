package safeforhall.model.person;

public class LastCollectionDate {

    public static final String MESSAGE_CONSTRAINTS = "TODO";

    public final String date;

    public LastCollectionDate(String date) {
        this.date = date;
    }

    public static boolean isValidCollectionDate(String date) {
        return true;
    }

    public String getDate() {
        return date;
    }
}
