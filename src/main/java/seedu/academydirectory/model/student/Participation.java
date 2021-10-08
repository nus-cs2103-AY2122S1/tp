package seedu.academydirectory.model.student;

import static java.util.Objects.requireNonNull;

public class Participation implements Information {

    private int[] participationArray;

    private static final String VALIDATION_REGEX = "/[0-9]+/";

    /**
     * The constructor for an Participation object.
     * @param numberOfSessions The number of Studios scheduled
     *                         for the semester that the student has to attend
     */
    public Participation(Integer numberOfSessions) {
        requireNonNull(numberOfSessions);
        participationArray = new int[numberOfSessions];
    }

    public Participation add(Integer index, int participationAdd) {
        int participationResult = participationArray[index - 1] + participationAdd;
        if (participationResult < 0) {
            participationArray[index - 1] = 0;
        } else if (participationResult > 100) { // TODO: Work out a proper max
            participationArray[index - 1] = 100;
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
        return "[" + participationCount + "]"; // participation score of a student
    }

    public static boolean isValidParticipation(String test) {
        requireNonNull(test);
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        } else {
            Integer testInt = Integer.parseInt(test);
            return -100 <= testInt && testInt <= 100; // arbitrary max number first TODO: work out a proper max
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
        return participationArray.hashCode();
    }
}
