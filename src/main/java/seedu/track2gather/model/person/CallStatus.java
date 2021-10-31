package seedu.track2gather.model.person;

import static seedu.track2gather.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Person's call status in the contacts list.
 * Guarantee: immutable; is valid as declared in {@link #isValidCallStatus(String)}
 */
public class CallStatus {
    public static final String MESSAGE_CONSTRAINTS =
        "CallStatus should be a non-negative integer followed by a boolean, separated by whitespace.";
    public static final String VALIDATION_REGEX = "^\\d+\\s+(true|false)$";

    private final int numFailedCalls;
    private final boolean isCalledInCurrentSession;

    /**
     * Constructs a {@code CallStatus}.
     *
     * @param numFailedCalls Number of failed calls.
     * @param isCalledInCurrentSession Boolean indicating if the person has been called in the current session.
     */
    public CallStatus(int numFailedCalls, boolean isCalledInCurrentSession) {
        requireAllNonNull(numFailedCalls, isCalledInCurrentSession);
        this.numFailedCalls = numFailedCalls;
        this.isCalledInCurrentSession = isCalledInCurrentSession;
    }

    /**
     * Constructs a {@code CallStatus}.
     *
     * @param callStatusString String containing number of failed calls and a boolean flag for whether they have been
     *                         called in the current session.
     */
    public CallStatus(String callStatusString) {
        this(Integer.valueOf(callStatusString.split(" ", 2)[0]),
                Boolean.valueOf(callStatusString.split(" ", 2)[1]));
    }

    /**
     * Tests validity of a given string as input to be parsed into {@code CallStatus}
     *
     * @param test string to test
     * @return bool whether the string is valid as an input.
     */
    public static boolean isValidCallStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a new {@code CallStatus} with {@code isCalledInCurrentSession} set to true.
     */
    public CallStatus call() {
        return new CallStatus(numFailedCalls, true);
    }

    /**
     * Returns a new {@code CallStatus} with {@code isCalledInCurrentSession} set to false.
     */
    public CallStatus reset() {
        return new CallStatus(numFailedCalls, false);
    }

    /**
     * Returns true if the person has been called in the current session.
     */
    public boolean isCalledInCurrentSession() {
        return isCalledInCurrentSession;
    }

    /**
     * Returns number of times person has failed to pick up since the start of SHN period.
     */
    public int getNumFailedCalls() {
        return numFailedCalls;
    }

    /**
     * Returns a new {@code CallStatus} which increments {@code numFailedCalls} and sets
     * {@code isCalledInCurrentSession} to true.
     */
    public CallStatus incrementNumFailedCalls() {
        return new CallStatus(numFailedCalls + 1, true);
    }

    @Override
    public String toString() {
        return numFailedCalls + " " + isCalledInCurrentSession;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof CallStatus // instanceof handles nulls
            && numFailedCalls == ((CallStatus) other).numFailedCalls
            && isCalledInCurrentSession == ((CallStatus) other).isCalledInCurrentSession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numFailedCalls, isCalledInCurrentSession);
    }

}
