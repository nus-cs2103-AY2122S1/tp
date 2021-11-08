package seedu.address.model.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.person.Person;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

/**
 * Represents an Interview in the HR Manager, with default status 'pending'.
 * Guarantees: immutable; position is valid and not null.
 */
public class Interview {

    // public static final String MESSAGE_CONSTRAINTS = "";

    public static final String MESSAGE_DATE_CONSTRAINTS = "Date should be valid and in DD/MM/YYYY format.";
    public static final String MESSAGE_TIME_CONSTRAINTS = "Time should be be valid and in HHMM format.";

    public static final String MESSAGE_DURATION_CONSTRAINTS_NOT_A_NUMBER = "Duration should be a positive integer.";
    public static final String MESSAGE_DURATION_CONSTRAINTS_INVALID_NUMBER = "Duration is in minutes, "
            + "it should be more than 0 and less than 1440.";

    private Position position;

    private InterviewStatus status;

    private Set<Person> candidates;

    private final LocalTime startTime;

    private final LocalDate date;

    private final Duration duration;

    private final Set<Integer> candidateIDs = new HashSet<>();

    public enum InterviewStatus {
        PENDING,
        COMPLETED;

        public static final String MESSAGE_CONSTRAINTS = "Interview Status can ony take the values:\n"
                + "pending\n"
                + "completed\n";

        private static final List<String> validStatus = new ArrayList<>(Arrays.asList("PENDING",
                "COMPLETED", ""));

        /**
         * Return true if a give string is a valid interview status.
         * Only "pending" and "completed" are valid strings for interview status.
         *
         * @param test A string to test if is a valid interview status.
         * @return True if test string is a valid interview status, false otherwise.
         */
        public static boolean isValidInterviewStatus(String test) {
            return validStatus.contains(test);
        }

    }

    /**
     * Constructs a {@code Interview}.
     *
     * @param position   A position for the interview.
     * @param candidates A list of names of the candidates attending the interview.
     * @param date       The date of the interview.
     * @param startTime  The start time of the interview.
     * @param duration   The duration of the interview.
     */
    public Interview(Position position, Set<Person> candidates, LocalDate date,
                     LocalTime startTime, Duration duration) {
        requireAllNonNull(position, candidates, startTime, duration);
        this.position = position;
        this.candidates = candidates;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
    }

    /**
     * Constructs a {@code Interview}.
     *
     * @param position   A position for the interview.
     * @param candidates A list of names of the candidates attending the interview.
     * @param startTime  The start time of the interview.
     * @param date       The date of the interview.
     * @param duration   The duration of the interview.
     * @param status     The interview status.
     */
    public Interview(Position position, Set<Person> candidates, LocalDate date,
                     LocalTime startTime, Duration duration, InterviewStatus status) {
        this(position, candidates, date, startTime, duration);
        if (status != null) {
            this.status = status;
        }
    }

    /**
     * Returns true if both interviews have the same position, candidates, start time, and duration.
     * This defines a weaker notion of equality between two interviews.
     */
    public boolean isSameInterview(Interview otherInterview) {
        if (otherInterview == this) {
            return true;
        }

        return otherInterview != null
                && otherInterview.getPositionTitle().equals(getPositionTitle())
                && otherInterview.getDate().equals(getDate())
                && otherInterview.getStartTime().equals(getStartTime())
                && otherInterview.getDuration().equals(getDuration());
    }

    public Position getPosition() {
        assert this.position != null : "Interview position is non-null";
        return position;
    }

    public void setPosition(Position newPosition) {
        position = newPosition;
    }

    public Set<Person> getCandidates() {
        assert this.candidates != null : "Interview candidate names set is non-null.";
        return this.candidates;
    }

    public void setCandidates(Set<Person> personSet) {
        assert this.candidates != null : "Interview candidate names set is non-null.";
        this.candidates = new HashSet<>();
        this.candidates.addAll(personSet);
    }

    public LocalDate getDate() {
        assert this.date != null : "Interview date is non-null.";
        return this.date;
    }

    public String getDateInFormattedString() {
        assert this.date != null : "Interview date is non-null.";
        String[] temp = this.date.toString().split("-");
        return temp[2] + "/" + temp[1] + "/" + temp[0];
    }

    public LocalTime getStartTime() {
        assert this.startTime != null : "Interview start time is non-null.";
        return this.startTime;
    }

    public String getTimeInFormattedString() {
        assert this.startTime != null : "Interview start time is non-null.";
        return this.startTime.toString().replace(":", "");
    }

    public Duration getDuration() {
        assert this.duration != null : "Interview duration is non-null.";
        return this.duration;
    }

