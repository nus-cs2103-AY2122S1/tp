package seedu.academydirectory.model.student;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

public class Attendance implements Information {

    private boolean[] attendanceArray;

    /**
     * The constructor for an Attendance object.
     * @param numberOfSessions The number of Studios scheduled
     *                         for the semester that the student has to attend
     */
    public Attendance(Integer numberOfSessions) {
        requireNonNull(numberOfSessions);
        attendanceArray = new boolean[numberOfSessions];
    }

    /**
     * Edits the attendance of a student in the specified Studio session.
     * @param index The session number
     * @param attendanceStatus true if attended, false otherwise
     */
    public Attendance update(Integer index, boolean attendanceStatus) {
        attendanceArray[index - 1] = attendanceStatus;
        return this;
    }

    /**
     * Replace this Attendance's boolean array with the given boolean array
     * @param boolArr The new boolean array
     */
    public void setAttendance(boolean[] boolArr) {
        this.attendanceArray = boolArr;
    }

    /**
     * Gets the number of sessions in total
     */
    public Integer getSessionCount() {
        return attendanceArray.length;
    }

    /**
     * Gets the boolean array representation of the Attendance object
     */
    public boolean[] getAttendanceArray() {
        return this.attendanceArray;
    }

    /**
     * Convert the boolean to the formatted String representation of a single session's attendance.
     * @param attendanceStatus Boolean of attendance
     * @param session The session index
     */
    private String attendanceStatusToString(boolean attendanceStatus, int session) {
        if (attendanceStatus) {
            String sessionToPrint = "";
            if (session + 1 < 10) {
                sessionToPrint = "  " + (session + 1) + "  ";
            } else {
                sessionToPrint += "  " + (session + 1) + " ";
            }
            return "[" + sessionToPrint + "]"; // session index displayed if attended
        } else {
            return "[       ]"; // session index omitted if unattended
        }
    }

    /**
     * Get attendance status of a specific session number
     * @param sessionNumber to be retrieve from
     * @return attendance status
     */
    public boolean getAttendanceFromSession(Integer sessionNumber) {
        if (sessionNumber < 0 || sessionNumber >= attendanceArray.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return attendanceArray[sessionNumber];
    }

    public static boolean isValidAttendance(String test) {
        return test.equals("1") || test.equals("0");
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < attendanceArray.length; i++) {
            boolean attendanceStatus = attendanceArray[i];
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
        boolean[] e = a.attendanceArray;
        if (e.length != attendanceArray.length) {
            return false;
        }
        for (int i = 0; i < attendanceArray.length; i++) {
            if (e[i] != attendanceArray[i]) {
                return false;
            }
        }
        return true;

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(attendanceArray);
    }
}
