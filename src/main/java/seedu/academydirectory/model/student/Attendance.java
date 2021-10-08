package seedu.academydirectory.model.student;

import static java.util.Objects.requireNonNull;

public class Attendance implements Information {

    private boolean[] attendanceInBoolean;

    /**
     * The constructor for an Attendance object.
     * @param numberOfSessions The number of Studios scheduled
     *                         for the semester that the student has to attend
     */
    public Attendance(Integer numberOfSessions) {
        requireNonNull(numberOfSessions);
        attendanceInBoolean = new boolean[numberOfSessions];
    }

    /**
     * Edits the attendance of a student in the specified Studio session.
     * @param index The session number
     * @param attendanceStatus true if attended, false otherwise
     */
    public Attendance update(Integer index, boolean attendanceStatus) {
        attendanceInBoolean[index - 1] = attendanceStatus;
        return this;
    }

    /**
     * Replace this Attendance's boolean array with the given boolean array
     * @param boolArr The new boolean array
     */
    public void setAttendance(boolean[] boolArr) {
        this.attendanceInBoolean = boolArr;
    }

    /**
     * Gets the number of sessions in total
     */
    public Integer getSessionCount() {
        return attendanceInBoolean.length;
    }

    /**
     * Gets the boolean array representation of the Attendance object
     */
    public boolean[] getAttendanceInBoolean() {
        return this.attendanceInBoolean;
    }

    /**
     * Convert the boolean to the formatted String representation of a single session's attendance.
     * @param attendanceStatus Boolean of attendance
     * @param session The session index
     */
    public String attendanceStatusToString(boolean attendanceStatus, int session) {
        if (attendanceStatus) {
            return "[" + (session + 1) + "]"; // session index displayed if attended
        } else {
            return "[ ]"; // session index omitted if unattended
        }
    }

    public static boolean isValidAttendance(String test) {
        return test.equals("1") || test.equals("0");
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < attendanceInBoolean.length; i++) {
            boolean attendanceStatus = attendanceInBoolean[i];
            str += attendanceStatusToString(attendanceStatus, i);
        }
        return str;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Attendance)) {
            return false;
        }
        Attendance a = (Attendance) other;
        boolean[] e = a.attendanceInBoolean;
        if (e.length != attendanceInBoolean.length) {
            return false;
        }
        for (int i = 0; i < attendanceInBoolean.length; i++) {
            if (e[i] != attendanceInBoolean[i]) {
                return false;
            }
        }
        return true;

    }

    @Override
    public int hashCode() {
        return attendanceInBoolean.hashCode();
    }
}