    public String getDurationInFormattedString() {
        assert this.duration != null : "Interview duration is non-null.";
        String temp = this.duration.toString().replace("PT", "");
        if (temp.contains("H") && temp.contains("M")) {
            int endHourIndex = temp.indexOf("H");
            return String.valueOf(Integer.parseInt(temp.substring(0, endHourIndex)) * 60
                    + Integer.parseInt(temp.substring(endHourIndex + 1, temp.length() - 1)));
        } else if (temp.contains("H")) {
            return String.valueOf(Integer.parseInt(temp.substring(0, temp.length() - 1)) * 60);
        } else {
            return temp.substring(0, temp.length() - 1);
        }
    }

    public Title getPositionTitle() {
        assert this.position != null : "Interview position is non-null.";
        return this.position.getTitle();
    }

    public InterviewStatus getStatus() {
        assert this.status != null : "Interview status is non-null.";
        return this.status;
    }

    public String getStatusInString() {
        assert this.status != null : "Interview status is non-null.";
        return this.status.toString();
    }

    public void setStatus(InterviewStatus status) {
        requireNonNull(status);
        this.status = status;
    }

    /**
     * Checks if another interview has timing overlaps with this interview.
     * @param other the interview to check against.
     * @return true if they start at the same time or if one begins before the other ends.
     */
    public boolean hasOverLapWith(Interview other) {
        LocalDateTime toScheduleStart = LocalDateTime.of(other.getDate(), other.getStartTime());
        LocalDateTime toScheduleEnd = LocalDateTime.of(other.getDate(), other.getEndTime());

        LocalDateTime start = LocalDateTime.of(getDate(), getStartTime());
        LocalDateTime end = LocalDateTime.of(getDate(), getEndTime());

        if (getStartTime().getHour() > getEndTime().getHour()
            || (getStartTime().getHour() == getEndTime().getHour() && getDuration().toHours() > 22)) {
            end = LocalDateTime.of(getDate().plusDays(1), getEndTime());
        }

        if (other.getStartTime().getHour() > other.getEndTime().getHour()
                || (other.getStartTime().getHour() == other.getEndTime().getHour()
                && other.getDuration().toHours() > 22)) {
            toScheduleEnd = LocalDateTime.of(other.getDate().plusDays(1), getEndTime());
        }

        return (toScheduleStart.isAfter(start) && toScheduleStart.isBefore(end))
                || start.isEqual(toScheduleStart)
                || (start.isAfter(toScheduleStart) && start.isBefore(toScheduleEnd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Interview // instanceof handles nulls
                && position.equals(((Interview) other).position) // position check
                && candidates.equals(((Interview) other).candidates) // candidates check
                && date.equals(((Interview) other).date)
                && startTime.equals(((Interview) other).startTime) // startTime check
                && duration.equals(((Interview) other).duration)
                && status.equals(((Interview) other).status)); // status check
    }

    public void setCandidateIDs(Set<Integer> candidateIDs) {
        this.candidateIDs.addAll(candidateIDs);
    }

    public Set<Integer> getCandidateIDs() {
        return candidateIDs;
    }

    //for checking and adding interview to person
    public boolean hasCandidate(Person person) {
        return candidates.contains(person);
    }

    public void deleteCandidate(Person person) {
        candidates.remove(person);
    }

    public void addCandidate(Person person) {
        candidates.add(person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, date, startTime, duration, status);
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return "[" + getPositionTitle().toString() + " "
                + getCandidates().toString() + " "
                + getDate() + " "
                + getDisplayTimePeriod() + " "
                + getStatusInString() + "]";
    }


    public LocalTime getEndTime() {
        return startTime.plusMinutes(duration.toMinutes());
    }

    public String getDisplayDate() {
        String month = Month.of(date.getMonthValue()).toString();
        month = month.charAt(0) + month.substring(1, 3).toLowerCase();
        return date.getDayOfMonth() + " " + month + " " + date.getYear();
    }

    public String getCandidatesNames() {
        Set<String> names = candidates.stream().map(c -> c.getName().fullName).collect(Collectors.toSet());
        return names.toString();
    }

    public String getCandidateNameString() {
        assert this.candidates != null;
        Set<String> names = candidates.stream().map(c -> c.getName().fullName).collect(Collectors.toSet());
        return names.toString().replace("[", "").replace("]", " ")
                .replace(",", "");
    }


    public String getDisplayString() {
        return "[" + getPositionTitle().toString() + " "
                + getCandidatesNames() + " "
                + getDisplayDate() + " "
                + getStartTime() + " - "
                + getEndTime() + " "
                + getStatusInString() + "]";
    }

    public String getDisplayTimePeriod() {
        return getStartTime() + " ~ " + getEndTime();
    }

    public String getDisplayStringWithoutNames() {
        return "[" + getPositionTitle().toString() + " "
                + getDisplayDate() + " "
                + getDisplayTimePeriod() + " "
                + getStatusInString() + "]";
    }
}

