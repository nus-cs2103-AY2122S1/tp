package seedu.address.model.tag;

import java.util.stream.Stream;

public enum Status {
    NONE("G"),
    NEED_GROUP("SG"),
    NEED_MEMBER("SM");

    private String status;

    Status(String s) {
        this.status = s;
    }

    /**
     * Parses the status of the filter command.
     *
     * @param status The status specified in the filter command
     * @return The {@code Status} group status.
     */
    public static Status parseStatusForFilter(String status) {
        assert (status != null);
        return Status.valueOf(status);
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String test) {
        System.out.println(Status.NONE.toString());
        return Stream.of(Status.values()).anyMatch(status -> status.toString().equalsIgnoreCase(test));
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

    @Override
    public String toString() {
        return "" + status;
    }
}
