package seedu.academydirectory.model.student;

import static java.util.Objects.requireNonNull;

public class StudioRecord implements Information {

    private final Attendance attendance;
    private final Participation participation;

    public static final String MESSAGE_CONSTRAINTS =
            "Studio Session should only contain numbers, and it should range from 1 to 10.";

    private static final String VALIDATION_REGEX = "^-?\\d+$";

    public StudioRecord(Integer numberOfSessions) {
        this.attendance = new Attendance(numberOfSessions);
        this.participation = new Participation(numberOfSessions);
    }

    public StudioRecord(Attendance attendance, Participation participation) {
        this.participation = participation;
        this.attendance = attendance;
    }

    public String toString() {
        return studioRecordStringHelper();
    }

    public String studioRecordStringHelper() {
        int[] participationArr = getParticipation().getParticipationArray();
        boolean[] attendanceArr = getAttendance().getAttendanceArray();
        if (participationArr.length != attendanceArr.length) {
            return "STUDIO RECORD FORMAT INVALID"; // shouldn't happen as we're controlling the studio record
        }
        String result = "";
        for (int i = 0; i < participationArr.length; i++) {
            boolean attendanceStatus = attendanceArr[i];
            if (attendanceStatus) {
                result += "[" + (i + 1) + ":  " + participationArr[i] + "]  ";
            } else {
                result += "[   ]  ";
            }
        }
        return result;

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

    public Participation getParticipation() {
        return this.participation;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudioRecord // instanceof handles nulls
                && attendance.equals(((StudioRecord) other).attendance));
    }

}
