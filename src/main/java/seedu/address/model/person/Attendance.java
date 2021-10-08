package seedu.address.model.person;

import java.util.ArrayList;

/**
 * Represents a Person's attendance in tApp.
 * Guarantees: immutable;
 */
public class Attendance {

    public static final String MESSAGE_CONSTRAINTS =
            "Weeks should be between 1 and %1$s";

    public static final int NUMBER_OF_WEEKS = 13;

    public final ArrayList<Integer> attendanceList;

    /**
     * Constructs an {@code Attendance}.
     */
    public Attendance() {
        attendanceList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_WEEKS; i++) {
            attendanceList.add(0);
        }
    }

    /**
     * Constructs an existing {@code Attendance}.
     */
    public Attendance(ArrayList<Integer> attendanceList) {
        this.attendanceList = attendanceList;
    }

    /**
     * Toggles attendance from 0 to 1, or 1 to 0
     * for the {@code week}.
     *
     * @param week attendance for the week
     */
    public void toggleAttendance(int week) {
        int isPresent = attendanceList.get(week);
        if (isPresent == 1) {
            attendanceList.set(week, 0);
        } else {
            attendanceList.set(week, 1);
        }
    }

    /**
     * Checks if a student is present during the specified {@code week}.
     *
     * @param week week to be checked.
     * @return int specifying if student was present.
     */
    public int checkPresent(int week) {
        int isPresent = attendanceList.get(week);
        return isPresent;
    }

    /**
     * Returns true if a given string is a valid week.
     */
    public static boolean isValidWeek(int week) {
        return week <= NUMBER_OF_WEEKS && week > 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Attendance // instanceof handles nulls
                && attendanceList.equals(((Attendance) other).attendanceList)); // state check
    }

    @Override
    public int hashCode() {
        return attendanceList.hashCode();
    }

}
