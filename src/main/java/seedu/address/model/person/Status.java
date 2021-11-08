package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Status {
    NONE(false),
    APPLIED(false),
    SCHEDULED(false),
    INTERVIEWED(false),
    ACCEPTED(false),
    REJECTED(true),
    WITHDRAWN(true);

    public static final String MESSAGE_CONSTRAINTS = "Status can take the values:\n0.NONE\n1."
            + "APPLIED\n2.SCHEDULED"
            + "\n3.INTERVIEWED\n4.ACCEPTED\n5.REJECTED\n6.WITHDRAWN";

    private static final List<String> valid = new ArrayList<String>(Arrays.asList("NONE", "APPLIED", "SCHEDULED",
            "INTERVIEWED",
            "ACCEPTED",
            "REJECTED",
            "WITHDRAWN", ""));

    private boolean isCompleted;

    private Status(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }


    /**
     * Returns the corresponding Status Enum give a valid input.
     *
     * @param statusInput String input.
     * @return Status.
     */
    public static Status parseStatus(String statusInput) {
        statusInput = statusInput == null ? "" : statusInput;
        switch (statusInput.toUpperCase()) {
        case "":
        case "APPLIED":
            return Status.APPLIED;
        case "SCHEDULED":
            return Status.SCHEDULED;
        case "INTERVIEWED":
            return Status.INTERVIEWED;
        case "ACCEPTED":
            return Status.ACCEPTED;
        case "REJECTED":
            return Status.REJECTED;
        case "WITHDRAWN":
            return Status.WITHDRAWN;
        default:
            return Status.NONE;
        }
    }

    public static Boolean isValidStatus(String statusInput) {
        return valid.contains(statusInput);
    }


    /**
     * Returns if the status represent a completed state.
     *
     * @return isCompleted.
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
