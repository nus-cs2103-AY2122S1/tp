package seedu.address.model.tag;

public enum Status {
    NONE(0),
    NEED_GROUP(1),
    NEED_MEMBER(2);

    private int status;

    Status(int s) {
        this.status = s;
    }

    /**
     * Converts a String into a Status by checking for keywords.
     * Used for parsing user input.
     *
     * @param s the given string.
     * @return the matching status
     */
    public static Status parseStatusFromString(String s) {
        if (s == null || s.trim().isEmpty() || !s.contains("need")) {
            return Status.NONE;
        } else if (s.contains("group")) {
            return Status.NEED_GROUP;
        } else if (s.contains("member")) {
            return Status.NEED_MEMBER;
        } else {
            return Status.NONE;
        }
    }

    /**
     * Converts an int into a Status.
     *
     * @param i the given int
     * @return the matching Status.
     */
    public static Status parseStatusFromInt(int i) {
        for (Status s : Status.values()) {
            if (s.status == 1) {
                return s;
            }
        }

        return Status.NONE;
    }

    @Override
    public String toString() {
        return "" + status;
    }
}
