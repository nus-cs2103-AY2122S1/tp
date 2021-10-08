package seedu.academydirectory.model.student;

import static java.util.Objects.requireNonNull;

public class StudioRecord implements Information {

    private Attendance attendance;

    public static final String VALIDATION_REGEX = "^(10|[1-9]?[0-9])$"; // session number between 1 and 10
    public static final String MESSAGE_CONSTRAINTS =
            "Studio Session should only contain numbers, and it should range from 1 to 10.";

    public StudioRecord(Integer numberOfSessions) {
        this.attendance = new Attendance(numberOfSessions);
    }

    public String toString() {
        return attendance.toString();
    }

    public static boolean isValidStudioRecord(String test) {
        requireNonNull(test);
        return test.matches(VALIDATION_REGEX);
    }

    public Attendance getAttendance() {
        return this.attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

}
