package seedu.academydirectory.model.student;

import static java.util.Objects.requireNonNull;

public class StudioRecord implements Information {

    private Attendance attendance;

    public static final String MESSAGE_CONSTRAINTS =
            "Studio Session should only contain numbers, and it should range from 1 to 10.";

    private static final String VALIDATION_REGEX = "^[1-9]\\d*$";

    public StudioRecord(Integer numberOfSessions) {
        this.attendance = new Attendance(numberOfSessions);
    }

    public String toString() {
        return attendance.toString();
    }

    public static boolean isValidStudioRecord(String test) {
        requireNonNull(test);
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        Integer studioRecord = Integer.parseInt(test);
        return 0 < studioRecord && studioRecord < 11;
    }

    public Attendance getAttendance() {
        return this.attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudioRecord // instanceof handles nulls
                && attendance.equals(((StudioRecord) other).attendance));
    }

}
