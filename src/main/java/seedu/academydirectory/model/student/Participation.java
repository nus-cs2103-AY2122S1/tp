package seedu.academydirectory.model.student;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Comparator;

public class Participation implements Information {

    private static final String VALIDATION_REGEX = "^-?[0-9]\\d*$";

    private int[] participationArray;

    private int totalParticipation = 0;

    /**
     * The constructor for an Participation object.
     *
     * @param numberOfSessions The number of Studios scheduled
     *                         for the semester that the student has to attend
     */
    public Participation(Integer numberOfSessions) {
        requireNonNull(numberOfSessions);
        participationArray = new int[numberOfSessions];
    }

    /**
     * Increments or decrements the participation count of the
     * given index in the participation array.
     *
     * @param index
     * @param participationAdd
     * @return
     */
    public Participation add(Integer index, int participationAdd) {
        int participationResult = participationArray[index - 1] + participationAdd;
        totalParticipation += participationAdd;
        if (participationResult < 0) {
            participationArray[index - 1] = 0;
        } else if (participationResult > 500) {
            participationArray[index - 1] = 500;
        } else {
            participationArray[index - 1] += participationAdd;
        }
        return this;
    }

    /**
     * Replace this Attendance's boolean array with the given boolean array
     * @param intArr The new boolean array
     */
    public void setParticipation(int[] intArr) {
        this.participationArray = intArr;
        totalParticipation = 0;
        for (int i : intArr) {
            totalParticipation += i;
        }
    }

    public int getTotalParticipation() {
        return totalParticipation;
    }

    /**
     * Gets the number of sessions in total
     */
    public Integer getSessionCount() {
        return participationArray.length;
    }

    /**
     * Gets the boolean array representation of the Attendance object
     */
    public int[] getParticipationArray() {
        return this.participationArray;
    }

    /**
     * Convert the boolean to the formatted String representation of a single session's attendance.
     * @param participationCount
     */
    public String participationCountToString(int participationCount) {
        String participationCountString = "";
        if (participationCount < 10) {
            participationCountString += "  " + participationCount + "  ";
        } else if (participationCount < 100) {
            participationCountString += "  " + participationCount + " ";
        } else {
            participationCountString += " " + participationCount + " ";
        }
        return "[" + participationCountString + "]"; // participation score of a student
    }

    /**
     * Checks if the given participation change is valid
     *
     * @param test The participation modifier being tested
     */
    public static boolean isValidParticipation(String test) {
        requireNonNull(test);
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        } else {
            Integer testInt = Integer.parseInt(test);
            return -500 <= testInt && testInt <= 500; // arbitrary max number first
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < participationArray.length; i++) {
            int participationCount = participationArray[i];
            str += participationCountToString(participationCount);
        }
        return str;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Participation)) {
            return false;
        }
        Participation p = (Participation) other;
        int[] e = p.participationArray;
        if (e.length != participationArray.length) {
            return false;
        }
        for (int i = 0; i < participationArray.length; i++) {
            if (e[i] != participationArray[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(participationArray);
    }

    public static Comparator<Student> getComparator(boolean isAscending) {
        return new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int participation1 = s1.getParticipation().getTotalParticipation();
                int participation2 = s2.getParticipation().getTotalParticipation();
                return isAscending
                        ? Integer.compare(participation1, participation2)
                        : Integer.compare(participation2, participation1);
            }
        };
    }
}
