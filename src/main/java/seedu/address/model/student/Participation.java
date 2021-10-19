package seedu.address.model.student;

import java.util.ArrayList;

/**
 * Represents a Student's tutorial participation in tApp.
 * Guarantees: immutable;
 */
public class Participation {

    public static final String MESSAGE_CONSTRAINTS =
            "Weeks should be between 1 and %1$s";

    public static final int NUMBER_OF_WEEKS = 13;

    public final ArrayList<Integer> participationList;

    /**
     * Constructs an {@code Participation}.
     */
    public Participation() {
        participationList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_WEEKS; i++) {
            participationList.add(0);
        }
    }

    /**
     * Constructs an existing {@code Participation}.
     */
    public Participation(ArrayList<Integer> participationList) {
        this.participationList = participationList;
    }

    /**
     * Toggles participation from 0 to 1, or 1 to 0
     * for the {@code week}.
     *
     * @param week participation for the week
     */
    public void toggleParticipation(int week) {
        int isPresent = participationList.get(week);
        if (isPresent == 1) {
            participationList.set(week, 0);
        } else {
            participationList.set(week, 1);
        }
    }

    /**
     * Checks if a student is present during the specified {@code week}.
     *
     * @param week week to be checked.
     * @return int specifying if student was present.
     */
    public int checkPresent(int week) {
        int isPresent = participationList.get(week);
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
                || (other instanceof Participation // instanceof handles nulls
                && participationList.equals(((Participation) other).participationList)); // state check
    }

    @Override
    public int hashCode() {
        return participationList.hashCode();
    }
}
