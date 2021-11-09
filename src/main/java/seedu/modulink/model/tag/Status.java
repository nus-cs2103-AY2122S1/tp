package seedu.modulink.model.tag;

import seedu.modulink.logic.parser.exceptions.ParseException;

public enum Status {
    NONE("G"),
    NEED_GROUP("SG"),
    NEED_MEMBER("SM");

    public static final String MESSAGE_CONSTRAINTS =
            "Available grouping statuses are: \"need group\" and \"need member\".\n"
            + "Please leave the module description blank to indicate you do not need a group.";

    public static final String VALIDATION_REGEX =
            "((need group)|(need members?))?";

    private String status;

    Status(String s) {
        this.status = s;
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String test) {
        test = test.toLowerCase();
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Converts a String into a Status by checking for keywords.
     * Used for parsing user input.
     *
     * @param s the given string.
     * @return the matching status
     */
    public static Status parseStatusFromString(String s) throws ParseException {
        s = s.trim();
        s = s.toLowerCase();
        if (!isValidStatus(s)) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }

        if (s.equalsIgnoreCase("need group")) {
            return Status.NEED_GROUP;
        } else if (s.equalsIgnoreCase("need member")
                || s.equalsIgnoreCase("need members")) {
            return Status.NEED_MEMBER;
        } else {
            return Status.NONE;
        }
    }

    @Override
    public String toString() {
        return "" + status;
    }
}
