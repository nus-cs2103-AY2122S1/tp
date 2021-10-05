package seedu.academydirectory.model.person;

import static java.util.Objects.requireNonNull;

public class Attendance {

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

    public void setAttendance(boolean[] boolArr) {
        this.attendanceInBoolean = boolArr;
    }

    public Integer getSessionCount() {
        return attendanceInBoolean.length;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < attendanceInBoolean.length; i++) {
            str += attendanceInBoolean[i] ? "[" + (i + 1) + "]" : "[ ]";
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
