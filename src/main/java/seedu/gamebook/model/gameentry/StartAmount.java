package seedu.gamebook.model.gameentry;

public class StartAmount extends Amount {
    private static final StartAmount EMPTY = new StartAmount();

    public StartAmount(String startAmount) {
        super(startAmount);
    }

    public StartAmount(Double startAmount) {
        super(startAmount);
    }

    private StartAmount() {
        super();
    }

    public static StartAmount empty() {
        return EMPTY;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }
}
