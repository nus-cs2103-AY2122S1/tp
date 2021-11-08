package seedu.address.testutil;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;

/**
 * A utility class to help with building Interview objects.
 */
public class InterviewBuilder {


    private Position position = TypicalPositions.HR_MANAGER;
    private Set<Person> candidates = new HashSet<>();
    private LocalDate localDate = LocalDate.of(2021, 10, 15);
    private LocalTime startTime = LocalTime.of(10, 0);
    private Duration duration = Duration.ofHours(1);
    private Interview.InterviewStatus status = Interview.InterviewStatus.PENDING;

    /**
     * Creates a {@code InterviewBuilder} with the default details.
     */
    public InterviewBuilder() {
    }

    /**
     * Initializes the InterviewBuilder with the data of {@code interviewToCopy}.
     */
    public InterviewBuilder(Interview interviewToCopy) {
        position = new Position(interviewToCopy.getPositionTitle());
        candidates = interviewToCopy.getCandidates();
        startTime = interviewToCopy.getStartTime();
        duration = interviewToCopy.getDuration();
        status = interviewToCopy.getStatus();
    }

    /**
     * Sets the {@code Position} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withPosition(Position position) {
        this.position = position;
        return this;
    }

    /**
     * Sets the {@code Candidates} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withCandidates(Set<Person> candidates) {
        this.candidates = candidates;
        return this;
    }

    /**
     * Sets the {@code localDate} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withDate(LocalDate date) {
        this.localDate = date;
        return this;
    }

    /**
     * Sets the {@code StartTime} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withDuration(Duration duration) {
        this.duration = duration;
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withStatus(Interview.InterviewStatus status) {
        this.status = status;
        return this;
    }

    public Interview build() {
        return new Interview(position, candidates, localDate, startTime, duration, status);
    }

}
