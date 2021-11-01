package seedu.academydirectory.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.academydirectory.commons.util.CollectionUtil.requireAllNonNull;

public class StudioRecord implements Information {

    public static final String MESSAGE_CONSTRAINTS =
            "Studio Session should only be an integer from 1 to 12 inclusive.";

    private static final String VALIDATION_REGEX = "^-?\\d+$";

    private final Integer numberOfSessions;
    private final Attendance attendance;
    private final Participation participation;

    /**
     * Default constructor for StudioRecord using number of sessions
     * @param numberOfSessions The number of Studio Sessions
     */
    public StudioRecord(Integer numberOfSessions) {
        this.numberOfSessions = numberOfSessions;
        this.attendance = new Attendance(numberOfSessions);
        this.participation = new Participation(numberOfSessions);
    }

    /**
     * Alternative constructor to build with known Attendance and Participation
     * @param attendance StudioRecord's Attendance object
     * @param participation StudioRecord's Attendance object
     */
    public StudioRecord(Attendance attendance, Participation participation) {
        requireAllNonNull(attendance, participation);
        assert (attendance.getSessionCount().equals(participation.getSessionCount()));
        this.numberOfSessions = attendance.getSessionCount();
        this.participation = participation;
        this.attendance = attendance;
    }

    public String toString() {
        return studioRecordStringHelper();
    }

    private String studioRecordStringHelper() {
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

    /**
     * Checks if the given session specified is valid.
     *
     * @param test The session being tested
     */
    public static boolean isValidStudioRecord(String test) {
        requireNonNull(test);
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        Integer studioRecord = Integer.parseInt(test);
        return 0 < studioRecord && studioRecord < 13;
    }

    public Attendance getAttendance() {
        return this.attendance;
    }

    public Participation getParticipation() {
        return this.participation;
    }

    public String getExtendedStudioRecords() {
        String displayedInfo = "";
        for (int sessionNumber = 0; sessionNumber < numberOfSessions; sessionNumber++) {
            boolean hasAttendedSession = attendance.getAttendanceFromSession(sessionNumber);
            int participationScore = participation.getParticipationScoreFrom(sessionNumber);
            if (hasAttendedSession) {
                displayedInfo += "- Session " + (sessionNumber + 1) + " attended. ";
                displayedInfo += "Participation mark is " + participationScore + "\n";
            } else {
                displayedInfo += "- Session " + (sessionNumber + 1) + " not attended.\n";
            }
        }
        return displayedInfo;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudioRecord // instanceof handles nulls
                && attendance.equals(((StudioRecord) other).attendance)
                && participation.equals((((StudioRecord) other).participation)));
    }

}
