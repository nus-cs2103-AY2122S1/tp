package seedu.address.model.student;

import java.util.ArrayList;

/**
 * Represents a Student's attendance in tApp.
 * Guarantees: immutable;
 */
public class Attendance {

    public static final String MESSAGE_CONSTRAINTS =
            "The week parameter should contain one positive integer between %1$s and %2$s!";

    public static final int FIRST_WEEK_OF_SEM = 3;
    public static final int LAST_WEEK_OF_SEM = 12;

    public final ArrayList<Integer> attendanceList;

    /**
     * Constructs an {@code Attendance}.
     */
    public Attendance() {
        attendanceList = new ArrayList<>();
        for (int i = FIRST_WEEK_OF_SEM; i <= LAST_WEEK_OF_SEM; i++) {
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
        attendanceList.set(week, 1 - isPresent);
    }

    /**
     * Checks if a student is present during the specified {@code week}.
     *
     * @param week week to be checked.
     * @return int specifying if student was present.
     */
    public boolean checkPresent(int week) {
        return attendanceList.get(week) == 1;
    }

    /**
     * Returns true if a given string is a valid week.
     */
    public static boolean isValidWeek(int week) {
        return week <= LAST_WEEK_OF_SEM && week >= FIRST_WEEK_OF_SEM;
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
