package seedu.gamebook.model.gameentry;

public class EndAmount extends Amount {
    private static final EndAmount EMPTY = new EndAmount();

    public EndAmount(String endAmount) {
        super(endAmount);
    }

    public EndAmount(Double endAmount) {
        super(endAmount);
    }

    private EndAmount() {
        super();
    }

    public static EndAmount empty() {
        return EMPTY;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

}
